package cl.uchile.dcc.gwent.model.card.effect

abstract class AbstractEffect extends Effect {
  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Effect]) {
      val other = obj.asInstanceOf[Effect]
      this.getClass == other.getClass
    } else false
  }
}
