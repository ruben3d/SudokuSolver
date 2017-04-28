package es.rubenmoreno.sudokusolver.board

class Row(cells: Array[Int], idx: Int) {

  val score = computeScore(cells, idx)

  private def computeScore(cells: Array[Int], idx: Int): Int = {

    def boardPos(i: Int, j: Int) = i * Board.Size + j

    Board.computeSubScore(cells, (c: Array[Int]) =>
      for {
        j <- 0 until Board.Size toArray
      } yield cells(boardPos(idx, j)))
  }
}

object Row {

  def apply(cells: Array[Int], idx: Int): Row = new Row(cells, idx)

}
