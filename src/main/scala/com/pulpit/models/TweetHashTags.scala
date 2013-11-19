package com.pulpit.models

import com.pulpit.PulpitSortedSet

class TweetHashTags(val word: String) extends PulpitSortedSet {
  insert(word)
  val key: String = "tweet-hashtags"
}

object TweetHashTags {

}