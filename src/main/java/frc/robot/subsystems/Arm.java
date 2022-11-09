// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  // The Romi has the left and right motors set to 
  // PWM channels 0 and 1 respectively
  private final Servo lift = new Servo(Constants.LIFT_PORT);
  private final Servo tilt = new Servo(Constants.TILT_PORT);
  private final Servo gripper = new Servo(Constants.GRIPPER_PORT);
  private final AnalogInput gripperRead = new AnalogInput(Constants.GRIPPER_FEEDBACK_PORT);
  private double liftPos;
  private double tiltPos;
  private double gripperPos;

  /** Creates a new Arm. */
  public Arm() {
    reset();
  }

  //Reset position to resting state
  public void reset(){
    liftPos = 0.5;
    tiltPos = 0.5;
    gripperPos = 0.5;
    
    lift.set(liftPos);
    tilt.set(tiltPos);
    gripper.set(gripperPos);
  }

  /**
   * Increment tilt motor position
   * 
   * @param delta Amount to change motor position
   */
  public void incrementTilt(double delta){
    tiltPos = MathUtil.clamp(tiltPos + delta, Constants.TILT_MIN, Constants.TILT_MAX);
    tilt.set(tiltPos);
  }

  /**
   * Increment lift motor position
   * 
   * @param delta Amount to change motor position
   */
  public void incrementLift(double delta){
    liftPos = MathUtil.clamp(liftPos + delta, Constants.LIFT_MIN, Constants.LIFT_MAX);
    lift.set(liftPos);
  }

  /**
   * Increment gripper motor position
   * 
   * @param delta Amount to change motor position
   */
  public void incrementGripper(double delta){
    gripperPos = MathUtil.clamp(gripperPos + delta, Constants.GRIPPER_MIN, Constants.GRIPPER_MAX);
    gripper.set(gripperPos);
  }

  // Get gripper motor position from feedback signal
  public int getGripperPos(){
    return gripperRead.getAverageValue();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
