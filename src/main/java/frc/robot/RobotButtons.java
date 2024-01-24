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
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.SwereCommands.TeleopSwerve;
import frc.robot.commands.SwereCommands.TurnToShootingCommand;
import frc.robot.commands.SwereCommands.LockWheelsCommnad;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PitchingSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.Swerve;
import frc.util.vision.Limelight;
// import frc.robot.commands.Balance;


// Yteam loadButtons
public class RobotButtons {
    public static Joystick systems = new Joystick(1);
    public static Joystick driver = new Joystick(0);

    // driver jpoystick buttons
    public static DoubleSupplier BreakValue = () -> driver.getRawAxis(XboxController.Axis.kRightTrigger.value);
    // public static Trigger forwardJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftY.value)) > 0.1);
    // public static Trigger sidesJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftX.value)) > 0.1);
    // public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    // public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    // systems joystick buttons
    
    

    /**
     * @param shootingSubsystem
     * @param collectSubsyste
     * @param armSubsystem
     * @param swerve
     */
    public void loadButtons(Swerve swerve, Limelight limelight, ShootingSubsystem shootingSubsystem, PitchingSubsystem pitchingSubsystem, IntakeSubsystem intakeSubsystem) {
        // driver joystick commands
        swerve.setDefaultCommand(
            new TeleopSwerve(
                    swerve,
                    () -> driver.getRawAxis(PS5Controller.Axis.kLeftY.value),
                    () -> driver.getRawAxis(PS5Controller.Axis.kLeftX.value),
                    () -> driver.getRawAxis(PS5Controller.Axis.kRightX.value)
                    ));
    }
    // systems joystick commands

}

