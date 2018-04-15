package builders

import com.clicktale.ai.rabbitmq.Predef._
import com.clicktale.ai.rabbitmq.protocol.RabbitProtocol
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

class RabbitEntityBuilder(exchange: String)(implicit protocol: RabbitProtocol) extends EntityBuilder {
  def build(): ChainBuilder = {
    foreach("${userProfiles}", "userProfile") {
      exec(rabbit("Publish")
        .send("${userProfile.userId}", "${userProfile.profile}")
      )
    }
  }
}
