// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight extends PIDSubsystem {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry ledMode = table.getEntry("ledMode");
  NetworkTableEntry camMode = table.getEntry("camMode");
  NetworkTableEntry pipeline = table.getEntry("pipeline");
  

  
  /** Creates a new LimeLight. */
  public LimeLight() {
    super(new PIDController(Constants.kP, Constants.kI, Constants.kD));

    setSetpoint(0);
  
  }

  public void setLEDMode(int mode)
  {

    ledMode.setDouble(mode);
    /**0	use the LED Mode set in the current pipeline
       1	force off
       2	force blink
       3	force on */

  }

  public void setCameraMode(int mode)
  {
    camMode.setNumber(mode);
    camMode.setDouble(mode);
  }

  public void setPipeline(double pipelineNumber)
  {
    pipeline.setDouble(pipelineNumber);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    if(RobotContainer.main.getShareButton())
    {
      Robot.limelight = true;
      setPipeline(1);
      setLEDMode(3);
      setCameraMode(0);
      RobotContainer.drive.arcadeDrive(0,.02*x);
    } else if(!Robot.auton){
      setCameraMode(1);
      Robot.limelight = false;
      setLEDMode(1);
    }
  }

  @Override
  protected void useOutput(double output, double setpoint) {
    //pass value to rotating motor based off of distance to angle
  }

  @Override
  protected double getMeasurement() {
    // TODO Auto-generated method stub
    return tx.getDouble(0.0);
  }
}
