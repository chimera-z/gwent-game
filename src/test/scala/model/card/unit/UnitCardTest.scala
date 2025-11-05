package model.card.unit

import cl.uchile.dcc.gwent.model.card.effect.{DoubleStrength, StrengthIsOne, StrengthPlusOne}
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class UnitCardTest extends munit.FunSuite {
  var testUnitCard: UnitCard = new MeleeCard("Warrior", 100)

  override def beforeEach(context: BeforeEach): Unit = {
    testUnitCard = new MeleeCard("Warrior", 100)
  }

  test("A unit card no effects initially") {
    assertEquals(testUnitCard.effects, ArrayBuffer())
  }

  test("A unit card can lose strength") {
    testUnitCard.setStrength(10)
    assertEquals(testUnitCard.strength, 10)
  }

  test("A unit card can gain strength") {
    testUnitCard.setStrength(1000)
    assertEquals(testUnitCard.strength, 1000)
  }

  test("A unit card cannot have negative strength") {
    testUnitCard.setStrength(-1)
    // No change in strength when invalid input
    assertEquals(testUnitCard.strength, 100)
  }

  test("A unit card can reset to initial strength after changes to actual strength") {
    val initialStrength = testUnitCard.strength
    testUnitCard.setStrength(initialStrength*2)
    testUnitCard.resetStrength()
    assertEquals(testUnitCard.strength, initialStrength)
  }

  test("An effect can be applied to a unit card") {
    val effect = new StrengthIsOne
    testUnitCard.applyEffect(effect)
    assertEquals(testUnitCard.strength, 1)
  }

  test("An effect can be removed from a card with a single effect") {
    val initialStrength = testUnitCard.strength
    val effect = new StrengthIsOne
    testUnitCard.applyEffect(effect)
    testUnitCard.removeEffect(effect)
    assertEquals(testUnitCard.strength, initialStrength)
  }

  test("An effect can be removed from a card maintaining other effects (applied in order)") {
    val initialStrength = testUnitCard.strength
    testUnitCard.applyEffect(new StrengthPlusOne) // strength == initialStrength + 1 [1]
    testUnitCard.applyEffect(new StrengthIsOne) // strength == 1 [2]
    testUnitCard.applyEffect(new DoubleStrength) // strength == 2 [3]
    testUnitCard.removeEffect(new StrengthIsOne) // strength == 2 * (initialStrength + 1) [1 y 3]
    assertEquals(testUnitCard.strength, 2 * (initialStrength + 1))
  }

  test("All effects of a card can be removed") {
    val initialStrength = testUnitCard.strength
    testUnitCard.applyEffect(new StrengthPlusOne)
    testUnitCard.applyEffect(new DoubleStrength)
    testUnitCard.applyEffect(new StrengthPlusOne)
    testUnitCard.clearEffects()
    assertEquals(testUnitCard.strength, initialStrength)
  }

}
