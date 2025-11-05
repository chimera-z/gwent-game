package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.effect.{DoubleStrength, Effect}
import cl.uchile.dcc.gwent.model.card.unit.UnitCard

class CloseBond extends UnitCardAbility {
  private val _effect: Effect = new DoubleStrength
  
  def apply[T <: UnitCard](zone: UnitBoardZone[T]): Unit = {
    if (zone.cards.isEmpty) return
    val lastCardName = zone.cards.last.name
    val cardsWithName = zone.cards.filter(_.name == lastCardName)
    if (cardsWithName.size > 1)
      for (card <- cardsWithName) card.applyEffect(_effect)
  }
}
