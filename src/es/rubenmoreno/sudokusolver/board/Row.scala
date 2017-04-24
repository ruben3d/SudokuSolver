package es.rubenmoreno.sudokusolver.board

case class Row(cells: Array[Int], idx: Int)

object Row {

  val size = Board.Size

  def score(r: Row): Int = {

    def createMarkers(r: Row) = {

      def boardPos(i: Int, j: Int): Int = i * Board.Size + j

      val markers = Array.fill[Int](Board.Valid)(0)

      for {
        j <- 0 to size - 1
      } markers(r.cells(boardPos(r.idx, j)) - 1) += 1

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

    val markers = createMarkers(r)
    calculate(markers)
  }
}
