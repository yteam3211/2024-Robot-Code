// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotButtons;
import frc.robot.subsystems.Swerve;
import frc.robot.autos.centerToRampa;
import frc.robot.autos.next2human;
import frc.robot.commands.timercommand.timeSetPointCollectCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.armSubsystem;
import frc.robot.subsystems.collectWheels;
import frc.util.vision.Limelight;
import frc.robot.commands.timercommand.TimerArmPosition;
import frc.robot.commands.timercommand.TimerGripperCommand;
import frc.robot.commands.timercommand.TimerSideToSide;
import frc.robot.commands.timercommand.moveInParallel;

import frc.robot.commands.timercommand.openInParallel;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class justShoot extends SequentialCommandGroup {
  /** Creates a new atuo1. */
  private final Swerve swerve;
  private ShootingSubsystem ShootingSubsystem;
  private CollectSubsystem collectSubsystem;
  private armSubsystem armSubsystem;
  private GripperSubsystem gripperSubsystem;
  private collectWheels collectWheels;
  private Limelight limelight;

  
  public justShoot(Swerve swerve,
  armSubsystem armSubsystem,
  CollectSubsystem collectSubsystem,
  ShootingSubsystem ShootingSubsystem,
  GripperSubsystem gripperSubsystem,
  collectWheels collectWheels,
  Limelight limelight
) {
    this.swerve = swerve;
    this.armSubsystem = armSubsystem;
    this.collectSubsystem = collectSubsystem;
    this.ShootingSubsystem = ShootingSubsystem;
    this.gripperSubsystem = gripperSubsystem;
    this.collectWheels = collectWheels;
    this.limelight = limelight;
    
    
  
//     // Add your commands in the addCommands() call, e.g.
//     // addCommands(new FooCommand(), new BarCommand());
    
  
    addCommands(
    //next2human.getAutoCommand(swerve)
    //new TimerSideToSide(limelight, swerve, isFinished(),2),
    new shootingOutputCommand(ShootingSubsystem, 0.5, 6930));

  }
}