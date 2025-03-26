// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShootBall extends Command {
  /** Creates a new Shooter. */
  private Shooter shooter;
  private Indexer indexer;
  double autoStart;
  int counter = 0;


  public ShootBall(Shooter shooterPass, Indexer indexerPass) {
    shooter = shooterPass;
    indexer = indexerPass;
    addRequirements(shooter, indexer);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    autoStart = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexer.setTurretMotorSpeed(0);
    shooter.setShooterSpeed(0);
    // runs for 2 seconds because runs once every 20 ms
    if(autoStart-Timer.getFPGATimestamp()>=4)
    {
      end(true);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.setTurretMotorSpeed(0);
    shooter.setShooterSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
