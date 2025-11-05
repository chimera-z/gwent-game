package model.card.ability

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.ability.{ClearClimate, Frostbite, ImpenetrableFog, TorrentialRain}
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}

import scala.collection.mutable.ArrayBuffer

class ClearClimateTest extends munit.FunSuite {
  var board: Board = _
  var climateCardWithAbility: ClimateCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    board = new Board
    board.firstSection.placeMeleeCard(new MeleeCard("Warrior", 10))
    board.secondSection.placeMeleeCard(new MeleeCard("Gladiator", 11))

    board.firstSection.placeRangedCard(new RangedCard("Mage", 12))
    board.secondSection.placeRangedCard(new RangedCard("Wizard", 13))

    board.firstSection.placeSiegeCard(new SiegeCard("Cannon", 14))
    board.secondSection.placeSiegeCard(new SiegeCard("Archer Tower", 15))

    climateCardWithAbility = ClimateCard("Foggy", new ClearClimate)
  }

  test("Ability removes all climate effects from unit cards") {
    val climateCardWithFrostbite = ClimateCard("Snow", new Frostbite)
    val climateCardWithImpenetrableFog = ClimateCard("Foggy", new ImpenetrableFog)
    val climateCardWithTorrentialRain = ClimateCard("Rainy", new TorrentialRain)
    climateCardWithFrostbite.applyAbilityOn(board)
    climateCardWithImpenetrableFog.applyAbilityOn(board)
    climateCardWithTorrentialRain.applyAbilityOn(board)
    climateCardWithAbility.applyAbilityOn(board)
    assertEquals(board.meleeZones, ArrayBuffer(new MeleeCard("Warrior", 10), new MeleeCard("Gladiator", 11)))
    assertEquals(board.rangedZones, ArrayBuffer(new RangedCard("Mage", 12), new RangedCard("Wizard", 13)))
    assertEquals(board.siegeZones, ArrayBuffer(new SiegeCard("Cannon", 14), new SiegeCard("Archer Tower", 15)))
    for (card <- board.unitZones) assertEquals(card.effects, ArrayBuffer())
  }
}
