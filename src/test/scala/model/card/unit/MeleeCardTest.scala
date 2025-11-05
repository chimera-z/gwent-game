package model.card.unit

import cl.uchile.dcc.gwent.model.board.Board
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}
import cl.uchile.dcc.gwent.model.card.ability.*


import scala.collection.mutable.ArrayBuffer

class MeleeCardTest extends munit.FunSuite {
  var testMeleeCard: MeleeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testMeleeCard = new MeleeCard("Warrior", 100)
  }
  test("Melee card constructor") {
    assertEquals(testMeleeCard.name, "Warrior")
    assertEquals(testMeleeCard.strength, 100)
  }

  test("Two melee cards are the same") {
    assertEquals(testMeleeCard, new MeleeCard(testMeleeCard.name, testMeleeCard.strength))
  }

  test("Two melee cards are different") {
    assertNotEquals(testMeleeCard, new MeleeCard("Gladiator", testMeleeCard.strength))
    assertNotEquals(testMeleeCard, new MeleeCard(testMeleeCard.name, 10))
  }

  test("A melee card is not ranged") {
    assert(testMeleeCard != new RangedCard(testMeleeCard.name, testMeleeCard.strength))
  }

  test("A melee card is not siege") {
    assert(testMeleeCard != new SiegeCard(testMeleeCard.name, testMeleeCard.strength))
  }

  test("A melee card is not a climate card") {
    assert(testMeleeCard != new ClimateCard(testMeleeCard.name, new Frostbite))
  }

  test("A melee card is not other type (Set)") {
    assert(!testMeleeCard.equals(Set(testMeleeCard.name, testMeleeCard.strength)))
  }

  test("Melee card is played (placed) on the melee zone of the board section") {
    val board = new Board
    val fBoardSection = board.firstSection
    testMeleeCard.moveTo(fBoardSection)
    assertEquals(fBoardSection.meleeZone, ArrayBuffer(testMeleeCard))
    assertEquals(board.secondSection.meleeZone, ArrayBuffer())
  }
}
