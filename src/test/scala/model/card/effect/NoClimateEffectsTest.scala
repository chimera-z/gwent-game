package model.card.effect

import cl.uchile.dcc.gwent.model.card.effect.{Effect, NoClimateEffects, StrengthIsOne, StrengthPlusOne}
import cl.uchile.dcc.gwent.model.card.unit.{SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class NoClimateEffectsTest extends munit.FunSuite {
  var effect: Effect = _
  var testUnitCard: UnitCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUnitCard = new SiegeCard("Cannon", 5)
    effect = new NoClimateEffects
  }

  test("The effect removes climate effects (StrengthIsOne)") {
    val initialStrength = testUnitCard.strength
    testUnitCard.applyEffect(new StrengthIsOne)
    effect.apply(testUnitCard)
    assertEquals(testUnitCard.strength, initialStrength)
  }


  test("The effect removes only climate effects (StrengthIsOne)") {
    val initialStrength = testUnitCard.strength
    testUnitCard.applyEffect(new StrengthIsOne)
    testUnitCard.applyEffect(new StrengthPlusOne)
    effect.apply(testUnitCard)
    assertEquals(testUnitCard.strength, initialStrength + 1)
  }

}
