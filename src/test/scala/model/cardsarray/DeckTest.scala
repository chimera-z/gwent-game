package model.cardsarray

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.card.ability.*
import cl.uchile.dcc.gwent.model.cardsarray.{Deck, Hand}

import scala.collection.mutable.ArrayBuffer

class DeckTest extends munit.FunSuite {
  var deck: Deck = _
  var deck2: Deck = _

  override def beforeEach(context: BeforeEach): Unit = {
    deck = new Deck
    deck2 = new Deck
    deck.addCard(new ClimateCard("Storm", new TorrentialRain))
    deck.addCard(new RangedCard("Mage", 10))
    deck.addCard(new MeleeCard("Gladiator", 2))
    deck.addCard(new SiegeCard("Archer Tower", 15))
  }

  test("Deck can add 25 cards at most") {
    for (i <- 1 to 27) deck.addCard(new ClimateCard("T", new ClearClimate))
    assertEquals(deck.size, 25)
  }

  test("Deck shuffle maintains elements") {
    deck.shuffle()
    val deckArray = deck.getCards
    assert(deckArray.contains(new ClimateCard("Storm", new TorrentialRain)))
    assert(deckArray.contains(new RangedCard("Mage", 10)))
    assert(deckArray.contains(new MeleeCard("Gladiator", 2)))
    assert(deckArray.contains(new SiegeCard("Archer Tower", 15)))
  }

  test("Decks are the same (order sensitive)") {
    deck2.addCard(new ClimateCard("Storm", new TorrentialRain))
    deck2.addCard(new RangedCard("Mage", 10))
    deck2.addCard(new MeleeCard("Gladiator", 2))
    deck2.addCard(new SiegeCard("Archer Tower", 15))
    assert(deck.equals(deck2))
  }

  test("Decks are different") {
    deck2.addCard(new ClimateCard("Storm", new TorrentialRain))
    deck2.addCard(new RangedCard("Mage", 10))
    deck2.addCard(new MeleeCard("Gladiator", 2))
    assertNotEquals(deck, deck2)
  }

  test("Deck is not an array") {
    assert(!deck.equals(ArrayBuffer(new ClimateCard("Storm", new TorrentialRain), new RangedCard("Mage", 10), new MeleeCard("Gladiator", 2)), new SiegeCard("Archer Tower", 15)))
  }

  test("Draw a card from the cards array") {
    val initialLength = deck.size
    val card = deck.drawCard
    assertEquals(deck.size, initialLength - 1)
  }

  test("Draw a card from an empty card array") {
    val deck2 = new Deck
    val thrown = intercept[NoSuchElementException] {
      deck2.drawCard
    }
    assertEquals(thrown.getMessage, "Deck is empty, cannot draw a card")
  }
}
