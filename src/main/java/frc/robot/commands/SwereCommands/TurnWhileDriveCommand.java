// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SwereCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.util.PID.Gains;
import frc.util.PID.PIDController;
import frc.util.vision.Limelight;

public class TurnWhileDriveCommand extends Command {
  private Swerve swerve;
  private Limelight limelight;
  private DoubleSupplier translationSup;
  private DoubleSupplier strafeSup;
  protected Gains gains = new Gains("rotation gains", 0, 0, 0);
  protected PIDController pid = new PIDController(gains);

  /** Creates a new TurnToZeroCommand. */
  public TurnWhileDriveCommand(Swerve swerve, Limelight limelight, DoubleSupplier translationSup, DoubleSupplier strafeSup) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
    this.swerve = swerve;
    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    addRequirements(swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.setTargetPosition(0);
    pid.setMaxOutput(Constants.SwerveConstant.maxSpeed * 0.6);
    System.out.println("******** inside TurnWhileDriveCommand");

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband)/8;
    double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband)/8;

    double output = pid.getOutput(limelight.getX());
    output += 0.1 * Constants.SwerveConstant.maxAngularVelocity * Math.signum(output);
    swerve.drive(new Translation2d(translationVal, strafeVal), output, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("********exit TurnWhileDriveCommand");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(limelight.getX()) < Constants.SHOOTING_ANGLE_TRESHOLD;
    };
  }