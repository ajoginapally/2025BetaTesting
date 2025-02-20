// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton.routines.SourceSubwooferRanged;

import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.StateHandler.ScoringType;
import frc.robot.commands.intake.DeployIntakeCommand;
import frc.robot.commands.scoring.ShootGamePiece;
import frc.robot.lib.autonutils.AutoHelpers;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SourceSubwooferRanged543 extends SequentialCommandGroup {
  /** Creates a new SourceSubwooferRanged543. */
  public SourceSubwooferRanged543() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // Preload
      RobotContainer.scoringMode(ScoringType.SUBWOOFER), 
      new ShootGamePiece(),
      RobotContainer.scoringMode(ScoringType.RANGED),
      //5Note
      new ParallelDeadlineGroup(
        new PathPlannerAuto("StartSourceSubwooferRanged5"),
        new SequentialCommandGroup(
            new WaitCommand(2.0),
          new DeployIntakeCommand()
          )
      ),
      AutoHelpers.commandPathFrom("5ToStageRanged"),
      AutoHelpers.goalCentricShoot(),

      //4Note
      new ParallelDeadlineGroup(
        AutoHelpers.commandPathFrom("StageRangedTo4"),
        new SequentialCommandGroup(
            new WaitCommand(1.45),
          new DeployIntakeCommand()
          )
      ),
      AutoHelpers.commandPathFrom("4ToStageRanged"),
      AutoHelpers.goalCentricShoot(),

      //3Note
      new ParallelDeadlineGroup(
        AutoHelpers.commandPathFrom("StageRangedTo3"), 
        new SequentialCommandGroup(
            new WaitCommand(1.65),
          new DeployIntakeCommand()
          )
      ),
      AutoHelpers.commandPathFrom("3ToStageRanged"),
      AutoHelpers.goalCentricShoot()
    );
  }
}
