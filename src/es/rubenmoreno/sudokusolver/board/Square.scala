package es.rubenmoreno.sudokusolver.board

// row,col position of square [0,2]
private class Square(cells: Array[Cell], row: Int, col: Int) {

  val score = computeScore(cells, row, col)

  private def computeScore(cells: Array[Cell], row: Int, col: Int): Int = {

    def boardPos(i: Int, j: Int) = (i + row * Square.Size) * Board.Size + col * Square.Size + j

    Board.computeSubScore(cells, (c: Array[Cell]) =>
      for {
        i <- 0 until Square.Size
        j <- 0 until Square.Size
      } yield cells(boardPos(i, j)))
  }
}

private object Square {

  val Size = 3
  val Range = 3

  def apply(cells: Array[Cell], row: Int, col: Int): Square = new Square(cells, row, col)
}
