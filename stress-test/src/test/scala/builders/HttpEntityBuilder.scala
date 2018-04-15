package builders

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.http

object HttpEntityBuilder extends EntityBuilder {

  def build: ChainBuilder = {
    foreach("${userProfiles}", "userProfile") {
      exec(http("user_profile_request}")
        .post("/stresstest?userId=&${userProfile._1}")
        .headers(getHeaders)
        .body(StringBody("${userProfile._2}"))
      )
    }
  }

  protected def getHeaders = Map(
    "Cache-Control" -> "no-cache",
    "Content-Type" -> "application/json",
    "Origin" -> "http://load.testing",
    "Pragma" -> "no-cache",
    "User-Agent" -> "User-Agent"
  )

}
