// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Eleavator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.EleavatorSubsystem;

public class EleavatorCommand extends Command {
  private EleavatorSubsystem eleavatorSubsysten;
  private double eleavatorPosition;
  /** Creates a new Eleavator. */
  public EleavatorCommand(EleavatorSubsystem eleavatorSubsysten,double eleavatorPosition) {
    this.eleavatorSubsysten = eleavatorSubsysten;
    this.eleavatorPosition =eleavatorPosition;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    eleavatorSubsysten.setPosition(eleavatorPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if( Math.abs(eleavatorPosition - eleavatorSubsysten.getMasterPosition())  < Constants.ELEAVATOR_TRESHOLD)
    {
      return true;
    }
    else
    {
      return false;
    }
    
  }
}
