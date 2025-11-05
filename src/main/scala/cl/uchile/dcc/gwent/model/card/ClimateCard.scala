package cl.uchile.dcc.gwent.model.card

import cl.uchile.dcc.gwent.model.board.{Board, BoardSection}
import cl.uchile.dcc.gwent.model.card.ability.ClimateCardAbility

/** A type of card that represents a climate effect in the Gwent game.
 *
 * Climate cards apply weather effects that impact the battlefield.
 * They are identified uniquely by their name.
 *
 * @param name The name of the climate card.
 */
class ClimateCard(name: String, private val _ability: ClimateCardAbility) extends AbstractCard(name, _ability) {

  override def moveTo(boardSection: BoardSection): Unit = {
    boardSection.placeClimateCard(this)
  }

  def applyAbilityOn(board: Board): Unit = {
    _ability.apply(board)
  }

  /** Compares this card with another object for equality.
   *
   * Two [[ClimateCard]]s are considered equal if they have the same name.
   *
   * @param obj The object to compare with.
   * @return `true` if the other object is a [[ClimateCard]] with the same name,
   *         `false` otherwise.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[ClimateCard]) {
      val other = obj.asInstanceOf[ClimateCard]
      _name == other.name && ability.getClass == other.ability.getClass
    } else false
  }
}
