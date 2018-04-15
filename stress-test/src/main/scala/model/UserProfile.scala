package model

import argonaut.Argonaut._
import argonaut.{CodecJson, DecodeJson, EncodeJson}
import model.Language.Language

case class UserProfile(id: Long,
                       email: String,
                       firstName: String,
                       lastName: String,
                       language: Language,
                       age: Int)

object UserProfile {
  implicit def UserProfileCodecJson: CodecJson[UserProfile] =
    casecodec6(UserProfile.apply, UserProfile.unapply)("id", "email", "firstName", "lastName", "language", "age")

  implicit val dx: DecodeJson[Language] = StringDecodeJson.map(x => Language.withName(x))
  implicit val ex: EncodeJson[Language] = EncodeJson(x => jString(x.toString))
}
