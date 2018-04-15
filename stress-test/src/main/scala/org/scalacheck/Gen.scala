package org.scalacheck

import org.scalacheck.rng.Seed

object RandomTest {

  def random[T](implicit arbitrary: Arbitrary[T]): T = {
    val gen: Gen[T] = arbitrary.arbitrary
    val res: Option[T] = gen.sample
    res.get
  }

}




