
package com.pulpit

import com.pulpit.models.TweetWord
import com.twitter.util.Time
import com.pulpit.config.AuthVariables
import twitter4j._

object StatusStreamer {

  def apply(wait: Int) = {
    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
    twitterStream.addListener(Util.simpleStatusListener)
    twitterStream.sample
    Thread.sleep(wait)
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}

object Util {
  
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(AuthVariables.authConsumerKey)
    .setOAuthConsumerSecret(AuthVariables.authConsumerSecret)
    .setOAuthAccessToken(AuthVariables.authAccessToken)
    .setOAuthAccessTokenSecret(AuthVariables.authAccessTokenSecret)
    .build

  def simpleStatusListener = new StatusListener() {
    var thusfar: Set[String] = Set()
    val startingTime: Long = Time.now.inMilliseconds  
    // Functions called by Twitter4j
    def onStatus(status: Status) { TweetWord.propogate(status) }
    // other not implemented functions
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

}