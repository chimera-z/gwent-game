package model.card.effect

import cl.uchile.dcc.gwent.model.card.effect.{Effect, StrengthIsOne}
import cl.uchile.dcc.gwent.model.card.unit.{SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class StrengthIsOneTest extends munit.FunSuite {
  var effect: Effect = _
  var testUnitCard: UnitCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUnitCard = new SiegeCard("Cannon", 5)
    effect = new StrengthIsOne
  }

  test("The effect sets card strength to one") {
    effect.apply(testUnitCard)
    assertEquals(testUnitCard.strength, 1)
  }

}
