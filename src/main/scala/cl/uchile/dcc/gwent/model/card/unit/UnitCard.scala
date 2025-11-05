package cl.uchile.dcc.gwent.model.card.unit

import cl.uchile.dcc.gwent.model.board.{BoardSection, UnitBoardZone}
import cl.uchile.dcc.gwent.model.card.Card
import cl.uchile.dcc.gwent.model.card.effect.Effect

import scala.collection.mutable.ArrayBuffer

/** A trait representing a unit card in the Gwent game.
 *
 * Unit cards are the main cards played onto the battlefield.
 * Unlike other types of cards, unit cards have a strength value
 * in addition to their name.
 */
trait UnitCard extends Card {
  def strength: Int
  def effects: ArrayBuffer[Effect]
  def setStrength(newStrength: Int): Unit
  def applyAbilityOn[T <: UnitCard](unitBoardZone: UnitBoardZone[T]): Unit
  def resetStrength(): Unit
  def applyEffect(effect: Effect): Unit
  def clearEffects(): Unit
  def removeEffect(effect: Effect): Unit
}
