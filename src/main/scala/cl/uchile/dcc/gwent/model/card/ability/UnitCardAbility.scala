package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.card.unit.UnitCard
import cl.uchile.dcc.gwent.model.board.UnitBoardZone

trait UnitCardAbility extends Ability {
  def apply[T <: UnitCard](zone: UnitBoardZone[T]): Unit
}