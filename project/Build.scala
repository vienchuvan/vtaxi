import com.github.play2war.plugin.{Play2WarKeys, Play2WarPlugin}
import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "vtaxi"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA)
    .settings(Play2WarPlugin.play2WarSettings: _*)
    .settings(
    // ... Your own settings here
    Play2WarKeys.servletVersion := "2.5"
  )
}
