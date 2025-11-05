package cl.uchile.dcc.gwent.model.card.unit

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.ability.{NoAbility, UnitCardAbility}

/** A type of unit card representing siege units in the Gwent game.
 *
 * Siege cards are played on the siege row of the battlefield.
 * They contribute their strength value to the player's score in that row.
 *
 * @param name The name of the siege card.
 * @param strength The strength of the siege card.
 */
class SiegeCard(name: String, strength: Int, ability: UnitCardAbility = new NoAbility) extends AbstractUnitCard(name, strength, ability) {
  override def moveTo(b: BoardSection): Unit = {
    b.placeSiegeCard(this)
  }
}
