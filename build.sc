import mill._, scalalib._
import coursier.maven.MavenRepository

object reckon extends ScalaModule {
  def scalaVersion = "2.13.1"
  def ivyDeps = Agg(
   ivy"org.ajoberstar.reckon:reckon-core:0.12.0",
   ivy"com.github.zafarkhaja:java-semver:0.9.0",
   ivy"org.apache.commons:commons-lang3:3.9"
  )
  def repositories = super.repositories ++ Seq(
    MavenRepository("https://jcenter.bintray.com/")
  )
}
