package com.pulpit

import com.redis.RedisClient

abstract class PulpitSortedSet {

  val key: String

  def insert(item: String): Option[Double] = RedisInterface.client.zincrby(key, 1, item)

  def getTop(limit: Int): Seq[(String, Double)] = 
    RedisInterface.client.zrangeWithScore(key, 0, limit, RedisClient.DESC).get

  def getTopJSON(limit: Int): String = 
    "[{" + getTop(limit).map{p => 
      "\"text\":\"" + p._1 + "\"," + 
      "\"value\":" + p._2
    }.mkString("},{") + "}]"


}