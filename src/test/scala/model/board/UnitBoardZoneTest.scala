package model.board

import cl.uchile.dcc.gwent.model.board.UnitBoardZone
import cl.uchile.dcc.gwent.model.card.unit.MeleeCard

import scala.collection.mutable.ArrayBuffer

class UnitBoardZoneTest extends munit.FunSuite {
  var unitBoardZone: UnitBoardZone[MeleeCard] = _

  override def beforeEach(context: BeforeEach): Unit = {
    unitBoardZone = new UnitBoardZone[MeleeCard]
  }
  
  test("Unit board zone is initialized empty") {
    assertEquals(unitBoardZone.cards, ArrayBuffer())
  }
  
  test("A card is added to the unit zone") {
    val meleeCard = new MeleeCard("Warrior", 11)
    unitBoardZone.addCard(meleeCard)
    assertEquals(unitBoardZone.cards, ArrayBuffer(meleeCard))
  }
  
}
