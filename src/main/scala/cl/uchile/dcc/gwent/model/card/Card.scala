package cl.uchile.dcc.gwent.model.card

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.ability.Ability

/** A trait representing a general card in the Gwent game.
 *
 * Every card must have a name, which uniquely identifies it
 * within the context of the game.
 */
trait Card {
  def name: String
  def ability: Ability
  def moveTo(b: BoardSection): Unit
}
