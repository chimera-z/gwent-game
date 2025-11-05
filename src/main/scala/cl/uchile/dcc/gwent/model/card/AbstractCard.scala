package cl.uchile.dcc.gwent.model.card

import cl.uchile.dcc.gwent.model.card.ability.Ability

/** An abstract base class for cards in the Gwent game.
 *
 * Provides a basic implementation of the [[Card]] trait by storing 
 * the card's name as a protected field.
 *
 * @param _name The name of the card.
 */
abstract class AbstractCard(protected val _name: String, private val _ability: Ability) extends Card {
  def name: String = _name
  def ability: Ability = _ability
}
