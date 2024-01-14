// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootingCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.SwereCommands.TurnToShootingCommand;
import frc.robot.subsystems.PitchingSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.Swerve;
import frc.util.vision.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CompleteShootingCommand extends SequentialCommandGroup {
  /** Creates a new CompleteSootingCommand. */
  public CompleteShootingCommand(Swerve swerve, Limelight limelight, ShootingSubsystem shootingSubsystem, PitchingSubsystem pitchingSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(new TurnToShootingCommand(swerve, limelight, Constants.SHOOTING_ANGLE_TRESHOLD), new PitchCommand(limelight,pitchingSubsystem), new ShootingSpeedCommand(shootingSubsystem, Constants.SHOOTING_VELCITY))

    );
  }
}