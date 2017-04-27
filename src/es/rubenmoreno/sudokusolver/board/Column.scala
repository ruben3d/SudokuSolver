package es.rubenmoreno.sudokusolver.board

class Column(cells: Array[Int], idx: Int) {

  val score = computeScore(cells, idx)

  private def computeScore(cells: Array[Int], idx: Int): Int = {

    def createMarkers(cells: Array[Int], idx: Int) = {

      def boardPos(i: Int, j: Int) = i * Board.Size + j

      val markers = Array.fill[Int](Board.Valid)(0)

      for {
        i <- 0 until Board.Size
      } markers(cells(boardPos(i, idx)) - 1) += 1

      markers
    }

    def calculate(markers: Array[Int]): Int =
      markers.foldLeft(0)((acc, n) =>
        if (n > 1)
          acc + n * n
        else
          acc)

    val markers = createMarkers(cells, idx)
    calculate(markers)
  }
}

object Column {

  def apply(cells: Array[Int], idx: Int): Column = new Column(cells, idx)

}
