// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotButtons;
import frc.util.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperTalonFX;

public class ElevatorSubsystem extends SuperSystem {
  public enum gains{
      EleavatorUp(0),
      EleavatorDown(1),
      EleavatorClimbDowm(2);
      public final int value;
      gains(int index) {
        value = index;
     }
  }
  private SuperTalonFX masterEleavatorMotor;
  private SuperTalonFX slave1EleavatorMotor;
  private SuperTalonFX slave2EleavatorMotor;
  private Gains elevatorUpGains;
  private Gains elevatorDownGains;    
  private Gains elevatorTestGains;
  public gains mode;
  public PIDController pidController = new PIDController(0, 0, 0);

  private static DigitalInput EleavatorMicrowSwitch;
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
    super("ElevatorSubsystem");
    elevatorUpGains = new Gains("elevator up Gains", 0.4, 0, 0.004);
    elevatorDownGains = new Gains("elevator climb up Gains", 0.12, 0, 0.004);
    elevatorTestGains  = new Gains("elevator climb up Gains", pidController.getP(), pidController.getI(), pidController.getD());

    masterEleavatorMotor = new SuperTalonFX(Constants.MASTER_ELEAVATOR_MOTOR_ID, Constants.CanBus.CANivore, 40, false, false, NeutralMode.Brake, elevatorUpGains, TalonFXControlMode.MotionMagic, 35000, 30000,100);
    slave2EleavatorMotor = new SuperTalonFX(masterEleavatorMotor, Constants.SLAVE_ELEAVATOR_MOTOR_ID, Constants.CanBus.CANivore, 40, false);
    EleavatorMicrowSwitch = new DigitalInput(Constants.MICROSWITCH_ELEAVATOR_ID);

    masterEleavatorMotor.config_kP(2, elevatorDownGains.kp);
    masterEleavatorMotor.config_kI(2, elevatorDownGains.ki);
    masterEleavatorMotor.config_kD(2, elevatorDownGains.kd);
    
  }

  
  /**
   * get the master motor of the elevator
   * @return the SuperTalonFX master motor
   */
  public double getMasterPosition()
  {
    return masterEleavatorMotor.getPosition();
  }
  
  public static boolean isEleavatorDown()
  {    
    return EleavatorMicrowSwitch.get();
  }
  
  public void resetEncoder()
  {
    masterEleavatorMotor.reset(0);
    // masterEleavatorMotor.set(ControlMode.PercentOutput, 0);
  }
  
  /**
   * set the hight that the elevator should go
   * @param hight hight im milimeters
   */
  public void setPosition(double hight)
  {
    double falconPos = hight * Constants.ELEVATOR_ENCODER_TICKS_PER_MILLIMETER;
    masterEleavatorMotor.set(ControlMode.MotionMagic, falconPos);
  }

  public void setOutput(double Output){
    masterEleavatorMotor.set(ControlMode.PercentOutput, Output);
  }
  
  /**
   * get the hight that the elevator got up. 
   * @return the hight from the base of the elevator to the bottom of the elevator's rider in milimeters
   */
  public double getElevatorHight(){
    // return ((getMasterPosition() / 2048) / Constants.ELEAVATOR_GEAR_RATIO) * Constants.ELEAVATOR_WINCH_CIRCUMFERENCE;
    return getMasterPosition() / Constants.ELEVATOR_ENCODER_TICKS_PER_MILLIMETER;
  }

  public void changeStation(gains mode){
   masterEleavatorMotor.selectProfileSlot(mode.value, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // getTab().putInDashboard("motor output", masterEleavatorMotor.getOutput(), false);
    getTab().putInDashboard("elevator hight", getElevatorHight(), false);
    getTab().putInDashboard("elevator master integrated encoder", getMasterPosition(), false);
    getTab().putInDashboard("is elevator down", isEleavatorDown(), false);
    getTab().putInDashboard("elevator motor output", masterEleavatorMotor.getOutput(), false);
    SmartDashboard.putData("elevator gains",pidController);

    if (this.isEleavatorDown())
    {
      resetEncoder();
      setPosition(0);
    }
    
    // if(getElevatorHight() > Constants.MAX_ELEAVATOR_POS)
    // {
    //   System.out.println("max elevator position");
    //   masterEleavatorMotor.set(ControlMode.Position, Constants.MAX_ELEAVATOR_POS);
    // }
  }
}
