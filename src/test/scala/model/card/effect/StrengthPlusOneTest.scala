package model.card.effect

import cl.uchile.dcc.gwent.model.card.effect.{Effect, StrengthPlusOne}
import cl.uchile.dcc.gwent.model.card.unit.{SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class StrengthPlusOneTest extends munit.FunSuite {
  var effect: Effect = _
  var testUnitCard: UnitCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUnitCard = new SiegeCard("Cannon", 5)
    effect = new StrengthPlusOne
  }

  test("The effect sets card strength to one") {
    val initialStrength = testUnitCard.strength
    effect.apply(testUnitCard)
    assertEquals(testUnitCard.strength, initialStrength + 1)
  }

}
