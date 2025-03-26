// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Turret extends SubsystemBase {
  /** Creates a new Turret. */
  TalonSRX turretTalon;

  public Turret() {
    // turretTalon = new TalonSRX(7);
  }

  public void setTurretMotorSpeed(double speed)
  {
    // turretTalon.set(TalonSRXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // controls
   
}
}
