package com.pulpit.models

import com.redis._

object Watchable {

  final val host: String = "localhost"
  final val port: Int = 6379
  final val key: String  = "tweetwords"

  val redisClient = new RedisClient(host, port)
  def create(word: String) = redisClient.zincrby(key, 1, word)

  private def getTop(limit: Int): Seq[(String, Double)] = 
    redisClient.zrangeWithScore(key, 0, limit, RedisClient.DESC).get

  def getTopInJSON(limit: Int): String = {
    val wrds: Seq[(String, Double)] = getTop(limit)
    "{\"tweetwords\":[" + wrds.map(w => "{\"text\":\"" + w._1 + "\", \"size\":" + w._2 + "}").mkString(",") + "]}"
  }

}
