package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;

public class AllianceSpecs {
    public boolean isRed;
    public Pose2d speakerPos;
    public double speakerLimelightPipeline;
  
    public AllianceSpecs() {
        if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red){
            isRed = true;
            speakerPos = Constants.RED_SPEAKER_POS;
            speakerLimelightPipeline = Constants.RED_SPEAKER_LIMELIGHT_PIPELINE;
        }
        else{
            isRed = false;
            speakerPos = Constants.BLUE_SPEAKER_POS;
            speakerLimelightPipeline = Constants.BLUE_SPEAKER_LIMELIGHT_PIPELINE;
        }
    }
}