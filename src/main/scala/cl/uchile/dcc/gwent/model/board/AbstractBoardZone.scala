package cl.uchile.dcc.gwent.model.board

import cl.uchile.dcc.gwent.model.card.Card

import scala.collection.mutable.ArrayBuffer

abstract class AbstractBoardZone[T <: Card] extends IBoardZone[T] {
  protected val _cards: ArrayBuffer[T] = ArrayBuffer[T]()

  def cards: ArrayBuffer[T] = _cards.clone()
}
