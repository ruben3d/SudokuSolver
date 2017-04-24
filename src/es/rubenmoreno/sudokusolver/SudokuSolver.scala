package es.rubenmoreno.sudokusolver

import es.rubenmoreno.sudokusolver.board.Board
import es.rubenmoreno.sudokusolver.board.Square
import es.rubenmoreno.sudokusolver.board.Column
import es.rubenmoreno.sudokusolver.board.Row

object SudokuSolver extends App {

  def debugSquares(board: Board) = {

    println("Squares:")

    println(Square.score(Square(board.cells, 0, 0)) + " " +
      Square.score(Square(board.cells, 0, 1)) + " " +
      Square.score(Square(board.cells, 0, 2)))
    println(Square.score(Square(board.cells, 1, 0)) + " " +
      Square.score(Square(board.cells, 1, 1)) + " " +
      Square.score(Square(board.cells, 1, 2)))
    println(Square.score(Square(board.cells, 2, 0)) + " " +
      Square.score(Square(board.cells, 2, 1)) + " " +
      Square.score(Square(board.cells, 2, 2)))
  }

  def debugColumns(board: Board) = {
    println("Columns:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Column.score(Column(board.cells, idx)))
    println(scores.mkString(" "))
  }

  def debugRows(board: Board) = {
    println("Rows:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Row.score(Row(board.cells, idx)))
    println(scores.mkString(" "))
  }

  def debugBoard(board: Board) = {
    println("Board:")
    println(board.score)
  }

  val board = Board(Array.fill(Board.Size * Board.Size) { scala.util.Random.nextInt(Board.Valid) + 1 })
  println(board)

  debugSquares(board)
  debugColumns(board)
  debugRows(board)
  debugBoard(board)
}
