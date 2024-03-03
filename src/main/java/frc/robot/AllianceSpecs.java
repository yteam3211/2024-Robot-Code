package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import frc.util.vision.Limelight;

public class AllianceSpecs {
    public static boolean isRed;
    public static DoubleSupplier poseY;
    public static DoubleSupplier poseX;
    public static double AMPAngle;
    
  
    public AllianceSpecs(Limelight limelight) {
        if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red){
            isRed = true;
            poseX = () -> limelight.getRedBotpose()[0];
            poseY = () -> limelight.getRedBotpose()[1];
            AMPAngle = Constants.RED_AMP_ANGLE;
        }
        else{
            isRed = false;
            poseX = () -> limelight.getBlueBotpose()[0];
            poseY = () -> limelight.getBlueBotpose()[1];
            AMPAngle = Constants.BLUE_AMP_ANGLE;
        }
    }
}