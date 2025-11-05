package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.unit.UnitCard

class NoAbility extends UnitCardAbility {
  def apply[T <: UnitCard](zone: UnitBoardZone[T]): Unit = {}
}
