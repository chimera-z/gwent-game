package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.effect.StrengthIsOne

class ClearClimate extends ClimateCardAbility {
  override def apply(board: Board): Unit = {
    for (card <- board.unitZones) card.removeEffect(new StrengthIsOne)
  }
}
