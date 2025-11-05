package model.card.effect

import cl.uchile.dcc.gwent.model.card.ability.Frostbite
import cl.uchile.dcc.gwent.model.card.effect.{StrengthIsOne, StrengthPlusOne}

class AbstractEffectTest extends munit.FunSuite {
  test("Two effects are the same") {
    val effect1 = new StrengthIsOne
    val effect2 = new StrengthIsOne
    assertEquals(effect1, effect2)
  }

  test("Two effects are different") {
    val effect1 = new StrengthIsOne
    val effect2 = new StrengthPlusOneTest
    assert(!effect1.equals(effect2))
  }

  test("An effect is not an ability (another type)") {
    val effect = new StrengthIsOne
    val ability = new Frostbite
    assert(!effect.equals(ability))
  }

}
