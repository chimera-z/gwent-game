package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.effect.{Effect, StrengthIsOne}

class TorrentialRain extends ClimateCardAbility {
  private val _effect = new StrengthIsOne

  override def apply(board: Board): Unit = {
    for (card <- board.siegeZones) card.applyEffect(_effect)
  }
}
