package cl.uchile.dcc.gwent.model.card.effect

import cl.uchile.dcc.gwent.model.card.unit.UnitCard

class StrengthPlusOne extends AbstractEffect {
  override def apply(card: UnitCard): Unit = card.setStrength(card.strength + 1)
}
