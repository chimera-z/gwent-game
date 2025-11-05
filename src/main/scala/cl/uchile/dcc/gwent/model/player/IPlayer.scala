package cl.uchile.dcc.gwent.model.player

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.Card

import scala.collection.mutable.*

/** A trait representing a player.
 *
 * It defines a common interface for all players, in order
 * to enforce the possession of a name, number of gems,
 * hand of cards and deck of cards. Additionally, all
 * players must be able to rob a card from the deck to the
 * hand, shuffle the deck and reduce the amount of gems.
 */
trait IPlayer {
  def name: String
  def gems: Int
  def boardSection: BoardSection
  def hand: ArrayBuffer[Card]
  def deck: ArrayBuffer[Card]
  def robCard(): Unit
  def playCard(c: Card): Unit
  def playCard(idx: Int): Unit
  def shuffleDeck(): Unit
  def reduceGem(): Unit
}
