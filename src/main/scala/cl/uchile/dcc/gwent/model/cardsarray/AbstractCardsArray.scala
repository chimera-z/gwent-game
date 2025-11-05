package cl.uchile.dcc.gwent.model.cardsarray

import cl.uchile.dcc.gwent.model.card.Card

import scala.collection.mutable.ArrayBuffer

/** Abstract class representing a collection of `Card` objects with a maximum size.
 *
 * Provides a concrete implementation of the `CardsArray` trait with a fixed maximum size.
 *
 * @param max_size the maximum number of cards allowed in this collection
 *
 * @see [[CardsArray]]
 */
abstract class AbstractCardsArray(max_size: Int) extends CardsArray {
  protected var _arrayOfCards: ArrayBuffer[Card] = ArrayBuffer()
  protected val _arrayOfCardsMaxSize: Int = max_size

  override def getCards: ArrayBuffer[Card] = _arrayOfCards.clone()

  override def isEmpty: Boolean = _arrayOfCards.isEmpty

  override def size: Int = _arrayOfCards.size

  override def addCard(c: Card): Boolean = {
    // If the deck is full, the card cannot be added
    if (size >= _arrayOfCardsMaxSize) false
    else {
      // Add the card to the deck
      _arrayOfCards.addOne(c)
      true
    }
  }

  /** Compares this collection with another object for equality.
   *
   * Two `CardsArray` objects are considered equal if they contain the same cards
   * in the same order and belong to the same class.
   *
   * @param obj the object to compare with
   * @return `true` if the objects are equal, `false` otherwise
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[CardsArray]) {
      val other = obj.asInstanceOf[CardsArray]
      other.getCards == getCards && this.getClass == other.getClass
    } else false
  }
}
