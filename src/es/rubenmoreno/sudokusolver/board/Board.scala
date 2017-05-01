package es.rubenmoreno.sudokusolver.board

// Row after row
class Board(val cells: Array[Cell]) {

  val score: Option[Int] = computeScore(cells)

  val isTemplate: Boolean = cells.exists(_ match {
    case Empty => true
    case _     => false
  })

  private def computeScore(cells: Array[Cell]): Option[Int] = {

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
}

object Board {
  val Size = 9
  val Valid = 9

  def apply(cells: Array[Cell]): Board = new Board(cells)

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
