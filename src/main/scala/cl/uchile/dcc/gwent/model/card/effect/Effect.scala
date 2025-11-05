package cl.uchile.dcc.gwent.model.card.effect

import cl.uchile.dcc.gwent.model.card.unit.UnitCard

trait Effect {
  def apply(card: UnitCard): Unit
}