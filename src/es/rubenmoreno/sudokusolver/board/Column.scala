package es.rubenmoreno.sudokusolver.board

case class Column(cells: Array[Int], idx: Int)

object Column {

  val size = Board.Size

  def score(c: Column): Int = {
    
    def createMarkers(c: Column) = {

      def boardPos(i: Int, j: Int): Int = i * Board.Size + j

      val markers = Array.fill[Int](Board.Valid)(0)

      for {
        i <- 0 to size - 1
      } markers(c.cells(boardPos(i, c.idx)) - 1) += 1

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

    val markers = createMarkers(c)
    calculate(markers)
  }
}
