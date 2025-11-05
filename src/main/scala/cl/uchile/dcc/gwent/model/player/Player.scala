package cl.uchile.dcc.gwent.model.player

import cl.uchile.dcc.gwent.model.board.BoardSection
import cl.uchile.dcc.gwent.model.card.Card
import cl.uchile.dcc.gwent.model.cardsarray.{Deck, Hand}

import scala.collection.mutable.*
import scala.util.Random

/** A class representing a player of the game.
 *
 * @param _name The name of the player.
 * @param _gems Represents the life of the player.
 * @param _deck A deck of cards.
 * @param _hand A hand of cards.
 *
 * @constructor Creates a player with name, gems,
 *              hand and deck.
 *
 * @see [[IPlayer]]
 */
class Player(private val _name: String, private var _gems: Int = 2, private val _boardSection: BoardSection, private var _deck: Deck = new Deck(), private var _hand: Hand = new Hand()) extends IPlayer:

  /** The name of the player. */
  override def name: String = _name

  /** The current number of gems (lives) the player has. */
  override def gems: Int = _gems

  override def boardSection: BoardSection = _boardSection

  /** The player’s deck of cards. */
  override def deck: ArrayBuffer[Card] = _deck.getCards

  /** The player’s hand of cards. */
  override def hand: ArrayBuffer[Card] = _hand.getCards


  def robCard(): Unit = {
    _hand.drawCardFromDeck(_deck)
  }

  def playCard(idx: Int): Unit = {
    val cardToBePlayed: Card = _hand.getCard(idx)
    _hand.removeCard(cardToBePlayed)
    cardToBePlayed.moveTo(boardSection)
  }

  def playCard(card: Card): Unit = {
    _hand.removeCard(card)
    card.moveTo(boardSection)
  }

  /** Randomly shuffles the deck of the player. */
  def shuffleDeck(): Unit = {
    _deck.shuffle()
  }

  /** Reduces the player's gem count by one, if greater than zero.
   *
   * The gem count will never become negative.
   */
  def reduceGem(): Unit =
    if (gems > 0) _gems -= 1

  /** Compares this player with another for equality.
   *
   * Two players are equal if they have the same name, number of gems, deck, and hand.
   */
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Player]) {
      val other = obj.asInstanceOf[Player]
      other.name == _name && other.gems == _gems && other.boardSection == _boardSection && other.deck == _deck.getCards && other.hand == _hand.getCards
    } else false
  }
