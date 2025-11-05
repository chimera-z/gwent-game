package model.board

import cl.uchile.dcc.gwent.model.board.{Board, BoardSection}
import cl.uchile.dcc.gwent.model.card.{Card, ClimateCard}
import cl.uchile.dcc.gwent.model.card.ability.*
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class BoardTest extends munit.FunSuite {
  var board: Board = _
  var initialFirstSectionMeleeZone: ArrayBuffer[MeleeCard] = _
  var initialSecondSectionMeleeZone: ArrayBuffer[MeleeCard] = _
  var initialFirstSectionRangedZone: ArrayBuffer[RangedCard] = _
  var initialSecondSectionRangedZone: ArrayBuffer[RangedCard] = _
  var initialFirstSectionSiegeZone: ArrayBuffer[SiegeCard] = _
  var initialSecondSectionSiegeZone: ArrayBuffer[SiegeCard] = _

  override def beforeEach(context: BeforeEach): Unit = {
    board = new Board

    board.firstSection.placeMeleeCard(new MeleeCard("Warrior", 10))
    board.secondSection.placeMeleeCard(new MeleeCard("Gladiator", 11))

    board.firstSection.placeRangedCard(new RangedCard("Mage", 12))
    board.secondSection.placeRangedCard(new RangedCard("Wizard", 13))

    board.firstSection.placeSiegeCard(new SiegeCard("Cannon", 14))
    board.secondSection.placeSiegeCard(new SiegeCard("Archer Tower", 15))

    initialFirstSectionMeleeZone = board.firstSection.meleeZone
    initialSecondSectionMeleeZone = board.secondSection.meleeZone
    initialFirstSectionRangedZone = board.firstSection.rangedZone
    initialSecondSectionRangedZone = board.secondSection.rangedZone
    initialFirstSectionSiegeZone = board.firstSection.siegeZone
    initialSecondSectionSiegeZone = board.secondSection.siegeZone

  }

  test("A board has two section and a climate zone (initially empty)") {
    board = new Board
    assert(board.firstSection.meleeZone.isEmpty)
    assert(board.secondSection.meleeZone.isEmpty)
    assert(board.firstSection.rangedZone.isEmpty)
    assert(board.secondSection.rangedZone.isEmpty)
    assert(board.firstSection.siegeZone.isEmpty)
    assert(board.secondSection.siegeZone.isEmpty)
    assert(board.climateZone.isEmpty)
  }

  test("Playing a climate card positions it correctly in the board") {
    val climateCard = new ClimateCard("Hurricane", new TorrentialRain)
    board.placeClimateCard(climateCard)
    assertEquals(board.climateZone, ArrayBuffer(climateCard))
    assertEquals(board.firstSection.meleeZone, initialFirstSectionMeleeZone)
    assertEquals(board.secondSection.meleeZone, initialSecondSectionMeleeZone)
    assertEquals(board.firstSection.rangedZone, initialFirstSectionRangedZone)
    assertEquals(board.secondSection.rangedZone, initialSecondSectionRangedZone)
    assertEquals(board.firstSection.siegeZone, initialFirstSectionSiegeZone)
    assertEquals(board.secondSection.siegeZone, initialSecondSectionSiegeZone)
  }

  test("Get all unit cards on board (no climate cards)") {
    val climateCard = new ClimateCard("Hurricane", new TorrentialRain)
    board.placeClimateCard(climateCard) // not included in unit cards
    // cards on board considering effect
    val boardCards: ArrayBuffer[UnitCard] = ArrayBuffer(new MeleeCard("Warrior", 10), new RangedCard("Mage", 12), new SiegeCard("Cannon", 1), new MeleeCard("Gladiator", 11), new RangedCard("Wizard", 13), new SiegeCard("Archer Tower", 1))
    assertEquals(board.unitZones, boardCards)
  }

  test("Get all melee cards on board") {
    assertEquals(board.meleeZones, ArrayBuffer(new MeleeCard("Warrior", 10), new MeleeCard("Gladiator", 11)))
  }

  test("Get all ranged cards on board") {
    assertEquals(board.rangedZones, ArrayBuffer(new RangedCard("Mage", 12), new RangedCard("Wizard", 13)))
  }

  test("Get all siege cards on board") {
    assertEquals(board.siegeZones, ArrayBuffer(new SiegeCard("Cannon", 14), new SiegeCard("Archer Tower", 15)))
  }

  test("Placing a new climate card removes effect of previous climate card") {
    board.placeClimateCard(new ClimateCard("Rainy", new TorrentialRain)) // sets siege cards strength to 1
    // sets ranged cards strength to 1, removing prev effect from siege cards
    board.placeClimateCard(new ClimateCard("Foggy", new ImpenetrableFog))
    assertEquals(board.meleeZones, ArrayBuffer(new MeleeCard("Warrior", 10), new MeleeCard("Gladiator", 11)))
    assertEquals(board.rangedZones, ArrayBuffer(new RangedCard("Mage", 1), new RangedCard("Wizard", 1)))
    assertEquals(board.siegeZones, ArrayBuffer(new SiegeCard("Cannon", 14), new SiegeCard("Archer Tower", 15)))
    assertEquals(board.climateZone, ArrayBuffer(new ClimateCard("Foggy", new ImpenetrableFog)))
  }
}
