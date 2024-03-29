// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.KickerSubsystem;

public class IntakeBackwordsCommand extends Command {
  private IntakeSubsystem intakeSubsystem;
  private double intakeWheelsOutput;
  public IntakeBackwordsCommand(IntakeSubsystem intakeSubsystem, double intakeWheelsOutput) {
    this.intakeSubsystem = intakeSubsystem;
    this.intakeWheelsOutput = intakeWheelsOutput;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("******** inside IntakeBackwordsCommand");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    System.out.println("intake note " + (!KickerSubsystem.isNoteIn() || !Robot.isAutonomous));
    if(!KickerSubsystem.isNoteIn() || !Robot.isAutonomous){
      intakeSubsystem.setWheelsMotorOutput(intakeWheelsOutput);
      System.out.println("wheels: ");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    intakeSubsystem.setWheelsMotorOutput(0);
    System.out.println("********exit IntakeBackwordsCommand");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
