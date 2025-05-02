// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.controls.*;
import com.ctre.phoenix6.hardware.*;
import com.ctre.phoenix.motorcontrol.*;
import frc.robot.util.VorTXController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.util.VortxMath;

public class Drive extends SubsystemBase {
  // create drivebase motors
  public static TalonFX right_front_motor = new TalonFX(10);
  public static TalonFX right_back_motor = new TalonFX(1); 
  public static TalonFX left_front_motor = new TalonFX(2);    
  public static TalonFX left_back_motor = new TalonFX(3); 
  public final VoltageOut m_request = new VoltageOut(0);

  private double move = 0;
  private double rotate = 0;

  public Drive(double move, double rotate) {
    config();
    
  }

  public void config()
  {
    // do you need that????????????????????????????????/
    // why do you need to set it to NOT inverted if that's how it already is
    right_front_motor.setInverted(false);   
    left_front_motor.setInverted(true);   
    left_back_motor.setInverted(true);

    right_back_motor.setControl(new Follower(right_back_motor.getDeviceID(), false));
    left_back_motor.setControl(new Follower(left_front_motor.getDeviceID(), false));
  }

  public void test()
  {
    right_front_motor.set(.3);
  }

  public void tankDrive(double left, double right)
  {
      right_front_motor.set(-right);
      left_front_motor.set(-left);
  }

  public void autoConfig()
  {
    right_back_motor.setControl(new Follower(right_back_motor.getDeviceID(), false));
    left_back_motor.setControl(new Follower(left_front_motor.getDeviceID(), false));
    right_front_motor.setInverted(false);
    left_front_motor.setInverted(false);
  }

  public void setDrive(double move, double rotation)
  {
    this.move = move;
    this.rotate = rotation;
  }

  public void arcadeDrive(double move, double rotation)
  {
    // This is defining the speed of both sides of the drive train based off of direction and rotation values (right is positive, left is negative)
    right_front_motor.set(move - rotation);
    left_front_motor.set(move + rotation);
   
  }

   public void racingDrive(double move, double rotation)
  {
    // This is defining the speed of both sides of the drive train based off of direction and rotation values (right is positive, left is negative)
    right_front_motor.set(move - rotation);
    left_front_motor.set(move + rotation);
  }
  @Override
  public void periodic() {


    if(RobotContainer.main.r2.getAsBoolean())
    {
    arcadeDrive(0.5+(0-VortxMath.applyDeadzone(RobotContainer.main.getLeftY(),Constants.Ydeadzone)/2), VortxMath.applyDeadzone(RobotContainer.main.getLeftX(),Constants.Xdeadzone)/1.15);
    } 
    else  if(RobotContainer.main.l2.getAsBoolean()){
      arcadeDrive(-0.5+(VortxMath.applyDeadzone(RobotContainer.main.getLeftY(),Constants.Ydeadzone)/2), VortxMath.applyDeadzone(RobotContainer.main.getLeftX(),Constants.Xdeadzone)/1.15);
      } 
    
      else if(!Robot.auton && !Robot.limelight){
     
     //Rivera Drive below
        double triggerSpeed = (RobotContainer.main.getL2Axis() - RobotContainer.main.getRightX());
    racingDrive(VortxMath.applyDeadzone(triggerSpeed, Constants.Ydeadzone), VortxMath.applyDeadzone(RobotContainer.main.getLeftX(),Constants.Xdeadzone));
    
    //Tank Drive below
    //tankDrive(RobotContainer.main.getLeftY(),RobotContainer.main.getRightY());

    //Arcade Drive below(one stick)
     //arcadeDrive(RobotContainer.main.getLeftY()*-1,RobotContainer.main.getLeftX());

     //Arcade Drive below(split sticks)
    //arcadeDrive(RobotContainer.main.getLeftY()*-1,RobotContainer.main.getRightR2());
  //}

    
    }
    /*else if(!Robot.auton && !Robot.limelight){
     double triggerSpeed = (RobotContainer.main.getR2Axis() -(- RobotContainer.main.getL2Axis()));
    racingDrive(VortxMath.applyDeadzone(-triggerSpeed, Constants.Ydeadzone), VortxMath.applyDeadzone(RobotContainer.main.getLeftX(),Constants.Xdeadzone));
    }*/
/* 
         else if(!Robot.auton && !Robot.limelight)
         {
     arcadeDrive((VortxMath.applyDeadzone(RobotContainer.main.getLeftY()*-1,Constants.Ydeadzone)/2), VortxMath.applyDeadzone(RobotContainer.main.getLeftX(),Constants.Xdeadzone)/1.15);
   }
*/
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
