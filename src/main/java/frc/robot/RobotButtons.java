package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import javax.swing.GroupLayout.Group;

import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;

import edu.wpi.first.networktables.PubSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.SwereCommands.DriveToTarget;
import frc.robot.commands.SwereCommands.TeleopSwerve;
import frc.robot.commands.SwereCommands.TurnToShootingCommand;
import frc.robot.commands.TransferCommands.TransferCommand;
import frc.robot.commands.AutoCommands.AutoShooingWheels;
import frc.robot.commands.Eleavator.EleavatorClimbDown;
import frc.robot.commands.Eleavator.EleavatorUpCommand;
import frc.robot.commands.Eleavator.OpenElevatorCommanGroup;
import frc.robot.commands.Eleavator.EleavatorDown;
import frc.robot.commands.Eleavator.PitchAndEleavator;
import frc.robot.commands.IntakeCommands.IntakeAndTransferCommand;
import frc.robot.commands.IntakeCommands.IntakeBackwordsCommand;
import frc.robot.commands.IntakeCommands.IntakeCommand;
import frc.robot.commands.IntakeCommands.IntakeWheels;
import frc.robot.commands.ShootingCommands.CompleteAMPShootingCommand;
import frc.robot.commands.ShootingCommands.CompleteSpeakerShootingCommand;
import frc.robot.commands.ShootingCommands.KickerCommand;
import frc.robot.commands.ShootingCommands.KickerShootingCommand;
import frc.robot.commands.ShootingCommands.PitchPos;
import frc.robot.commands.ShootingCommands.ShootOnTheMoveCommand;
import frc.robot.commands.ShootingCommands.PitchCommand;
import frc.robot.commands.ShootingCommands.ShootingSpeedCommand;
import frc.robot.commands.ShootingCommands.ShootingVelocity;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.PitchingSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.TransferSubsystem;
import frc.util.vision.Limelight;
// import frc.robot.commands.Balance;


// Yteam loadButtons
public class RobotButtons {
    public static Joystick systems = new Joystick(1);
    public static Joystick driver = new Joystick(0);

    // driver jpoystick buttons
    public static DoubleSupplier BreakValue = () -> driver.getRawAxis(PS5Controller.Axis.kR2.value);
    public static Trigger resetGyro = new Trigger(() -> driver.getRawButton(PS5Controller.Button.kL1.value));
    // public static Trigger forwardJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftY.value)) > 0.1);
    // public static Trigger sidesJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftX.value)) > 0.1);
    // public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    // public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    
    // systems joystick buttons
    public static Trigger intakeTrigger = new Trigger(() ->  systems.getRawButton(PS5Controller.Button.kR2.value)); 
    public static Trigger completeSpeakerShootingTrigger = new Trigger(() -> systems.getRawButton(PS5Controller.Button.kTriangle.value));
    public static Trigger apmShootingTrigger = new Trigger(() ->  systems.getRawButton(PS5Controller.Button.kCross.value));
    public static Trigger pitchDown = new Trigger(() ->  systems.getRawButton(PS5Controller.Button.kCircle.value)); // systems
     public static Trigger pitch = new Trigger(() ->  systems.getRawButton(PS5Controller.Button.kSquare.value));// systems
    public static Trigger climbUpTrigger = new Trigger(() ->  systems.getPOV() == 0);  // systems  
    public static Trigger climbDown = new Trigger(() ->  systems.getPOV() == 180); // systems   
    public static Trigger climb = new Trigger(() ->  systems.getPOV() == 90); // systems

    public static Trigger PitchTrigger = new Trigger(() -> systems.getRawButton(PS5Controller.Button.kOptions.value));
    public static Trigger shootingReverse = new Trigger(() -> systems.getRawButton(PS5Controller.Button.kL1.value));
    public static Trigger intakeReverse = new Trigger(() -> systems.getRawButton(PS5Controller.Button.kR1.value));
    
    

    public Trigger LEDtTrigger = new Trigger(() -> systems.getRawButton(PS5Controller.Button.kOptions.value));
    

    /**
     * @param shootingSubsystem
     * @param collectSubsyste
     * @param armSubsystem
     * @param swerve
     */
    public void loadButtons(Swerve swerve, Limelight limelight, ShootingSubsystem shootingSubsystem, PitchingSubsystem pitchingSubsystem, IntakeSubsystem intakeSubsystem, TransferSubsystem transferSubsystem,ElevatorSubsystem elevatorSubsystem,KickerSubsystem kickerSubsystem, ShootingMath shootingMath) {
        // driver joystick commands
        swerve.setDefaultCommand(
            new TeleopSwerve(
                    swerve,
                    () -> -driver.getRawAxis(PS5Controller.Axis.kLeftY.value),
                    () -> -driver.getRawAxis(PS5Controller.Axis.kLeftX.value),
                    () -> -driver.getRawAxis(PS5Controller.Axis.kRightX.value)
                    ));
        resetGyro.onTrue(new InstantCommand(() -> Robot.m_robotContainer.getSwerve().zeroGyro()));

                // systems joystick commands
        completeSpeakerShootingTrigger.onTrue(new CompleteSpeakerShootingCommand(swerve, limelight, shootingSubsystem, pitchingSubsystem, elevatorSubsystem, kickerSubsystem, shootingMath)); 
        // completeSpeakerShootingTrigger.onFalse(new InstantCommand(() -> shootingSubsystem.setShooterOutput(0)));
        shootingReverse.whileTrue(new ShootingSpeedCommand(shootingSubsystem, kickerSubsystem, 2000, 0.4));

        apmShootingTrigger.whileTrue(new CompleteAMPShootingCommand(shootingSubsystem, pitchingSubsystem));
        apmShootingTrigger.onFalse(new KickerShootingCommand(kickerSubsystem, shootingSubsystem, Constants.KICKER_OUTPUT));
        
        intakeTrigger.whileTrue(new IntakeAndTransferCommand( intakeSubsystem, transferSubsystem, shootingSubsystem, kickerSubsystem,pitchingSubsystem));
        intakeReverse.whileTrue(new IntakeBackwordsCommand(intakeSubsystem, 0.4));


        climbUpTrigger.onTrue(new OpenElevatorCommanGroup(elevatorSubsystem, pitchingSubsystem, Constants.CLIMB_ELEVATOR_HIGHT));
        climbDown.onTrue(new EleavatorDown(elevatorSubsystem, -5));
        climb.onTrue(new EleavatorClimbDown(elevatorSubsystem, -90));

        PitchTrigger.onTrue(new PitchCommand(limelight, pitchingSubsystem, elevatorSubsystem, shootingMath, shootingSubsystem));
        pitchDown.onTrue(new PitchPos(pitchingSubsystem, 0));//TODO: ANGLE = 0!!
        pitch.onTrue(new PitchPos(pitchingSubsystem, -60));

        LEDtTrigger.onTrue(new InstantCommand(() -> swerve.setLEDS(false, true, true)));

    }
}

