package es.rubenmoreno.sudokusolver.board

class Row(cells: Array[Cell], idx: Int) {

  val score = computeScore(cells, idx)

  private def computeScore(cells: Array[Cell], idx: Int): Int = {

    def boardPos(i: Int, j: Int) = i * Board.Size + j

    Board.computeSubScore(cells, (c: Array[Cell]) =>
      for {
        j <- 0 until Board.Size
      } yield cells(boardPos(idx, j)))
  }
}

object Row {

  def apply(cells: Array[Cell], idx: Int): Row = new Row(cells, idx)

}
