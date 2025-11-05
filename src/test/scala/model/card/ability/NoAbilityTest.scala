package model.card.ability

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.ability.NoAbility
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class NoAbilityTest extends munit.FunSuite {
  var zone: UnitBoardZone[MeleeCard] = _
  var unitCardWithAbility: MeleeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    zone = new UnitBoardZone[MeleeCard]
    unitCardWithAbility = new MeleeCard("Warrior", 10, new NoAbility)
    zone.addCard(new MeleeCard("Gladiator", 11))
    zone.addCard(new MeleeCard("Warrior", 10))
  }

  test("Ability adds one to strength of all the cards in the zone") {
    zone.addCard(unitCardWithAbility) // simulating placing on board
    unitCardWithAbility.applyAbilityOn(zone)
    assertEquals(zone.cards, ArrayBuffer(new MeleeCard("Gladiator", 11), new MeleeCard("Warrior", 10), unitCardWithAbility))
  }
}
