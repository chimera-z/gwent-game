package model.card.unit

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ability.*

import scala.collection.mutable.ArrayBuffer

class RangedCardTest extends munit.FunSuite {
  var testRangedCard: RangedCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testRangedCard = new RangedCard("Mage", 100)
  }

  test("Ranged card constructor") {
    assertEquals(testRangedCard.name, "Mage")
    assertEquals(testRangedCard.strength, 100)
  }

  test("Two ranged cards are the same") {
    assertEquals(testRangedCard, new RangedCard(testRangedCard.name, testRangedCard.strength))
  }

  test("Two ranged cards are different") {
    assertNotEquals(testRangedCard, new RangedCard("Archer", testRangedCard.strength))
    assertNotEquals(testRangedCard, new RangedCard(testRangedCard.name, 10))
  }

  test("A ranged card is not melee") {
    assert(testRangedCard != new MeleeCard(testRangedCard.name, testRangedCard.strength))
  }

  test("A ranged card is not siege") {
    assert(testRangedCard != new SiegeCard(testRangedCard.name, testRangedCard.strength))
  }

  test("A ranged card is not a climate card") {
    assert(testRangedCard != new ClimateCard(testRangedCard.name, new Frostbite))
  }

  test("A ranged card is not other type (Set)") {
    assert(!testRangedCard.equals(Set(testRangedCard.name, testRangedCard.strength)))
  }

  test("Ranged card is played (placed) on the ranged zone of the board section") {
    val board = new Board
    val fBoardSection = board.firstSection
    testRangedCard.moveTo(fBoardSection)
    assertEquals(fBoardSection.rangedZone, ArrayBuffer(testRangedCard))
    assertEquals(board.secondSection.rangedZone, ArrayBuffer())
  }
}
