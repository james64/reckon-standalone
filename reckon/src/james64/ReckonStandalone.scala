package james64

import java.util.Optional

import org.ajoberstar.reckon.core.Reckoner
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

object ReckonStandalone {

  def main(args: Array[String]) : Unit = println {
    Reckoner.builder()
      .git(localGit)
      .scopeCalc(_ => Optional.ofNullable(args(0)))
      .stageCalc((_,_) => Optional.ofNullable(args(1)))
      .stages(args(1))
      .build
      .reckon
  }

  private def localGit : Repository = (new FileRepositoryBuilder())
    .setGitDir(new java.io.File(".git"))
    .build()
}
