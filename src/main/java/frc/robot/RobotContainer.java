// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain drivetrain;
  public final ArcadeDrive arcadeDrive;

  private static XboxController driverJoystick;
  JoystickButton A, B, X, Y, LB, RB, RT, LT, M1, M2;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drivetrain = new RomiDrivetrain();
    arcadeDrive = new ArcadeDrive(drivetrain, () -> driverJoystick.getRawAxis(1), () -> driverJoystick.getRawAxis(2));
    arcadeDrive.addRequirements(drivetrain);
    drivetrain.setDefaultCommand(arcadeDrive);
    

    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);
    //Declare Driver Controller Buttons
    A = new JoystickButton(driverJoystick, Constants.BUT_A);
    B = new JoystickButton(driverJoystick, Constants.BUT_B);
    X = new JoystickButton(driverJoystick, Constants.BUT_X);
    Y = new JoystickButton(driverJoystick, Constants.BUT_Y);
    LB = new JoystickButton(driverJoystick, Constants.BUT_LB);
    RB = new JoystickButton(driverJoystick, Constants.BUT_RB);
    LT = new JoystickButton(driverJoystick, Constants.LEFT_TRIG);
    RT = new JoystickButton(driverJoystick, Constants.RIGHT_TRIG);
    M1 = new JoystickButton(driverJoystick, Constants.BUT_M1);
    M2 = new JoystickButton(driverJoystick, Constants.BUT_M2);
    // Configure the button bindings
    configureButtonBindings();
  }



  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(getArcadeDriveCommand());
  }

  public Command getArcadeDriveCommand(){
    return new ArcadeDrive(drivetrain, () -> driverJoystick.getRawAxis(1), () -> driverJoystick.getRawAxis(2));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
