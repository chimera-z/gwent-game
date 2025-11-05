package model.card.unit

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ability.*

import scala.collection.mutable.ArrayBuffer

class SiegeCardTest extends munit.FunSuite {
  var testSiegeCard: SiegeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testSiegeCard = new SiegeCard("Catapult", 5)
  }

  test("Siege card constructor") {
    assertEquals(testSiegeCard.name, "Catapult")
    assertEquals(testSiegeCard.strength, 5)
  }

  test("Two siege cards are the same") {
    assertEquals(testSiegeCard, new SiegeCard(testSiegeCard.name, testSiegeCard.strength))
  }

  test("Two siege cards are different") {
    assertNotEquals(testSiegeCard, new SiegeCard("Cannon", testSiegeCard.strength))
    assertNotEquals(testSiegeCard, new SiegeCard(testSiegeCard.name, 10))
  }

  test("A siege card is not melee") {
    assert(testSiegeCard != new MeleeCard(testSiegeCard.name, testSiegeCard.strength))
  }

  test("A siege card is not ranged") {
    assert(testSiegeCard != new RangedCard(testSiegeCard.name, testSiegeCard.strength))
  }

  test("A siege card is not a climate card") {
    assert(testSiegeCard != new ClimateCard(testSiegeCard.name, new ClearClimate))
  }

  test("A siege card is not other type (Set)") {
    assert(!testSiegeCard.equals(Set(testSiegeCard.name, testSiegeCard.strength)))
  }

  test("Siege card is played (placed) on the siege zone of the board section") {
    val board = new Board
    val fBoardSection = board.firstSection
    testSiegeCard.moveTo(fBoardSection)
    assertEquals(fBoardSection.siegeZone, ArrayBuffer(testSiegeCard))
    assertEquals(board.secondSection.siegeZone, ArrayBuffer())
  }
}
