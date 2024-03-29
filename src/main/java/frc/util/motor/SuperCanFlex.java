// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.util.motor;

import com.revrobotics.CANSparkFlex;

import frc.util.PID.Gains;

/**
 * This class is SuperMotor of SparkFlex
 * 
 * @author Noya Aberjil
 */
/** Add your docs here. */
public class SuperCanFlex extends CANSparkFlex implements SuperMotor
{
    private ControlType controlType;
    private static final int MAX_AMPS_DEFAULT = 60;

    /**
     * This constractor of m0aster
     * 
     * @param deviceNumber can id
     * @param motorType    type of motor Brushed or Brushless
     * @param amps         amper limitation
     * @param inverted     when side motor move
     * @param mode         mode of motor brake or coast
     */
    public SuperCanFlex(int deviceNumber, MotorType motorType, int amps, boolean inverted, IdleMode mode) {
        super(deviceNumber, motorType);
        setInverted(inverted);
        setSmartCurrentLimit(amps);
        setIdleMode(mode);

    }

    /**
     * This constractor of master
     * 
     * @param deviceNumber     can id
     * @param motorType        type of motor Brushed or Brushless
     * @param amps             amper limitation
     * @param inverted         when side motor move
     * @param positionMultiply changing Position
     * @param velocityMultiply changing velocity
     * @param mode             mode of motor brake or coast
     */
    public SuperCanFlex(int deviceNumber, MotorType motorType, int amps, boolean inverted, double positionMultiply,
            double velocityMultiply, IdleMode mode, ControlType controlType, Gains gains, double maxVel, double maxAcc,
            double allowedErr) {

        super(deviceNumber, motorType);
        this.controlType = controlType;
        setInverted(inverted);
        setSmartCurrentLimit(amps);
        getEncoder().setPositionConversionFactor(positionMultiply);
        getEncoder().setVelocityConversionFactor(velocityMultiply);
        setIdleMode(mode);

        getPIDController().setP(gains.kp);
        getPIDController().setI(gains.ki);
        getPIDController().setD(gains.kd);
        getPIDController().setIZone(gains.Ki_zone);
        getPIDController().setFF(gains.Kf);
        getPIDController().setOutputRange(-1, 1);

        getPIDController().setSmartMotionMaxVelocity(maxVel, 0);
        getPIDController().setSmartMotionMaxAccel(maxAcc, 0);
        getPIDController().setSmartMotionAllowedClosedLoopError(allowedErr, 0);
    }

    /**
     * This constractor of slave
     * 
     * @param leader       The master that motor follow
     * @param deviceNumber can id
     * @param motorType    type of motor Brushed or Brushless
     * @param amps         amper limitation
     * @param inverted     when side motor move
     * @param mode         mode of motor brake or coast
     */
    public SuperCanFlex(SuperSparkMax leader, int deviceNumber, MotorType motorType, int amps, boolean inverted,
            IdleMode mode) {
        super(deviceNumber, motorType);
        follow(leader);
        setInverted(inverted);
        setSmartCurrentLimit(amps);
        setIdleMode(mode);
    }

    /**
     * This constractor of master
     * 
     * @param deviceNumber can id
     * @param inverted     when side motor move
     */
    public SuperCanFlex(int deviceNumber, boolean inverted) {
        this(deviceNumber, MotorType.kBrushless, MAX_AMPS_DEFAULT, inverted, IdleMode.kBrake);
    }

    @Override
    public void setOutput(double output) {
        if (controlType == null)
            return;
        set(output);
    }

    @Override
    public double getOutput() {
        return get();
    }

    @Override
    public double getAmper() {
        return getOutputCurrent();
    }

    @Override
    public void setMode(Object mode) {
        if (mode instanceof IdleMode)
            setIdleMode((IdleMode) mode);
    }

    @Override
    public double getVelocity() {
        return getEncoder().getVelocity();
    }

    @Override
    public double getPosition() {
        return getEncoder().getPosition();
    }

    @Override
    public void reset(double pos) {
        getEncoder().setPosition(pos);
    }


}
