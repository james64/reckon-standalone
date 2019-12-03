package james64

import java.util.Optional

import caseapp._

import org.ajoberstar.reckon.core.Reckoner
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

object ReckonStandalone extends CaseApp[Options] {

  def run(options: Options, arg: RemainingArgs): Unit = println {
    Reckoner.builder()
      .git(localGit(options.gitDir))
      .scopeCalc(_ => Optional.ofNullable(options.scope.orNull))
      .stageCalc((_,_) => Optional.ofNullable(options.stage.orNull))
      .stages(dummyStage(options.stage))
      .build
      .reckon
  }

  private def dummyStage(input: Option[String]) : String = input match {
    case None => "rc"
    case Some("final") => "rc"
    case Some(s) => s
  }

  private def localGit(gitDir: String) : Repository = new FileRepositoryBuilder()
    .setGitDir(new java.io.File(gitDir))
    .build()
}

@AppName("Reckon-standalone")
case class Options(
  @HelpMessage("Optional scope to pass to reckoner. One of \"major\", \"minor\", \"patch\". Defaults to \"minor\".")
  scope: Option[String],
  @HelpMessage("Optional stage to pass to reckoner. Defaults to \"rc\".")
  stage: Option[String],
  @HelpMessage("Location of .git directory. Defaults to \".git\"")
  gitDir: String = ".git"
)
