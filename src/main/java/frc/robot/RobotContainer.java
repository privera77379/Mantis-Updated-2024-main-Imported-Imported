// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.Agitator;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;
import frc.robot.util.VorTXController;
import edu.wpi.first.wpilibj2.command.Command;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Drive drive = new Drive(0,0);

  static final Agitator agitator = new Agitator();
  //private final LimeLight limeLight = new LimeLight();

  //private final Target target = new Target(limeLight);

  //private final Turret turret = new Turret();

  static final Intake intake = new Intake();
  static final Indexer indexer = new Indexer();

  static final Shooter shooter = new Shooter();

  static final Climber climber = new Climber();

  static final LimeLight limelight = new LimeLight();


 
  // private final DriveStraight m_DriveStraight = new DriveStraight(intake, drive, .3, 0.0);
  // private final ShootBall m_shootBall = new ShootBall(shooter, indexer);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    //limeLight.setDefaultCommand(new Target(limeLight));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //binding triggers to arcade drive behavior
    //main.square.whenPressed(new Target(limeLight));
    //main.circle.toggleWhenPressed(new ShootBall(shooter, indexer));

  }


  /**
   * Use thisto pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
    //return m_DriveStraight;
  }

  public DriveStraight getDriveAutoCommand()
  {
    return null;
    // return m_DriveStraight;
  } 

  public ShootBall getShootBallCommand()
  {
    return null;
    // return m_shootBall;
  } 

  public static VorTXController main = new VorTXController(0);
  public static VorTXController co = new VorTXController(1);
  public static PS4Controller c_shooterTest = new PS4Controller(2);
  


}
