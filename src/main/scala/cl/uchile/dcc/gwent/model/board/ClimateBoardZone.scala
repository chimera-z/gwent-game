package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.ClimateCard

class ClimateBoardZone extends AbstractBoardZone[ClimateCard] {
  def addCard(card: ClimateCard): Unit = {
    _cards.clear()
    _cards += card
  }
}