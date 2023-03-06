// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.util.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperSparkMax;
import frc.util.motor.SuperTalonFX;
import frc.util.motor.SuperTalonSRX;
import frc.util.motor.SuperVictorSP;
import pabeles.concurrency.IntOperatorTask.Min;


// Yteam Example Subsystem
public class CartridgeSubsystem extends SuperSystem {
//  public SuperSparkMax ShooingMotor;
private Gains shootingGains;
 private DigitalInput upMicroSwitch;
 private DigitalInput downMicroSwitch;
 private SuperTalonFX Shooting;
 private SuperTalonSRX shootingWheels;
 private int max = 6930;
 private int Min = -6000;
  // Motors, Selenoid and Sensors declaration
  public CartridgeSubsystem() {
    super("ShootingSubsystem");
    shootingGains = new Gains("_shootingGains",0.03, 0,0);
    Shooting = new SuperTalonFX(Constants.SHOOTING_MOTOR, 30, false, false, NeutralMode.Coast, shootingGains, TalonFXControlMode.PercentOutput);
     // upMicroSwitch = new DigitalInput(Constants.UP_MICROSWITCH);
    // downMicroSwitch = new DigitalInput(Constants.DOWN_MICROSWITCH);
    // firstShootingSp = new VictorSP(0);
    // secondShootingSp = new VictorSP(1);
    // ShooingMotor = new SuperSparkMax(14,false);
    // ShooingMotor = new SuperSparkMax(14, MotorType.kBrushless, 30, false, 1 ,1 , IdleMode.kBrake, ControlType.kPosition, shootingGains, 0, 0, 0);
  }

  /** Creates a new ExampleSubsystem. */
  
  @Override
  public void periodic() {
    // getTab().putInDashboard("Position", ShooingMotor.getEncoder().getPosition(), false);
    // SmartDashboard.putNumber("Shootingoutput", Shooing.getOutput());
    // SmartDashboard.putNumber("ShootingAmper", Shooing.getAmper());
    // SmartDashboard.putNumber("ShootingPosition", Shooing.getPosition());
    // System.out.println("shoot" + Shooing.getPosition());
    // This method will be called once per scheduler run
  }

  public void setOutput(double output){
    // ShooingMotor.setMode(ControlMode.PercentOutput);
     // neo motor 
    Shooting.set(ControlMode.PercentOutput, output);
    // SmartDashboard.putNumber("Shootingtarget", output);
    // if ((output > 0 && !isShootingUp()) || (output <0 && !isShootingDown())) {
    //   firstShootingSp.set(output);
    //   secondShootingSp.set(output);
    // }
  
  }
  public void resetEncoder(){
    Shooting.reset(0);
  }

  public void setPosition(double position){
    Shooting.set(ControlMode.Position, position);
    // ShooingMotor.setMode(ControlMode.Position);
    // ShooingMotor.getPIDController().setReference(position, ControlType.kPosition);
    // getTab().putInDashboard("encoder_live", ShooingMotor.getPosition(), false);

  }
  public double GetPosition(){
   return Shooting.getPosition();
 

    
  }

  public void setVelocity(double velocity){
    Shooting.set(ControlMode.Velocity, velocity);
    // ShooingMotor.setMode(ControlMode.Position);
    // ShooingMotor.getPIDController().setReference(position, ControlType.kPosition);
    // getTab().putInDashboard("encoder_live", ShooingMotor.getPosition(), false);

  }

  public double GetOutput(){
    return Shooting.getOutput();
  }

  public boolean isShootingUp(){
    
    //  return upMicroSwitch.get();
    return false;
  }

  public boolean isShootingDown(){
    
    // return downMicroSwitch.get();
    return false;
  }

public void changeDefault(){
  // setDefaultCommand(new ShootingPosition(this, 0));
}
}


