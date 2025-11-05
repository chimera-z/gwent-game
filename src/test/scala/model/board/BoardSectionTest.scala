package model.board

import cl.uchile.dcc.gwent.model.board.{Board, BoardSection}
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard}

import scala.collection.mutable.ArrayBuffer

class BoardSectionTest extends munit.FunSuite {
  var board: Board = _
  var boardSection: BoardSection = _
  var meleeCard: MeleeCard = _
  var rangedCard: RangedCard = _
  var siegeCard: SiegeCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    board = Board()
    boardSection = board.firstSection
    meleeCard = MeleeCard("Warrior", 10)
    rangedCard = RangedCard("Mage", 10)
    siegeCard = SiegeCard("Cannon", 10)
  }

  test("Playing a melee card positions it correctly in the melee zone") {
    boardSection.placeMeleeCard(meleeCard)
    assertEquals(boardSection.meleeZone, ArrayBuffer(meleeCard))
    assertEquals(boardSection.rangedZone, ArrayBuffer())
    assertEquals(boardSection.siegeZone, ArrayBuffer())
  }

  test("Playing a ranged card positions it correctly in the ranged zone") {
    boardSection.placeRangedCard(rangedCard)
    assertEquals(boardSection.meleeZone, ArrayBuffer())
    assertEquals(boardSection.rangedZone, ArrayBuffer(rangedCard))
    assertEquals(boardSection.siegeZone, ArrayBuffer())
  }

  test("Playing a siege card positions it correctly in the siege zone") {
    boardSection.placeSiegeCard(siegeCard)
    assertEquals(boardSection.meleeZone, ArrayBuffer())
    assertEquals(boardSection.rangedZone, ArrayBuffer())
    assertEquals(boardSection.siegeZone, ArrayBuffer(siegeCard))
  }

  test("A board is not a set of tuples (inequality)") {
    assert(!boardSection.equals(Set(ArrayBuffer(), ArrayBuffer(), ArrayBuffer())))
  }

  test("Two board sections are the same") {
    val otherBoardSection = board.secondSection
    boardSection.placeMeleeCard(meleeCard)
    otherBoardSection.placeMeleeCard(meleeCard)
    assertEquals(boardSection, otherBoardSection)
  }
  
  test("Obtain all unit cards in section") {
    boardSection.placeMeleeCard(meleeCard)
    boardSection.placeRangedCard(rangedCard)
    boardSection.placeSiegeCard(siegeCard)
    assertEquals(boardSection.unitZones, ArrayBuffer(meleeCard, rangedCard, siegeCard))
  }
}
