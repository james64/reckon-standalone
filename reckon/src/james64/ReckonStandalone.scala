package james64

import java.util.Optional

import caseapp._
import org.ajoberstar.reckon.core.Reckoner
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository

object ReckonStandalone extends CaseApp[Options] {

  def run(options: Options, arg: RemainingArgs): Unit = print {
    Reckoner.builder()
      .git(localGit(options.gitDir))
      .scopeCalc(_ => Optional.ofNullable(options.scope.orNull))
      .stageCalc((_,_) => Optional.ofNullable(options.stage.orNull))
      .stages(dummyStages(options.stage):_*)
      .build
      .reckon
  }

  private def dummyStages(inputStage: Option[String]) : Seq[String] = (inputStage.toSet + "rc").toList

  private def localGit(gitDir: String) : Repository = Git.open(new java.io.File(gitDir)).getRepository
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
