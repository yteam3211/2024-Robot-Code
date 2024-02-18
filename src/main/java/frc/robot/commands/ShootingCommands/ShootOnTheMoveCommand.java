// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootingCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.ShootingMath;
import frc.robot.commands.SwereCommands.TurnToShootingCommand;
import frc.robot.commands.SwereCommands.TurnWhileDriveCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.PitchingSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.Swerve;
import frc.util.vision.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootOnTheMoveCommand extends SequentialCommandGroup {
  /** Creates a new ShootOnTheMove. */
  public ShootOnTheMoveCommand(Swerve swerve, Limelight limelight, ShootingSubsystem shootingSubsystem, PitchingSubsystem pitchingSubsystem,ElevatorSubsystem eleavatorSubsystem,KickerSubsystem kickerSubsystem, ShootingMath shootingMath, DoubleSupplier translationSup, DoubleSupplier strafeSup) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelDeadlineGroup(
        new ParallelCommandGroup(
          new TurnToShootingCommand(swerve, limelight, shootingMath),
          new ShootingSpeedCommand(shootingSubsystem,kickerSubsystem, Constants.SHOOTING_VELCITY,0.4)),
        new PitchCommand(limelight, pitchingSubsystem, eleavatorSubsystem, shootingMath)),
      new KickerCommand(kickerSubsystem, 0)
    );
  }
}
