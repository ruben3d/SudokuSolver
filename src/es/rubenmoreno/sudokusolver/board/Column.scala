package es.rubenmoreno.sudokusolver.board

class Column(cells: Array[Cell], idx: Int) {

  val score = computeScore(cells, idx)

  private def computeScore(cells: Array[Cell], idx: Int): Int = {

    def boardPos(i: Int, j: Int) = i * Board.Size + j

    Board.computeSubScore(cells, (c: Array[Cell]) =>
      for {
        i <- 0 until Board.Size
      } yield cells(boardPos(i, idx)))
  }
}

object Column {

  def apply(cells: Array[Cell], idx: Int): Column = new Column(cells, idx)

}
