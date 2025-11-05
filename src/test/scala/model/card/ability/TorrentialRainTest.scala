package model.card.ability

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.ability.TorrentialRain
import cl.uchile.dcc.gwent.model.card.effect.StrengthIsOne
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}

import scala.collection.mutable.ArrayBuffer

class TorrentialRainTest extends munit.FunSuite {
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

    climateCardWithAbility = ClimateCard("Rain", new TorrentialRain)
  }

  test("Ability sets the strength of all siege cards to 1") {
    climateCardWithAbility.applyAbilityOn(board)
    assertEquals(board.siegeZones, ArrayBuffer(new SiegeCard("Cannon", 1), new SiegeCard("Archer Tower", 1)))
    for (card <- board.siegeZones) assertEquals(card.effects, ArrayBuffer(new StrengthIsOne))
  }
}
