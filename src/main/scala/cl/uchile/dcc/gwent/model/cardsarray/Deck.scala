package cl.uchile.dcc.gwent.model.cardsarray

import cl.uchile.dcc.gwent.model.card.Card
import scala.util.Random

/** Represents a deck of cards in the game, holding up to 25 cards.
 *
 * This class extends `AbstractCardsArray`, providing a concrete collection
 * of cards with a fixed maximum size of 25. It adds deck-specific functionality
 * such as shuffling and drawing cards.
 *
 *  * @see [[AbstractCardsArray]]
 */
class Deck extends AbstractCardsArray(max_size = 25) {
  /** Shuffles the cards in the deck randomly.
   *
   * This modifies the internal order of cards in the deck.
   */
  def shuffle(): Unit = {
    _arrayOfCards = Random.shuffle(_arrayOfCards)
  }

  /** Draws a card from the top of the deck.
   *
   * If the deck is empty, prints a message and returns `None`.
   * Otherwise, removes and returns the first card in the deck.
   *
   * @return `Some(card)` if a card was successfully drawn, `None` if the deck is empty
   */
  def drawCard: Card = {
    // If the list is empty, return None
    if isEmpty then {
      throw new NoSuchElementException("Deck is empty, cannot draw a card")
    } else
      // Return the first card in the list
      _arrayOfCards.remove(0)
  }
}
