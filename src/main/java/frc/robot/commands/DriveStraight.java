// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class DriveStraight extends Command {
  /** Creates a new DriveStraight. */
  private Drive drive;
  private double move;
  private double turn;
  private Intake intake;
  double autoStart;


  int counter = 0;


  public DriveStraight(Intake intake, Drive drive, Double move, Double turn) {
    this.intake = intake;
    this.drive = drive;
    this.move = move;
    this.turn = turn;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    autoStart = Timer.getFPGATimestamp();

    intake.extendPivotArm();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setIntakeSpeed(0);
    //drive.arcadeDrive(.3, 0);
    drive.left_front_motor.set(.5);
    drive.right_front_motor.set(.5);
    System.out.println("we got here");

    if(autoStart-Timer.getFPGATimestamp()>=2);
    {
      drive.arcadeDrive(0,0);
      intake.setIntakeSpeed(0);
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
