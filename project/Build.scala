import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "marknote"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
	    "postgresql" % "postgresql" % "8.4-702.jdbc4",
        "org.markdownj" % "markdownj" % "0.3.0-1.0.2b4",
	    "org.specs2" %% "specs2" % "1.8.2" % "test",
	    "org.mockito" % "mockito-all" % "1.9.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here
    )

}
