// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ShootingCommands.KickerCommand;
import frc.util.SuperSystem;
import frc.util.motor.SuperSparkMax;

public class KickerSubsystem extends SuperSystem {
  /** Creates a new KickerSubsystem. */
    public SuperSparkMax kickerMotor;
    public DigitalInput noteIn;
    

  public KickerSubsystem() {
  super("Kicker Subsystem");
   kickerMotor = new SuperSparkMax(Constants.KICKER_SHOOTER_MOTOR_ID, MotorType.kBrushless, 40, false, IdleMode.kCoast);
   noteIn = new DigitalInput(1);

  }

  @Override
  public void periodic() {
    // test( 1 > 2 ? 3 : 2, 2);
    // This method will be called once per scheduler run
    getTab().putInDashboard("is note in", isNoteIn(), false);
  }

    public void setKickerOutput(double Output)
  {
    kickerMotor.setMode(ControlMode.PercentOutput);
    kickerMotor.set(Output);
  }

  public boolean isNoteIn(){
    return noteIn.get();
  }
  
  public void setPosition(double pos){
    kickerMotor.setMode(ControlMode.Position);
    kickerMotor.set(pos);

  }
  public void test(double a, double b){}
  }
