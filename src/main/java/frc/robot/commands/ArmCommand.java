// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmCommand extends CommandBase {
  private final Arm arm;
  private final XboxController joystick;
  /** Creates a new ArmCommand. */
  public ArmCommand(Arm a, XboxController x) {
    arm = a;
    joystick = x;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(joystick.getRawButton(Constants.BUT_LB)){
      arm.incrementLift(-Constants.Arm.SERVO_INCREMENT);
    }
    if(joystick.getRawButton(Constants.BUT_RB)){
      arm.incrementLift(Constants.Arm.SERVO_INCREMENT);
    }
    if(joystick.getRawButton(Constants.BUT_Y)){
      arm.incrementTilt(Constants.Arm.SERVO_INCREMENT);
    }
    if(joystick.getRawButton(Constants.BUT_A)){
      arm.incrementTilt(-Constants.Arm.SERVO_INCREMENT);
    }
    if(joystick.getRawButton(Constants.BUT_X)){
      arm.incrementGripper(Constants.Arm.SERVO_INCREMENT);
    }
    if(joystick.getRawButton(Constants.BUT_B)){
      arm.incrementTilt(-Constants.Arm.SERVO_INCREMENT);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
