// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.armCollectSubsystem;
import frc.robot.subsystems.armSubsystem;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** An example command that uses an example subsystem. */
public class ArmCollectCommand extends CommandBase {
  private final armCollectSubsystem armCollect;
  private double position;
  private double seconds;
  private Timer timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmCollectCommand(armCollectSubsystem armCollect, double position, double seconds) {
    this.armCollect = armCollect;
    this.position = position;
    this.seconds = seconds;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armCollect);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armCollect.setArmCollectPosition(position);
  }

  // Called once the command ends or is interrupted.
  @Override

  public void end(boolean interrupted) {
    // timer.delay(1);
    armCollect.setArmCollectPosition(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return timer.hasElapsed(seconds);
    }
}
