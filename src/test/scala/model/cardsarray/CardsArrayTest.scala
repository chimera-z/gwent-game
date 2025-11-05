package model

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{RangedCard, MeleeCard, SiegeCard}
import cl.uchile.dcc.gwent.model.card.ability.*
import cl.uchile.dcc.gwent.model.cardsarray.{Deck, Hand}
import cl.uchile.dcc.gwent.model.cardsarray.CardsArray
import scala.collection.mutable.ArrayBuffer

class CardsArrayTest extends munit.FunSuite {
  var deck: CardsArray = _
  var deck2: CardsArray = _
  var hand: CardsArray = _

  override def beforeEach(context: BeforeEach): Unit = {
    hand = new Hand
    deck = new Deck
    deck2 = new Deck
    deck.addCard(new ClimateCard("Storm", new TorrentialRain))
    deck.addCard(new RangedCard("Mage", 10))
    deck.addCard(new MeleeCard("Gladiator", 2))
    deck.addCard(new SiegeCard("Archer Tower", 15))
  }

  test("Cards array is not empty") {
    assert(!deck.isEmpty)
  }

  test("Cards array is empty") {
    assert(hand.isEmpty)
  }

  test("Add card to cards array") {
    assert(deck2.addCard(new ClimateCard("Storm", new TorrentialRain)))
    assertEquals(deck2.size, 1)
    assert(deck2.getCards.contains(new ClimateCard("Storm", new TorrentialRain)))
  }

  test("A deck is not a Hand") {
    hand.addCard(new ClimateCard("Storm", new TorrentialRain))
    hand.addCard(new RangedCard("Mage", 10))
    hand.addCard(new MeleeCard("Gladiator", 2))
    hand.addCard(new SiegeCard("Archer Tower", 15))
    assert(!deck.equals(hand))
  }

  test("A hand is not a deck") {
    assertNotEquals(hand, deck2)
  }
}
