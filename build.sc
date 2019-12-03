import mill._
import scalalib._
import coursier.maven.MavenRepository
import mill.scalalib.publish.{Developer, License, PomSettings, VersionControl}

object reckon extends ScalaModule with PublishModule {
  def scalaVersion = "2.13.1"
  def ivyDeps = Agg(
    ivy"org.ajoberstar.reckon:reckon-core:0.12.0",
    ivy"com.github.zafarkhaja:java-semver:0.9.0",
    ivy"org.apache.commons:commons-lang3:3.9",
    ivy"com.github.alexarchambault::case-app:2.0.0-M9"
  )
  def repositories = super.repositories ++ Seq(
    MavenRepository("https://jcenter.bintray.com/")
  )

  def artifactId = "reckon-standalone"
  def publishVersion = "0.0.1"
  def pomSettings = PomSettings(
    description = "Simple wrapper for Reckon by ajoberstar",
    organization = "com.github.james64",
    url = "https://github.com/james64/reckon-standalone",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("james64", "reckon-standalone"),
    developers = Seq(Developer("", "Jakub Dubovsky", "https://github.com/james64/"))
  )
}
