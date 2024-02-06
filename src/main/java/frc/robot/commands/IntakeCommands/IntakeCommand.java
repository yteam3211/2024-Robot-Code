// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
  private IntakeSubsystem intakeSubsystem;
  private double intakePosition;
  private double intakeWheelsVelocity;
  public IntakeCommand(IntakeSubsystem intakeSubsystem,double intakePosition,double intakeWheelsVelocity)
  {
    this.intakeSubsystem = intakeSubsystem;
    this.intakePosition = intakePosition;
    this.intakeWheelsVelocity = intakeWheelsVelocity;

    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    
    intakeSubsystem.setWheelsMotorVelocity(intakeWheelsVelocity);
    intakeSubsystem.setIntakeOpenMotorPosition(intakePosition);
    // intakeSubsystem.setIntakeOpenMotorOutput(intakePosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    intakeSubsystem.setWheelsMotorOutput(0);
    intakeSubsystem.setIntakeOpenMotorPosition(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
