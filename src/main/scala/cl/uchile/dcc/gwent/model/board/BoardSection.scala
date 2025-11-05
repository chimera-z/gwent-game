package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

class BoardSection(private val board: Board) extends IBoardSection {
  private val _meleeZone = new UnitBoardZone[MeleeCard]
  private val _rangedZone = new UnitBoardZone[RangedCard]
  private val _siegeZone = new UnitBoardZone[SiegeCard]

  def meleeZone: ArrayBuffer[MeleeCard] = _meleeZone.cards
  def rangedZone: ArrayBuffer[RangedCard] = _rangedZone.cards
  def siegeZone: ArrayBuffer[SiegeCard] = _siegeZone.cards
  def climateZone: ArrayBuffer[ClimateCard] = board.climateZone
  def unitZones: ArrayBuffer[UnitCard] = meleeZone ++ rangedZone ++ siegeZone

  private def placeCard[T <: UnitCard](card: T, unitBoardZone: UnitBoardZone[T]): Unit = {
    unitBoardZone.addCard(card)
    card.applyAbilityOn(unitBoardZone)
  }

  def placeMeleeCard(meleeCard: MeleeCard): Unit = placeCard(meleeCard, _meleeZone)
  def placeRangedCard(rangedCard: RangedCard): Unit = placeCard(rangedCard, _rangedZone)
  def placeSiegeCard(siegeCard: SiegeCard): Unit = placeCard(siegeCard, _siegeZone)
  def placeClimateCard(climateCard: ClimateCard): Unit = board.placeClimateCard(climateCard)

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[BoardSection]) {
      var other = obj.asInstanceOf[BoardSection]
      meleeZone == other.meleeZone && rangedZone == other.rangedZone && siegeZone == other.siegeZone
    } else false
  }
}
