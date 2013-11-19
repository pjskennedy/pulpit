import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name := "pulpit"

version := "0.1.0"

scalaVersion := "2.10.0"

resolvers += "twitter-repo" at "http://maven.twttr.com"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "twitter" at "http://maven.twttr.com"

libraryDependencies += "com.twitter" %% "finagle-core" % "6.2.0"

libraryDependencies += "com.twitter" %% "finagle-http" % "6.2.0"

libraryDependencies += "net.debasishg" % "redisclient_2.10" % "2.10"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"

libraryDependencies += "com.twitter" %% "util-collection" % "6.3.6"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.0"

