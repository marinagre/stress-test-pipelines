package model

case class UserProfileStats(userId: Long,
                            numberOfSearches: Int,
                            numberOfClicks: Int,
                            timeOnProfile: Int)
