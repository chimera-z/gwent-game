package model.card.ability

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.ability.CloseBond
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class CloseBondTest extends munit.FunSuite {
  var zone: UnitBoardZone[MeleeCard] = _
  var unitCardWithAbility: MeleeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    zone = new UnitBoardZone[MeleeCard]
    unitCardWithAbility = new MeleeCard("Warrior", 10, new CloseBond)
    zone.addCard(new MeleeCard("Gladiator", 11))
    zone.addCard(new MeleeCard("Warrior", 15))
  }

  test("Ability adds one to strength of all the cards in the zone") {
    zone.addCard(unitCardWithAbility) // placing on board to simulate ability adequately
    unitCardWithAbility.applyAbilityOn(zone)
    assertEquals(zone.cards, ArrayBuffer(new MeleeCard("Gladiator", 11), new MeleeCard("Warrior", 30), new MeleeCard("Warrior", 20, new CloseBond)))
  }
}
