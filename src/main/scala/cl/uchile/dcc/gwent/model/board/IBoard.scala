package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}
import cl.uchile.dcc.gwent.model.card.ClimateCard

import scala.collection.mutable.ArrayBuffer

trait IBoard {
  def firstSection: BoardSection
  def secondSection: BoardSection
  def climateZone: ArrayBuffer[ClimateCard]
  def unitZones: ArrayBuffer[UnitCard]
  def meleeZones: ArrayBuffer[MeleeCard] 
  def rangedZones: ArrayBuffer[RangedCard] 
  def siegeZones: ArrayBuffer[SiegeCard]
  def placeClimateCard(climateCard: ClimateCard): Unit
}
