package cl.uchile.dcc.gwent.model.card.ability

import cl.uchile.dcc.gwent.model.board.Board

trait ClimateCardAbility extends Ability {
  def apply(board: Board): Unit
}