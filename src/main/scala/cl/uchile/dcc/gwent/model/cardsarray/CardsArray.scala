package cl.uchile.dcc.gwent.model.cardsarray

import cl.uchile.dcc.gwent.model.card.Card

import scala.collection.mutable.ArrayBuffer

/** Trait representing a collection of `Card` objects.
 *
 * This trait defines a common interface for classes that manage an array-like
 * structure of `Card` objects, providing basic operations such as retrieving
 * the cards, checking if the collection is empty, getting its size, and
 * adding new cards.
 */
trait CardsArray {

  /** Returns the underlying collection of cards as an `ArrayBuffer[Card]`.
   *
   * @return the mutable array buffer clone containing all cards
   */
  def getCards: ArrayBuffer[Card]

  /** Checks whether the collection of cards is empty.
   *
   * @return `true` if there are no cards in the collection, `false` otherwise
   */
  def isEmpty: Boolean

  /** Returns the number of cards currently in the collection.
   *
   * @return the size of the card collection
   */
  def size: Int

  /** Adds a card to the collection.
   *
   * @param c the `Card` to add
   * @return `true` if the card was successfully added, `false` otherwise
   */
  def addCard(c: Card): Boolean
}
