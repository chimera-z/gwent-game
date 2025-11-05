package model.board

import cl.uchile.dcc.gwent.model.board.ClimateBoardZone
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.ability.TorrentialRain
import cl.uchile.dcc.gwent.model.card.unit.MeleeCard

import scala.collection.mutable.ArrayBuffer

class ClimateBoardZoneTest extends munit.FunSuite {
  var climateBoardZone: ClimateBoardZone = _

  override def beforeEach(context: BeforeEach): Unit = {
    climateBoardZone = new ClimateBoardZone
  }

  test("Zone is initially empty") {
    assertEquals(climateBoardZone.cards, ArrayBuffer())
  }

  test("A card can be added to a climate zone") {
    climateBoardZone.addCard(new ClimateCard("Rain", new TorrentialRain))
    assertEquals(climateBoardZone.cards, ArrayBuffer(new ClimateCard("Rain", new TorrentialRain)))
  }

  test("The climate zone can hold at most one climate card (the last one added)") {
    climateBoardZone.addCard(new ClimateCard("Rain", new TorrentialRain))
    climateBoardZone.addCard(new ClimateCard("Rain2", new TorrentialRain))
    assertEquals(climateBoardZone.cards, ArrayBuffer(new ClimateCard("Rain2", new TorrentialRain)))
  }
}
