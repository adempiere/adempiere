resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.7.0-SNAPSHOT")

resolvers += "sbt-vaadin-plugin repo" at "http://henrikerola.github.io/repository/release"

addSbtPlugin("org.vaadin.sbt" % "sbt-vaadin-plugin" % "1.2.0")