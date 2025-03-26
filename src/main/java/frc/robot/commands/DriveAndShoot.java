// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class DriveAndShoot extends Command {
  /** Creates a new DriveAndShoot. */
  Drive drive;
  Intake intake;
  Indexer indexer;
  Shooter shooter;

  public DriveAndShoot(Drive drive, Intake intake, Indexer indexer, Shooter shooter) {
    addRequirements(drive, intake, indexer, shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setIntakeSpeed(.6);
    indexer.setTurretMotorSpeed(-.6);
    shooter.setShooterSpeed(.6);
    drive.arcadeDrive(.3, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeSpeed(0);
    indexer.setTurretMotorSpeed(0);
    shooter.setShooterSpeed(0);
    drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
