package model.card.effect

import cl.uchile.dcc.gwent.model.card.effect.{DoubleStrength, Effect}
import cl.uchile.dcc.gwent.model.card.unit.{SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class DoubleStrengthTest extends munit.FunSuite {
  var effect: Effect = _
  var testUnitCard: UnitCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUnitCard = new SiegeCard("Cannon", 5)
    effect = new DoubleStrength
  }

  test("The effect doubles the cards strength") {
    val initialStrength = testUnitCard.strength
    effect.apply(testUnitCard)
    assertEquals(testUnitCard.strength, initialStrength * 2)
  }

}
