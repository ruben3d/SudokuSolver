package es.rubenmoreno.sudokusolver

import es.rubenmoreno.sudokusolver.board.Board
import es.rubenmoreno.sudokusolver.board.debugPrintBoard
import es.rubenmoreno.sudokusolver.board.debugPrintLoadedBoard
import es.rubenmoreno.sudokusolver.solver.Settings
import es.rubenmoreno.sudokusolver.solver.Solver

object SudokuSolver extends App {

  val empty = Board.load("res/empty.txt")

  println("Empty:")
  debugPrintLoadedBoard(empty)

  val board01 = Board.load("res/board01.txt")

  println("Board01:")
  debugPrintLoadedBoard(board01)

  board01.foreach(board => {
    val boardA = board.populate
    println("Board A:")
    debugPrintBoard(boardA)

    val boardB = board.populate
    println("Board B:")
    debugPrintBoard(boardB)

    val boardC :: boardD :: Nil = boardA cross boardB

    println("Board C:")
    debugPrintBoard(boardC)

    println("Board D:")
    debugPrintBoard(boardD)

    val boardE = boardD.mutate(0.1)
    println("Board E:")
    debugPrintBoard(boardE)

    val solver = new Solver(Settings(1000, 500, 0.04, 0.04))
    val best = solver solve board

    println("Best:")
    debugPrintBoard(best)
  })
}
