package es.rubenmoreno.sudokusolver.board

import scala.collection.immutable.Vector

// Row after row
class Board(c: Array[Int]) {

  val cells = c
  val score = computeScore(c)

  private def computeScore(cells: Array[Int]): Int = {

    def computeSquareScores(cells: Array[Int]): Int = {

      val coords = for {
        i <- 0 until Square.range
        j <- 0 until Square.range
      } yield (i, j)

      coords.foldLeft(0)((acc, coord) => acc + Square.score(Square(cells, coord._1, coord._2)))
    }

    def computeColumnScores(cells: Array[Int]): Int = {
      val coords = 0 until Board.Size
      coords.foldLeft(0)((acc, coord) => acc + Column.score(Column(cells, coord)))
    }

    def computeRowScores(cells: Array[Int]): Int = {
      val coords = 0 until Board.Size
      coords.foldLeft(0)((acc, coord) => acc + Row.score(Row(cells, coord)))
    }

    computeSquareScores(cells) + computeColumnScores(cells) + computeRowScores(cells)
  }

  override def toString: String = {

    val bar = "+-----+-----+-----+\n"

    def addBar(acc: Int) = if (acc % 3 == 0) bar else ""

    val body = cells.sliding(Board.Size, Board.Size).foldLeft(("", 0))((acc, a) => {

      val line = a.sliding(Square.size, Square.size).foldLeft("")((str, s) => str + s.mkString("|", " ", ""))

      (acc._1 + addBar(acc._2) + line + "|\n", acc._2 + 1)
    })._1

    body + bar
  }
}

object Board {
  val Size = 9
  val Valid = 9

  def apply(cells: Array[Int]): Board = new Board(cells)
}