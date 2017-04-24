package es.rubenmoreno.sudokusolver.board

// row,col position of square [0,2]
case class Square(cells: Array[Int], row: Int, col: Int)

object Square {

  val size = 3
  val range = 3

  def score(sq: Square): Int = {

    def createMarkers(sq: Square) = {

      def boardPos(i: Int, j: Int): Int = (i + sq.row * size) * Board.Size + sq.col * size + j

      val markers = Array.fill[Int](Board.Valid)(0)

      for {
        i <- 0 to size - 1
        j <- 0 to size - 1
      } markers(sq.cells(boardPos(i, j)) - 1) += 1

      markers
    }

    def calculate(markers: Array[Int]): Int = {
      markers.foldLeft(0)((acc, n) => {
        if (n > 1)
          acc + n * n
        else
          acc
      })
    }

    val markers = createMarkers(sq)
    calculate(markers)
  }
}
