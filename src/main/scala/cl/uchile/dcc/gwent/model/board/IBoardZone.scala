package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.Card

import scala.collection.mutable.ArrayBuffer

trait IBoardZone[T <: Card] {
  def cards: ArrayBuffer[T]
  def addCard(card: T): Unit
}
