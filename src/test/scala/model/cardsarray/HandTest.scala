package model.cardsarray

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.Card
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.card.ability.*
import cl.uchile.dcc.gwent.model.cardsarray.{Deck, Hand}

import scala.collection.mutable.ArrayBuffer

class HandTest extends munit.FunSuite {
  var hand: Hand = _
  var hand2: Hand = _
  var deck: Deck = _
  var emptyDeck: Deck = _

  override def beforeEach(context: BeforeEach): Unit = {
    hand = new Hand
    hand2 = new Hand
    hand.addCard(new ClimateCard("Storm", new TorrentialRain))
    hand.addCard(new RangedCard("Mage", 10))
    hand.addCard(new MeleeCard("Gladiator", 2))
    hand.addCard(new SiegeCard("Archer Tower", 15))
    
    emptyDeck = new Deck
    deck = new Deck
    deck.addCard(new ClimateCard("Storm", new TorrentialRain))
  }

  test("Hand can have 10 cards at most") {
    for (i <- 1 to 11) hand.addCard(new ClimateCard("T", new ClearClimate))
    assertEquals(hand.size, 10)
  }

  test("Hands are the same") {
    hand2.addCard(new ClimateCard("Storm", new TorrentialRain))
    hand2.addCard(new RangedCard("Mage", 10))
    hand2.addCard(new MeleeCard("Gladiator", 2))
    hand2.addCard(new SiegeCard("Archer Tower", 15))
    assertEquals(hand, hand2)
  }

  test("Hands are not the same as other type") {
    assert(!hand2.equals(ArrayBuffer()))
  }

  test("Hand can't draw from empty deck") {
    val thrown = intercept[NoSuchElementException] {
      hand2.drawCardFromDeck(emptyDeck)
    }
    assert(hand2.size == 0)
  }

  test("Hand draws from non empty deck") {
    val cardsInDeck = deck.getCards
    hand2.drawCardFromDeck(deck)
    assertEquals(hand2.getCards, cardsInDeck)
    assert(deck.isEmpty)
  }
}
