package builders

import io.gatling.core.structure.ChainBuilder

trait EntityBuilder {
  def build: ChainBuilder
}
