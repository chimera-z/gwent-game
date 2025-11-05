package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}

import scala.collection.mutable.ArrayBuffer

trait IBoardSection {
  def meleeZone: ArrayBuffer[MeleeCard]

  def rangedZone: ArrayBuffer[RangedCard] 

  def siegeZone: ArrayBuffer[SiegeCard]

  def unitZones: ArrayBuffer[UnitCard]

  def placeMeleeCard(meleeCard: MeleeCard): Unit

  def placeRangedCard(rangedCard: RangedCard): Unit

  def placeSiegeCard(siegeCard: SiegeCard): Unit

  def placeClimateCard(climateCard: ClimateCard): Unit

}
