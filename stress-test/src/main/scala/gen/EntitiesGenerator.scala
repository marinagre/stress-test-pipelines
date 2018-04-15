package gen

import model.Language.Language
import model.{UserProfile, UserProfileStats}
import org.scalacheck.Gen.alphaChar
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck.{Arbitrary, Gen}

object EntitiesGenerator {

  def genUserProfile(implicit userProfileArbitrary: Arbitrary[UserProfile])
  : UserProfile = {
    userProfileArbitrary.arbitrary.sample.get
  }

  def genUserProfileStats(implicit profileStatsArbitrary: Arbitrary[UserProfileStats])
  : UserProfileStats = {
    profileStatsArbitrary.arbitrary.sample.get
  }

  def generateUserProfiles(number: Int): List[UserProfile] = {
    List.range(1, number).map(_ => genUserProfileSensible)
  }

  def genUserProfileSensible: UserProfile = {
    for {
      id <- Gen.posNum[Long]
      email <- generateEmailAddressLimited
      firstName <- Gen.alphaStr
      lastName <- Gen.alphaStr
      language <- implicitly[Arbitrary[Language]].arbitrary
      age <- Gen.chooseNum[Int](10, 130)
    } yield UserProfile(id = id, email = email, firstName = firstName,
      lastName = lastName, language = language, age)
  }.sample.get


  private def generateEmailAddressLimited: Gen[String] = {
    for {
      username <- alphaStrLimitedInSize(6, 10)
      at <- Gen.const("@")
      domain <- alphaStrLimitedInSize(3, 20)
      tld <- Gen.oneOf(".com", ".net", ".co.il")
    } yield List(username, at, domain, tld).mkString
  }

  private def alphaStrLimitedInSize(minSize: Int = 1, maxLength: Int): Gen[String] = {
    Gen.listOfN(Gen.choose(minSize, maxLength).sample.get, alphaChar)
      .map(_.mkString).suchThat(_.forall(_.isLetter))
  }


  private def generateEmailAddress: Gen[String] = {
    for {
      username <- Gen.alphaStr
      at <- Gen.const("@")
      domain <- Gen.alphaStr
      tld <- Gen.oneOf(".com", ".net", ".co.il")
    } yield List(username, at, domain, tld).mkString
  }

  def test = {
    Gen.posNum[Int].suchThat(x => x > 10)
  }

}
