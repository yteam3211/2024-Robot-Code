// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SwereCommands;

import org.opencv.core.TickMeter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotButtons;
import frc.robot.subsystems.Swerve;

public class lockWheelsCommnad extends CommandBase {
  private Swerve swerve;
  private boolean isAuto = false;
  private Timer timer = new Timer();


  /** Creates a new lockWheelsCommnad. */
  public lockWheelsCommnad(Swerve swerve) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.swerve = swerve;
  }

  public lockWheelsCommnad(Swerve swerve, boolean isAutonmous){
    this.swerve = swerve;
    this.isAuto = isAutonmous;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerve.setStop();
    timer.start();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotButtons.forwardJoystick.getAsBoolean() || RobotButtons.sidesJoystick.getAsBoolean() || RobotButtons.rotationJoystick.getAsBoolean() || (isAuto && timer.hasElapsed(0.05));
  }
}
