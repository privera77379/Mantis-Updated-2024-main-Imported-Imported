// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  TalonSRX climberMotorLeft = new TalonSRX(15);
  TalonSRX climberMotorRight = new TalonSRX(17);

  public Climber() {
   climberMotorLeft.setInverted(true);
   climberMotorLeft.setNeutralMode(NeutralMode.Brake);
   climberMotorRight.setNeutralMode(NeutralMode.Brake);

  }


  /*
   * fix this class this whole thing is so weird
   * prints are all the same, naming is also weird
   * can't tell what anything does
   */
  public void climb(double demand)
  {
    climberMotorRight.set(TalonSRXControlMode.PercentOutput, demand);
  }

  public void release(double demand)
  {
    climberMotorLeft.set(TalonSRXControlMode.PercentOutput, demand);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(RobotContainer.main.pov0.getAsBoolean())
    {
      System.out.println("Making it");
      // JEREMY FIX THIS OR BAD
      // NAMES ARE BAD AND CONFUSING
      climb(-1);
      release(-1);//56yrftutyhi7ut768ut87iutuy87uui6y8i7il7uy87iy5r6f7tgufiunhtr6yr6r67r67r67r67r67r67rti7yi7yhi7uhuihuii
    } else if(RobotContainer.main.pov180.getAsBoolean())
    {
      release(1);
      climb(1);
    } else if(RobotContainer.main.pov90.getAsBoolean())
    {
      System.out.println("Making it");
      climb(-.5);
    } else if(RobotContainer.main.pov270.getAsBoolean())
    {
      System.out.println("Making it");
      release(-.5);
    } else{
      climberMotorRight.set(TalonSRXControlMode.PercentOutput, 0);
      climberMotorLeft.set(TalonSRXControlMode.PercentOutput, 0);

    }
  }
}
