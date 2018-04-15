package builders

import com.github.mnogu.gatling.kafka.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

object KafkaEntityBuilder extends EntityBuilder {

  def build: ChainBuilder = {
    foreach("${userProfiles}", "userProfile") {
      exec(kafka("request")
        .send[String]("${userProfile.profile}")
      )
    }
  }
}
