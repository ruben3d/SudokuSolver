package es.rubenmoreno.sudokusolver

package object board {

  def debugPrintSquareScores(board: Board) = {

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

  def debugPrintColumnScores(board: Board) = {
    println("Columns:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Column(board.cells, idx).score)
    println(scores.mkString(" "))
  }

  def debugPrintRowScores(board: Board) = {
    println("Rows:")

    val range = 0 until Board.Size
    val scores = range.map(idx => Row(board.cells, idx).score)
    println(scores.mkString(" "))
  }

  def debugPrintBoardScore(board: Board) = {
    println("Board:")
    println(board.score.getOrElse(-1))
  }

}