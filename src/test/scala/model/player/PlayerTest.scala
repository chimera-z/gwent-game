package model.player

import cl.uchile.dcc.gwent.model.board.{Board, BoardSection}
import cl.uchile.dcc.gwent.model.card.{Card, ClimateCard}
import cl.uchile.dcc.gwent.model.card.ability.*
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard}
import cl.uchile.dcc.gwent.model.player.Player
import cl.uchile.dcc.gwent.model.cardsarray.{Deck, Hand}
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class PlayerTest extends munit.FunSuite {
  var player: Player = _
  var deck: Deck = _
  var board: Board = _
  var boardSection: BoardSection = _

  override def beforeEach(context: BeforeEach): Unit = {
    deck = new Deck
    deck.addCard(new ClimateCard("Tormenta", new TorrentialRain))
    deck.addCard(new RangedCard("Mage", 20))
    board = new Board
    boardSection = board.firstSection
    player = new Player(
      _name = "TestPlayer",
      _boardSection = boardSection,
      _deck = deck)
  }

  test("Constructor works for default") {
    assertEquals(player.name, "TestPlayer")
    assertEquals(player.gems, 2)
    assert(player.boardSection == boardSection)
    assertEquals(player.hand, ArrayBuffer.empty)
    assertEquals(player.deck, ArrayBuffer(new ClimateCard("Tormenta", new TorrentialRain), new RangedCard("Mage", 20)))
  }

  test("Constructor works for non default"){
    player = new Player(
      _name = "T",
      _boardSection = boardSection,
      _gems = 1,
      _deck = deck
    )
    assertEquals(player.name, "T")
    assertEquals(player.gems, 1)
    assertEquals(player.hand, ArrayBuffer.empty)
    assertEquals(player.boardSection, boardSection)
    assertEquals(player.deck, ArrayBuffer(new ClimateCard("Tormenta", new TorrentialRain), new RangedCard("Mage", 20)))
  }

  test("Deck returned by getter is immutable copy") {
    val d = player.deck
    d.remove(0)
    assertEquals(player.deck.size, 2)
  }

  test("Hand returned by getter is immutable copy") {
    player.robCard()
    val h = player.hand
    h.clear()
    assertEquals(player.hand.size, 1)
  }

  test("Normal case: Drawing a card moves it from deck to hand") {
    player.robCard()
    assertEquals(player.hand.length, 1)
    assertEquals(player.deck.length, 1)
  }

  test("Border case: Can't move cards from empty deck") {
    player.robCard()
    player.robCard()
    val thrown = intercept[NoSuchElementException] {
      player.robCard()
    }
    assertEquals(player.hand.length, 2)
    assertEquals(player.deck.length, 0)
  }

  test("Border case: Can't draw if hand already has 10 cards") {
    for (i <- 1 to 9) {
      deck.addCard(MeleeCard("Dummy", 1))
    }

    player = new Player(
      _name= "TestPlayer",
      _gems= 2,
      _boardSection = boardSection,
      _deck = deck)

    for (i <- 1 to 10) {
      player.robCard()
    }

    val thrown = intercept[IllegalStateException] {
      player.robCard()
    }

    assertEquals(player.hand.size, 10)
    assertEquals(player.deck.size, 1)
  }

  test("Normal case: Shuffle deck keeps same elements") {
    val originalDeck = player.deck.clone()
    player.shuffleDeck()
    assert(player.deck.diff(originalDeck).isEmpty && originalDeck.diff(player.deck).isEmpty)
  }

  test("Normal case: Lose a gem") {
    player.reduceGem()
    assertEquals(player.gems, 1)
  }

  test("Border case: Can't have negative gems") {
    player.reduceGem()
    player.reduceGem()
    player.reduceGem()
    assertEquals(player.gems, 0)
  }

  test("Two players are the same") {
    assertEquals(player, new Player(
      _name = player.name,
      _gems = player.gems,
      _boardSection = boardSection,
      _deck = deck
    ))
  }

  test("Two players are different due to name") {
    assertNotEquals(player, new Player(
      _name = "OTHER",
      _gems = player.gems,
      _boardSection = boardSection,
      _deck = deck
    ))
  }

  test("Two players are different due to gems") {
    assertNotEquals(player, new Player(
      _name = "OTHER",
      _gems = player.gems + 1,
      _boardSection = boardSection,
      _deck = deck
    ))
  }

  test("Two players are different due to deck") {
    val newDeck = new Deck
    newDeck.addCard(new ClimateCard("Other", new ClearClimate))
    assertNotEquals(player, new Player(
      _name = player.name,
      _gems = player.gems,
      _boardSection = boardSection,
      _deck = newDeck
    ))
  }

  test("A player is not of another type (Set)") {
    assert(!player.equals(Set(player.name, player.gems, player.boardSection, player.deck, player.hand)))
  }

  test("Playing a card takes it out from the hand") {
    player.robCard()
    val card = player.hand(0)
    player.playCard(card)
    assert(player.boardSection.climateZone.nonEmpty || player.boardSection.meleeZone.nonEmpty || player.boardSection.rangedZone.nonEmpty || player.boardSection.siegeZone.nonEmpty)
    assert(!player.hand.contains(card))
  }

  test("Can't play a card not present in hand") {
    player.robCard()
    val prevSize = player.hand.size
    val fakeCard = ClimateCard("Fake Card", new Frostbite)
    val thrown = intercept[NoSuchElementException] {
      player.playCard(fakeCard)
    }
    assertEquals(player.hand.size, prevSize)
    assert(!player.boardSection.climateZone.contains(fakeCard))
  }

  test("Play a card by index") {
    player.robCard()
    player.playCard(0)
    assert(player.boardSection.climateZone.nonEmpty || player.boardSection.meleeZone.nonEmpty || player.boardSection.rangedZone.nonEmpty || player.boardSection.siegeZone.nonEmpty)
    assert(player.hand.isEmpty)
  }

  test("Border Case: Play a card by index out of bounds") {
    val thrown = intercept[IndexOutOfBoundsException] {
      player.playCard(100)
    }
    assertEquals(thrown.getMessage, "Index 100 is out of bounds for hand of size 0")
  }
}
