package propertytest

import argonaut._, Argonaut._
import model.UserProfile
import org.scalacheck.Prop.forAll

import org.scalacheck.ScalacheckShapeless._

object UserProfileSerializationTests {

  def toJson(user: UserProfile): Json = ???
  def fromJson(json: Json): Either[Exception, UserProfile] = ???

  val jsonTest = forAll { user: UserProfile =>
    Parse.decode[UserProfile](user.asJson.toString()) == Right(user)
  }

}
