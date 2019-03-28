import _root_.sbt._

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.9")
resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
addSbtPlugin("org.jetbrains" % "sbt-idea-plugin" % "1.0.1")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "4.0.1")
