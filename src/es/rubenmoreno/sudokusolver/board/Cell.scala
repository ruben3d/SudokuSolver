package es.rubenmoreno.sudokusolver.board

sealed trait Cell
case object Empty extends Cell {
  override def toString: String = "X"
}
case class FreeCell(val value: Int) extends Cell {
  override def toString: String = value toString
}
case class LockedCell(val value: Int) extends Cell {
  override def toString: String = value toString
}

object Cell {
  implicit def FreeCellToInt(cell: FreeCell): Int = cell.value
  implicit def LockedCellToInt(cell: LockedCell): Int = cell.value
}
