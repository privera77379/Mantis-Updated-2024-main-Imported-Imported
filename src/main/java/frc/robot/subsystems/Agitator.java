// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Agitator extends SubsystemBase {
  /** Creates a new Agitator. */
  TalonSRX turretTalon;

  public Agitator() {
    turretTalon = new TalonSRX(7);
  }

  public void setTurretMotorSpeed(double speed)
  {
    turretTalon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    if(RobotContainer.main.square.getAsBoolean()) {
      setTurretMotorSpeed(.7);
    } else if(RobotContainer.main.getPSButton())
    {
      setTurretMotorSpeed(-.7);
    } else if(RobotContainer.main.getTriangleButton())
    {
    setTurretMotorSpeed(1);
    }
    else if(RobotContainer.main.getCrossButton())
    {
    setTurretMotorSpeed(.7);
    } else if(!Robot.auton){
      setTurretMotorSpeed(0);
    }
  }
}
