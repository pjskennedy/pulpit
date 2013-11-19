package com.pulpit.models



object Tweet {
  
  val regex = "#?([a-zA-Z0-9]+)".r

  def propogate(body: String) {
    for {
      word <- body.split(' ')
      if (Tweet.validWord(word))
    } yield (Watchable.create(word.toLowerCase))
  }

  def validWord(word: String): Boolean = regex.pattern.matcher(word).matches

}
