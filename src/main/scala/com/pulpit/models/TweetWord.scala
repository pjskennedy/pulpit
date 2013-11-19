package com.pulpit.models

import com.pulpit.PulpitSortedSet
import twitter4j.Status


object TweetWord extends PulpitSortedSet {

  val key: String = "twitter-word"

  val validRegex = "#?([a-zA-Z0-9]+)".r

  def propogate(status: Status) {
    for {
      word <- status.getText.split(' ')
      cword = clean(word)
      if (validWord(cword))      
    } yield (insert(cword))
  }

  def clean(word: String): String = 
    word.replace("\"", " ").replace(".", " ").replace(",", " ").toLowerCase

  def validWord(word: String): Boolean = validRegex.pattern.matcher(word).matches

}