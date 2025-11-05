package cl.uchile.dcc.gwent.model.card.unit

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.ability.{NoAbility, UnitCardAbility}

/** A type of unit card representing ranged units in the Gwent game.
 *
 * Ranged cards are played on the ranged row of the battlefield.
 * They contribute their strength value to the player's score in that row.
 *
 * @param name The name of the ranged card.
 * @param strength The strength of the ranged card.
 */
class RangedCard(name: String, strength: Int, ability: UnitCardAbility = new NoAbility) extends AbstractUnitCard(name, strength, ability) {
  override def moveTo(b: BoardSection): Unit = {
    b.placeRangedCard(this)
  }
}
