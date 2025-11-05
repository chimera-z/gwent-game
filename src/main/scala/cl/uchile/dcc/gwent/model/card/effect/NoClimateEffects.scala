package cl.uchile.dcc.gwent.model.card.effect

import cl.uchile.dcc.gwent.model.card.unit.UnitCard

class NoClimateEffects extends AbstractEffect {
  override def apply(card: UnitCard): Unit =
    card.removeEffect(new StrengthIsOne)
}