// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem.gains;

public class ElevatorDown extends Command {
  private ElevatorSubsystem eleavatorSubsystem;
  private double eleavatorPosition;
  /** Creates a new Elevator. */
  public ElevatorDown(ElevatorSubsystem eleavatorSubsystem ,double eleavatorPosition) {
    this.eleavatorSubsystem = eleavatorSubsystem;
    this.eleavatorPosition =eleavatorPosition;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    eleavatorSubsystem.changeStation(gains.EleavatorUp);
    System.out.println("******** inside EleavatorDown");

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (eleavatorSubsystem.isEleavatorDown() ) {
      eleavatorSubsystem.setOutput(0);
    }
    else
    {
      eleavatorSubsystem.setPosition(eleavatorPosition);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    eleavatorSubsystem.setOutput(0);
    System.out.println("********exit EleavatorDown");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return eleavatorSubsystem.isEleavatorDown() || (Math.abs(eleavatorPosition - eleavatorSubsystem.getElevatorHight()) < Constants.ELEAVATOR_TRESHOLD);
  }
}
