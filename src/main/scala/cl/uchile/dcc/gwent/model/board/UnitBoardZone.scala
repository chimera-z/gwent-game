package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.unit.UnitCard

class UnitBoardZone[T <: UnitCard] extends AbstractBoardZone[T] {
  def addCard(card: T): Unit = _cards.addOne(card)
}
