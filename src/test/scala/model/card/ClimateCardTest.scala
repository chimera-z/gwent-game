package model.card

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.card.ability.*

import scala.collection.mutable.ArrayBuffer


class ClimateCardTest extends munit.FunSuite {
  var testClimateCard: ClimateCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testClimateCard = new ClimateCard("Storm", new TorrentialRain)
  }

  test("Check climate card constructor") {
    assertEquals(testClimateCard.name, "Storm")
  }

  test("Two climate cards are the same") {
    assertEquals(testClimateCard, new ClimateCard("Storm", new TorrentialRain))
  }

  test("Two climate cards are different") {
    assertNotEquals(testClimateCard, new ClimateCard("Rain", new TorrentialRain))
  }

  test("A climate card is not a unit card") {
    assert(testClimateCard != new RangedCard("Mage", 100))
  }

  test("Climate card is not a string") {
    assert(!testClimateCard.equals("Storm"))
  }

  test("Climate card is moved to board") {
    val board = new Board
    val section = board.firstSection
    testClimateCard.moveTo(section)
    assertEquals(section.climateZone, ArrayBuffer(testClimateCard))
  }
}
