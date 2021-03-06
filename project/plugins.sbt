addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")
addSbtPlugin("org.portable-scala" % "sbt-scala-native-crossproject" % "1.0.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.4.0")
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.0")
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.2")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.25")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" %  "2.4.2")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.5")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.1.1")
//addSbtPlugin("org.scalameta" % "sbt-mdoc" % "2.2.12" )
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0")

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")

// for Scala.js 1.x.x
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta29")
