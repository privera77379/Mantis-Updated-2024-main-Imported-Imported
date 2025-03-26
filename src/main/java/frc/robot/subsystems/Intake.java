// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  Compressor compressor = new Compressor(0,PneumaticsModuleType.CTREPCM);
  Solenoid leftIntakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 7);
  Solenoid climberSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 6);
  TalonSRX intakeMotor = new TalonSRX(8);
  public Intake() {
    intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
    intakeMotor.setInverted(true);
  }

  public void extendPivotArm()
  {
    //climberSolenoid.set(true);
    leftIntakeSolenoid.set(true);
  }

  public void contractPivotArm()
  {
    //climberSolenoid.set(false);
    leftIntakeSolenoid.set(false);
  }

  public void extend()
  {
    climberSolenoid.set(true);
    // leftIntakeSolenoid.set(true);
    // rightIntakeSolenoid.set(true);
  }

  public void contract()
  {
    // leftIntakeSolenoid.set(false);
    climberSolenoid.set(false);
  }

  public void setIntakeSpeed(double speed)
  {
    intakeMotor.set(TalonSRXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    if(RobotContainer.main.circle.getAsBoolean())
    {
      setIntakeSpeed(.9);
    } else if(RobotContainer.main.getCircleButton()){
      setIntakeSpeed(-.9);
    } else if(RobotContainer.main.getR1Button()){
      extend();
    } else if(RobotContainer.main.getL1Button()){
      contract();
    } else if(RobotContainer.co.getR2Button())
    {
      extendPivotArm();
    } else if(RobotContainer.main.getPSButton())
    {
      setIntakeSpeed(-.7);
    } else if(RobotContainer.co.getL2Button())
    {
      contractPivotArm(); 
    } else if(!Robot.auton){
      intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }
  }
}
