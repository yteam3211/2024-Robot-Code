// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootingCommands.KickerCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.ShootingSubsystem;

public class KickerShootingCommand extends Command {
  /** Creates a new kickerCommand. */
  private KickerSubsystem kickerSubsystem;
  private ShootingSubsystem shootingSubsystem;
  private double kickerOutput;

  public KickerShootingCommand(KickerSubsystem kickerSubsystem, ShootingSubsystem shootingSubsystem, double kickerOutput) {
  this.kickerSubsystem = kickerSubsystem;
  this.shootingSubsystem = shootingSubsystem;
  this.kickerOutput = kickerOutput;
  addRequirements(kickerSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("******** inside KickerShootingCommand");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("shooting kicker in use");
    if(Math.abs(shootingSubsystem.getVelocity() - Constants.SHOOTING_SPEAKER_VELCITY) < Constants.SHOOTING_VELOCITY_TRESHOLD)
      kickerSubsystem.setKickerOutput(kickerOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    kickerSubsystem.setKickerOutput(0);
    shootingSubsystem.setShooterOutput(0);
    System.out.println("********exit KickerShootingCommand");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return !KickerSubsystem.isNoteIn();
  }
}
