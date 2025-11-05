package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.unit.UnitCard
import cl.uchile.dcc.gwent.model.card.effect.{Effect, StrengthPlusOne}

class MoralReinforcement extends UnitCardAbility {
  private val _effect: Effect = new StrengthPlusOne

  def apply[T <: UnitCard](zone: UnitBoardZone[T]): Unit = {
    for (card <- zone.cards.dropRight(1)) card.applyEffect(_effect)
  }
}
