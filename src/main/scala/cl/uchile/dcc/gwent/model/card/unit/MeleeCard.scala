package cl.uchile.dcc.gwent.model.card.unit

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.ability.{NoAbility, UnitCardAbility}

/** A type of unit card representing melee units in the Gwent game.
 *
 * Melee cards are played on the close combat row of the battlefield.
 *
 * @param name The name of the melee card.
 * @param strength The strength of the melee card.
 */
class MeleeCard(name: String, strength: Int, ability: UnitCardAbility = new NoAbility) extends AbstractUnitCard(name, strength, ability) {
  override def moveTo(b: BoardSection): Unit = {
    b.placeMeleeCard(this)
  }
}
