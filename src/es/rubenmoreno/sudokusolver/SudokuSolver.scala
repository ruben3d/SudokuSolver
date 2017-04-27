package es.rubenmoreno.sudokusolver

import es.rubenmoreno.sudokusolver.board.Board
import es.rubenmoreno.sudokusolver.board.Square
import es.rubenmoreno.sudokusolver.board.Column
import es.rubenmoreno.sudokusolver.board.Row

object SudokuSolver extends App {

  def debugSquares(board: Board) = {

    println("Squares:")

    println(Square(board.cells, 0, 0).score + " " +
      Square(board.cells, 0, 1).score + " " +
      Square(board.cells, 0, 2).score)
    println(Square(board.cells, 1, 0).score + " " +
      Square(board.cells, 1, 1).score + " " +
      Square(board.cells, 1, 2).score)
    println(Square(board.cells, 2, 0).score + " " +
      Square(board.cells, 2, 1).score + " " +
      Square(board.cells, 2, 2).score)
  }

  def debugColumns(board: Board) = {
    println("Columns:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Column(board.cells, idx).score)
    println(scores.mkString(" "))
  }

  def debugRows(board: Board) = {
    println("Rows:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Row(board.cells, idx).score)
    println(scores.mkString(" "))
  }

  def debugBoard(board: Board) = {
    println("Board:")
    println(board.score)
  }

  def createRandomBoard() = Board(Array.fill(Board.Size * Board.Size)(scala.util.Random.nextInt(Board.Valid) + 1))

  val board = createRandomBoard()
  println(board)

  debugSquares(board)
  debugColumns(board)
  debugRows(board)
  debugBoard(board)
}
