// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.*;
import com.ctre.phoenix6.hardware.*;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  TalonFX shooterLeft = new TalonFX(23);
  TalonFX shooterRight = new TalonFX(6);
  double speed = 0;
  public final VoltageOut m_request = new VoltageOut(0);
  public static NeutralModeValue NeutralMode;


  public Shooter() {

    shooterLeft.setInverted(false);
    shooterRight.setControl(new Follower(shooterLeft.getDeviceID(), false));
    shooterRight.setInverted(true);
    
  }

  public void setShooterSpeed(double inputSpeed)
  {
    shooterLeft.set(inputSpeed);
    shooterRight.set(inputSpeed);
  }

  @Override
  public void periodic() {
    // controls
    if(RobotContainer.main.triangle.getAsBoolean()) {
      setShooterSpeed(1);
    } else if(RobotContainer.main.cross.getAsBoolean()) {
      setShooterSpeed(.26);
    } else if(!Robot.auton){
      setShooterSpeed(0);
    }    
  }
}
