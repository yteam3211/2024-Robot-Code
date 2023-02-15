// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Limelight;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;
import frc.util.PID.Gains;
import frc.util.PID.PIDController;
import frc.util.vision.Limelight;

public class DriveToTarget extends CommandBase {
  /** Creates a new DriveToTarget. */
  protected Limelight limelight;
  protected Swerve swerve;
  protected PIDController pidX = new PIDController();
  protected PIDController pidY = new PIDController();
  protected PIDController pidR = new PIDController();
  protected Gains gainsX = new Gains("vision x", 0, 0, 0);
  protected Gains gainsY = new Gains("vision y", 0, 0, 0);
  protected Gains gainsR = new Gains("vision r", 0, 0, 0);

  // protected Gains
  public DriveToTarget(Limelight limelight, Swerve swerve) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
    this.swerve = swerve;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidX.setTargetPosition(0.0);
    pidY.setTargetPosition(37.2);
    pidX.setTargetPosition(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
// how many degrees back is your limelight rotated from perfectly vertical?


// distance from the center of the Limelight lens to the floor
double limelightLensHeightInches = 43.026;

// distance from the target to the floor
double goalHeightCM = 41.275;

double angleToGoalDegrees = limelight.getY();
double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0);

//calculate distance
double distanceFromLimelightToGoalCM = (goalHeightCM - limelightLensHeightInches)/Math.tan(angleToGoalRadians);

    swerve.drive(
        new Translation2d(pidX.getOutput(limelight.getX()), pidY.getOutput(distanceFromLimelightToGoalCM)),
        pidR.getOutput(Swerve.gyro.getYaw()),
        false, // Field oriented by the controller switch
        true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
