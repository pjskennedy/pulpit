package com.pulpit

import com.redis._
import akka.util.Timeout
import akka.actor.ActorSystem
import com.twitter.util.Time
import com.twitter.conversions.time._

object RedisInterface {

  implicit val system = ActorSystem("redis-client")
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout((5.seconds.inMilliseconds))

  private val port: Int = 6379
  private val host: String = "localhost"

  val client: RedisClient = new RedisClient(host, port)

}