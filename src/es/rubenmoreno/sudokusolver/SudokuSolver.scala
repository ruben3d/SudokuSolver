package es.rubenmoreno.sudokusolver

import es.rubenmoreno.sudokusolver.board.Board
import es.rubenmoreno.sudokusolver.board.FreeCell
import es.rubenmoreno.sudokusolver.board.debugPrintBoardScore
import es.rubenmoreno.sudokusolver.board.debugPrintColumnScores
import es.rubenmoreno.sudokusolver.board.debugPrintRowScores
import es.rubenmoreno.sudokusolver.board.debugPrintSquareScores

object SudokuSolver extends App {

  def createRandomBoard() = Board(Array.fill(Board.Size * Board.Size)(scala.util.Random.nextInt(Board.Valid) + 1).map(FreeCell(_)))

  val board = createRandomBoard()
  println(board)

  debugPrintSquareScores(board)
  debugPrintColumnScores(board)
  debugPrintRowScores(board)
  debugPrintBoardScore(board)
}
