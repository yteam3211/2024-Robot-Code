// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePos extends Command {
  private IntakeSubsystem intakeSubsystem;
  private double position;
  /** Creates a new IntakePos. */
  public IntakePos(IntakeSubsystem intakeSubsystem, double position) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakeSubsystem = intakeSubsystem;
    this.position = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("******* inside IntakePos");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeSubsystem.setIntakeOpenMotorPosition(position);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.setIntakeOpenMotorPosition(position);
    System.out.println("******* exit IntakePos");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return intakeSubsystem.getPosition() > 10;
    // return true;
  }
}
