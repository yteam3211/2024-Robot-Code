// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootingSubsystem;

public class AutoShooingWheels extends Command {
  /** Creates a new ShooterWheelsCommand. */
  private ShootingSubsystem shootingSubsystem;
  private double shootingVelocity;
  public AutoShooingWheels(ShootingSubsystem shootingSubsystem,double shootingVelocity) {
    this.shootingSubsystem = shootingSubsystem;
    this.shootingVelocity = shootingVelocity;
    addRequirements(shootingSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
     shootingSubsystem.setShooterVelocity(shootingVelocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shootingSubsystem.setShooterOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  return false;
  }
}