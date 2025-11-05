package cl.uchile.dcc.gwent.model.card.unit

import cl.uchile.dcc.gwent.model.board.{BoardSection, UnitBoardZone}
import cl.uchile.dcc.gwent.model.card.AbstractCard
import cl.uchile.dcc.gwent.model.card.ability.{NoAbility, UnitCardAbility}
import cl.uchile.dcc.gwent.model.card.effect.Effect

import scala.collection.mutable.ArrayBuffer

/** An abstract base class for unit cards in the Gwent game.
 *
 * Provides a basic implementation of the [[UnitCard]] trait, storing
 * both the card's name and its strength.
 *
 * @param name The name of the unit card.
 * @param _strength The strength of the unit card.
 */
abstract class AbstractUnitCard(name: String, private var _strength: Int, private var _ability: UnitCardAbility) extends AbstractCard(name, _ability) with UnitCard {

  private val initialStrength: Int = _strength
  private var _effects: ArrayBuffer[Effect] = ArrayBuffer()

  /** Returns the current strength of the unit card.
   *
   * @return The strength value of the card.
   */
  def strength: Int = _strength
  
  def effects: ArrayBuffer[Effect] = _effects.clone()

  def applyAbilityOn[T <: UnitCard](unitBoardZone: UnitBoardZone[T]): Unit = {
    _ability.apply(unitBoardZone)
  }

  def setStrength(newStrength: Int): Unit = {
    if (newStrength >= 0) _strength = newStrength
  }

  def resetStrength(): Unit = _strength = initialStrength
  
  def applyEffect(effect: Effect): Unit = {
    effect.apply(this)
    _effects.addOne(effect)
  }

  def clearEffects(): Unit = {
    _effects.clear()
    resetStrength()
  }

  def removeEffect(effect: Effect): Unit = {
    _effects = _effects.filter(_ != effect)
    resetStrength()
    for (effect <- _effects) {
      effect.apply(this)
    }
  }

  /** Compares this card with another object for equality.
   *
   * Two [[UnitCard]]s are considered equal if:
   *  - They have the same name.
   *  - They have the same strength.
   *  - They are of the same runtime class.
   *
   * @param obj The object to compare with.
   * @return `true` if the cards are equal, `false` otherwise.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[UnitCard]) {
      val other = obj.asInstanceOf[UnitCard]
      _strength == other.strength && _name == other.name && other.getClass == this.getClass && ability.getClass == other.ability.getClass
    } else false
  }
}
