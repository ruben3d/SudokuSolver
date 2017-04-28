package es.rubenmoreno.sudokusolver.board

class Column(cells: Array[Int], idx: Int) {

  val score = computeScore(cells, idx)

  private def computeScore(cells: Array[Int], idx: Int): Int = {

    def boardPos(i: Int, j: Int) = i * Board.Size + j

    Board.computeSubScore(cells, (c: Array[Int]) =>
      for {
        i <- 0 until Board.Size toArray
      } yield cells(boardPos(i, idx)))
  }
}

object Column {

  def apply(cells: Array[Int], idx: Int): Column = new Column(cells, idx)

}
