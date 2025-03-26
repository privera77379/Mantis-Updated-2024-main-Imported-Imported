// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static boolean auton = false;
  public static boolean limelight = false;
  private Command m_autonomousCommand;
  public static SendableChooser<Integer> autoMode;


  private RobotContainer m_robotContainer;
  // DriveStraight m_driveAuto;
  // ShootBall m_shooterAuto;
  // Turret turret = new Turret();
  // Shooter shooter = new Shooter();
  // Indexer indexer = new Indexer();
  // Drive drive = new Drive(0, 0);
  // Climber climber = new Climber();
  double autoStartTime;


  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
    autoMode = new SendableChooser<>();
    autoMode.setDefaultOption("3 Ball Auto", 3);
    autoMode.addOption("2 Ball Auto With Turn", 2);
    autoMode.addOption("2 Ball Auto Straight", 1);
    SmartDashboard.putData(autoMode);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    


   autoStartTime = Timer.getFPGATimestamp(); 
   auton = true;
    RobotContainer.drive.config();
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // m_driveAuto = m_robotContainer.getDriveAutoCommand();
    // m_shooterAuto = m_robotContainer.getShootBallCommand();
    

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   // m_autonomousCommand.schedule();
    //   // m_shooterAuto.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    
    if(time-autoStartTime<1)
    {
      RobotContainer.intake.extendPivotArm();
      RobotContainer.drive.arcadeDrive(.275, 0);
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
    } else if(time-autoStartTime<1.25 && time-autoStartTime>1 && (autoMode.getSelected() == 3 || autoMode.getSelected() == 2)){
      RobotContainer.drive.arcadeDrive(-.1, .305);
      RobotContainer.agitator.setTurretMotorSpeed(0);
    } else if(time-autoStartTime<1.25 && time-autoStartTime>1 && autoMode.getSelected() == 1){
      RobotContainer.drive.arcadeDrive(-.1, 0.0);
      RobotContainer.agitator.setTurretMotorSpeed(0);
    } else if(time-autoStartTime<4 && time-autoStartTime>1.25){
      RobotContainer.drive.arcadeDrive(0, 0);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
      RobotContainer.shooter.setShooterSpeed(.5);
    } else if(time-autoStartTime<5.5 && time-autoStartTime>4 && (autoMode.getSelected() == 1 || autoMode.getSelected() == 2)){
      RobotContainer.shooter.setShooterSpeed(0);
      RobotContainer.drive.arcadeDrive(.3, 0.0);
      RobotContainer.intake.setIntakeSpeed(0);
      RobotContainer.intake.contractPivotArm();
      RobotContainer.agitator.setTurretMotorSpeed(0);
    } else if(time-autoStartTime<5.270 && time-autoStartTime>4 && autoMode.getSelected() == 3){
      RobotContainer.drive.arcadeDrive(0, .19);
      RobotContainer.shooter.setShooterSpeed(0);
      RobotContainer.indexer.setTurretMotorSpeed(0);
      RobotContainer.agitator.setTurretMotorSpeed(0);
    } else if(time-autoStartTime<6.345 && time-autoStartTime>5.345 && autoMode.getSelected() == 3){
      RobotContainer.drive.arcadeDrive(.75, 0);
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
    } else if(time-autoStartTime<7.345 && time-autoStartTime>6.345 && autoMode.getSelected() == 3){
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.drive.arcadeDrive(.12, 0);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
    } else if(time-autoStartTime<8.075 && time-autoStartTime>7.345 && autoMode.getSelected() == 3){
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.drive.arcadeDrive(0, -.2405);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
      RobotContainer.shooter.setShooterSpeed(0);
    } else if(time-autoStartTime<8.3 && time-autoStartTime>8.15 && autoMode.getSelected() == 3){
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.drive.arcadeDrive(-.1, 0);
      RobotContainer.indexer.setTurretMotorSpeed(.7);
    } else if(time-autoStartTime<9.75 && time-autoStartTime>8.3 && autoMode.getSelected() == 3){
      RobotContainer.intake.setIntakeSpeed(.9);
      RobotContainer.drive.arcadeDrive(-.1, 0);
      RobotContainer.indexer.setTurretMotorSpeed(.3);
      //RobotContainer.shooter.setShooterSpeed(.55);
    } else if(time-autoStartTime<11.25 && time-autoStartTime>9.95 && autoMode.getSelected() == 3){
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
      RobotContainer.shooter.setShooterSpeed(.5);
    } else if(time-autoStartTime<15 && time-autoStartTime>11.25 && autoMode.getSelected() == 3){
      //RobotContainer.drive.arcadeDrive(.75, 0);
      RobotContainer.agitator.setTurretMotorSpeed(.7);
      RobotContainer.indexer.setTurretMotorSpeed(-.85);
      RobotContainer.shooter.setShooterSpeed(.5);
      RobotContainer.intake.setIntakeSpeed(.9);
     
    } else {
      RobotContainer.drive.arcadeDrive(0, 0);
      RobotContainer.intake.setIntakeSpeed(0);
      RobotContainer.shooter.setShooterSpeed(0);
      RobotContainer.indexer.setTurretMotorSpeed(0);
      RobotContainer.agitator.setTurretMotorSpeed(0);
    }
    // DriveStraight m_driveAuto = m_robotContainer.getDriveAutoCommand();
    // m_driveAuto.schedule();

    //ShootBall m_shooterAuto = m_robotContainer.getShootBallCommand();
    // m_shooterAuto.schedule();
    // if(m_shooterAuto.isFinished())
    // {
    //   m_driveAuto.schedule();
    // }
    // if(m_driveAuto.isFinished())
    // {
    //   m_shooterAuto.schedule();
    // }
  }

  @Override
  public void teleopInit() {
    auton = false;
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    // turret is actually indexer is stupid name 
    //turret.setTurretMotorSpeed(.10);
    // TalonFX talonLeft = new TalonFX(5);
    // TalonFX talonRight = new TalonFX(6);


    // talonRight.setInverted(true);
    // talonRight.follow(talonLeft);
    // talonLeft.set(TalonFXControlMode.PercentOutput, .75);
    // shooter.setShooterSpeed(.75);
    //intake.setIntakeSpeed(1);
    // indexer.setTurretMotorSpeed(-.5);
    
    // climber.climb(.5);
    

  }
}