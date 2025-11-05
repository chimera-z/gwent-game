package cl.uchile.dcc.gwent.model.cardsarray

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.Card

/** Represents a player's hand in the game, holding up to 10 cards.
 *
 * This class extends `AbstractCardsArray`, providing a concrete
 * collection of cards with a fixed maximum size of 10. It adds
 * functionality specific to a player's hand, such as drawing cards
 * from a deck.
 *
 * @see [[AbstractCardsArray]]
 */
class Hand extends AbstractCardsArray(max_size = 10) {

  /** Draws a card from the given `Deck` and adds it to this hand.
   *
   * If the hand already contains 10 cards, no card is drawn and `None` is returned.
   * Otherwise, the card is drawn from the deck and added to the hand.
   *
   * @param d the deck to draw a card from
   * @return `Some(card)` if a card was successfully drawn and added, `None` otherwise
   */
  def drawCardFromDeck(d: Deck): Unit = {
    if (size >= _arrayOfCardsMaxSize) {
      throw new IllegalStateException(s"Hand already has maximum number of cards ${size}")
    }
    else {
      val card = d.drawCard
      addCard(card)
    }
  }

  def getCard(index: Int): Card = {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(s"Index $index is out of bounds for hand of size ${_arrayOfCards.length}")
    }
    _arrayOfCards(index)
  }

  def removeCard(card: Card): Unit = {
    if (!_arrayOfCards.contains(card)) throw new NoSuchElementException(s"The card ${card} is not present in the hand")
    _arrayOfCards -= card
  }
}
