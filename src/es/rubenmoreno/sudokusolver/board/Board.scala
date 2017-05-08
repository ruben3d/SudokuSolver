package es.rubenmoreno.sudokusolver.board

import scala.io.Source
import scala.util.Random

// Row after row
class Board(val cells: Array[Cell]) {

  val isTemplate: Boolean = cells.exists(_ match {
    case Empty => true
    case _     => false
  })

  val score: Option[Int] = computeScore(cells, isTemplate)

  private def computeScore(cells: Array[Cell], isTemplate: Boolean): Option[Int] = {

    def computeSquareScores(cells: Array[Cell]): Int = {

      val coords = for {
        i <- 0 until Square.Range
        j <- 0 until Square.Range
      } yield (i, j)

      coords.foldLeft(0)((acc, coord) => acc + Square(cells, coord._1, coord._2).score)
    }

    def computeColumnScores(cells: Array[Cell]): Int = {
      val coords = 0 until Board.Size
      coords.foldLeft(0)((acc, coord) => acc + Column(cells, coord).score)
    }

    def computeRowScores(cells: Array[Cell]): Int = {
      val coords = 0 until Board.Size
      coords.foldLeft(0)((acc, coord) => acc + Row(cells, coord).score)
    }

    if (isTemplate)
      None
    else
      Some(computeSquareScores(cells) + computeColumnScores(cells) + computeRowScores(cells))
  }

  override def toString: String = {

    val bar = "+-----+-----+-----+\n"

    def addBar(acc: Int) = if (acc % 3 == 0) bar else ""

    val body = cells.sliding(Board.Size, Board.Size).foldLeft(("", 0))((acc, rawLine) => {

      val line = rawLine.sliding(Square.Size, Square.Size).foldLeft("")((str, rawSegment) => str + rawSegment.mkString("|", " ", ""))

      (acc._1 + addBar(acc._2) + line + "|\n", acc._2 + 1)
    })._1

    body + bar
  }

  def populate: Board = {
    val newCells = cells.map(cell => cell match {
      case Empty => FreeCell(Random.nextInt(Board.Valid) + 1)
      case _     => cell
    })
    Board(newCells)
  }

  def cross(that: Board): Seq[Board] = {
    val pivot = Random.nextInt(cells.length)
    val seg0 = cells.splitAt(pivot)
    val seg1 = that.cells.splitAt(pivot)
    val cells0 = seg0._1 ++ seg1._2
    val cells1 = seg1._1 ++ seg0._2

    List(Board(cells0), Board(cells1))
  }

  def mutate(probability: Double): Board = {
    val newCells = cells.map(cell => cell match {
      case Empty         => Empty
      case LockedCell(v) => LockedCell(v)
      case FreeCell(v)   => if (Random.nextDouble() < probability) FreeCell(Random.nextInt(Board.Valid) + 1) else FreeCell(v)
    }).toArray[Cell]
    Board(newCells)
  }
}

object Board {
  val Size = 9
  val Valid = 9
  val WorstScore = 2187

  def apply(cells: Array[Cell]): Board = new Board(cells)

  def load(file: String): Option[Board] = {

    try {

      val tokens = Source.fromFile(file).getLines().flatMap(_ split (" "))
      val cells = tokens.map(token => token toInt match {
        case 0                          => Empty
        case i if 1 to Valid contains i => LockedCell(i)
        case _                          => throw new IllegalArgumentException
      }).toArray[Cell]

      if (cells.length == Size * Size)
        Some(Board(cells))
      else
        throw new IllegalArgumentException

    } catch {
      case e: Throwable => None
    }
  }

  // enumerator: extracts the 9 elements from the cells to compute a score
  def computeSubScore(cells: Array[Cell], enumerator: Array[Cell] => Seq[Cell]): Int = {

    def createMarkers(elements: Seq[Cell]) = {

      val m = Array.fill[Int](Valid)(0)

      elements.foldLeft(m)((markers, e) => e match {
        case Empty => throw new IllegalArgumentException("A board with an Empty cell can not be scored.")
        case FreeCell(v) => {
          markers(v - 1) += 1
          markers
        }
        case LockedCell(v) => {
          markers(v - 1) += 1
          markers
        }
      })
    }

    def calculate(markers: Array[Int]) =
      markers.foldLeft(0)((acc, n) =>
        if (n > 1)
          acc + n * n
        else
          acc)

    val markers = createMarkers(enumerator(cells))
    calculate(markers)
  }
}
