// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Indexer extends SubsystemBase {
  TalonSRX indexTalon;

  public Indexer() {
    indexTalon = new TalonSRX(4);
    indexTalon.setInverted(true);
  }

  public void setTurretMotorSpeed(double speed)
  {
    indexTalon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // controls
    if(RobotContainer.main.cross.getAsBoolean())
    {
      setTurretMotorSpeed(-.85);
    
    } else if(RobotContainer.main.square.getAsBoolean())
    {
      setTurretMotorSpeed(0);
    } else if(RobotContainer.main.triangle.getAsBoolean())
    {
      setTurretMotorSpeed(-1);
    } else if(RobotContainer.main.circle.getAsBoolean())
    {
      setTurretMotorSpeed(0);
    } else if(RobotContainer.main.getPSButton())
    {
      setTurretMotorSpeed(.7);
    }
    else if(!Robot.auton){
      setTurretMotorSpeed(0);
    }
  }
}
