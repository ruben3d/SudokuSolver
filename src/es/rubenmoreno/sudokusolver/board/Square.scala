package es.rubenmoreno.sudokusolver.board

// row,col position of square [0,2]
class Square(cells: Array[Int], row: Int, col: Int) {

  val score = computeScore(cells, row, col)

  private def computeScore(cells: Array[Int], row: Int, col: Int): Int = {

    def createMarkers(cells: Array[Int], row: Int, col: Int) = {

      def boardPos(i: Int, j: Int) = (i + row * Square.Size) * Board.Size + col * Square.Size + j

      val markers = Array.fill[Int](Board.Valid)(0)

      for {
        i <- 0 until Square.Size
        j <- 0 until Square.Size
      } markers(cells(boardPos(i, j)) - 1) += 1

      markers
    }

    def calculate(markers: Array[Int]): Int =
      markers.foldLeft(0)((acc, n) =>
        if (n > 1)
          acc + n * n
        else
          acc)

    val markers = createMarkers(cells, row, col)
    calculate(markers)
  }
}

object Square {

  val Size = 3
  val Range = 3

  def apply(cells: Array[Int], row: Int, col: Int): Square = new Square(cells, row, col)
}
