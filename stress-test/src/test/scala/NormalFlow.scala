import builders.HttpEntityBuilder
import gen.EntitiesGenerator
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import model.UserProfile

import scala.concurrent.duration._

/**
  * Created by marina.grechuhin on 02/04/2018.
  */
class NormalFlow extends Simulation {

  protected val initialUsers = 4
  protected val startWarmupUsers = 12
  protected val endWarmupUsers = 10
  protected val numOfLoadUsers = 10
  protected val finishedLoadUSers = 5

  val httpProtocol: HttpProtocolBuilder = http
    .baseURL("http://192.168.127.9:8080")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.8,he;q=0.6,ru;q=0.4")
  //.check(status.is(200))

  val numOfUsers = 2

  val scn = scenario("NormalFlow")
    .exec(inOrderChainWithFeeder(numOfUsers))

  protected def inOrderChainWithFeeder(numberOfUsers: Int): ChainBuilder = {
    val seqMessages =
      Iterator.continually(
        initFeeder(EntitiesGenerator.generateUserProfiles(numberOfUsers)))

    feed(seqMessages)
      .exec(HttpEntityBuilder.build)
  }

  protected def initFeeder(userProfiles: List[UserProfile]): Map[String, Any] = {
    Map("userProfiles" -> userProfiles.map(profile => (profile.id, profile.toString)))
  }

  val typicalLoadInjectionSteps = Array(
    rampUsersPerSec(1) to initialUsers during (5 seconds),
    constantUsersPerSec(initialUsers) during (5 seconds),
    //Start stressing the system
    rampUsersPerSec(endWarmupUsers) to numOfLoadUsers during (6 seconds),
    //Keep load pressure for a period of time
    constantUsersPerSec(numOfLoadUsers) during (4 seconds))

  val singleUser = atOnceUsers(1)


  setUp(scn.inject(typicalLoadInjectionSteps)).protocols(httpProtocol)

}
