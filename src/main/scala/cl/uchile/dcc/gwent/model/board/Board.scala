package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.unit.{MeleeCard, RangedCard, SiegeCard, UnitCard}
import cl.uchile.dcc.gwent.model.card.ClimateCard
import cl.uchile.dcc.gwent.model.card.ability.ClearClimate

import scala.collection.mutable.ArrayBuffer

class Board extends IBoard {

  private val _firstSection: BoardSection = new BoardSection(this)

  private val _secondSection: BoardSection = new BoardSection(this)

  private val _climateZone = new ClimateBoardZone

  def firstSection: BoardSection = _firstSection

  def secondSection: BoardSection = _secondSection

  def climateZone: ArrayBuffer[ClimateCard] = _climateZone.cards
  
  def unitZones: ArrayBuffer[UnitCard] = _firstSection.unitZones ++ _secondSection.unitZones

  def meleeZones: ArrayBuffer[MeleeCard] = _firstSection.meleeZone ++ _secondSection.meleeZone

  def rangedZones: ArrayBuffer[RangedCard] = _firstSection.rangedZone ++ _secondSection.rangedZone

  def siegeZones: ArrayBuffer[SiegeCard] = _firstSection.siegeZone ++ _secondSection.siegeZone

  def placeClimateCard(climateCard: ClimateCard): Unit = {
    if (!climateZone.isEmpty) {
      val clearAbility = new ClearClimate
      clearAbility.apply(this)
    }
    _climateZone.addCard(climateCard)
    climateCard.applyAbilityOn(this)
  }
}
