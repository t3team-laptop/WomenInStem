// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  // The Romi has the left and right motors set to 
  // PWM channels 0 and 1 respectively
  private final Servo lift = new Servo(Constants.Arm.LIFT_PORT);
  private final Servo tilt = new Servo(Constants.Arm.TILT_PORT);
  private final Servo gripper = new Servo(Constants.Arm.GRIPPER_PORT);
  private final AnalogInput gripperRead = new AnalogInput(Constants.Arm.GRIPPER_FEEDBACK_PORT);
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
    tiltPos = saturateLimit(tiltPos + delta, Constants.Arm.TILT_MIN, Constants.Arm.TILT_MAX);
    tilt.set(tiltPos);
  }

  /**
   * Increment lift motor position
   * 
   * @param delta Amount to change motor position
   */
  public void incrementLift(double delta){
    liftPos = saturateLimit(liftPos + delta, Constants.Arm.LIFT_MIN, Constants.Arm.LIFT_MAX);
    lift.set(liftPos);
  }

  /**
   * Increment gripper motor position
   * 
   * @param delta Amount to change motor position
   */
  public void incrementGripper(double delta){
    gripperPos = saturateLimit(gripperPos + delta, Constants.Arm.GRIPPER_MIN, Constants.Arm.GRIPPER_MAX);
    gripper.set(gripperPos);
  }

  // Get gripper motor position from feedback signal
  public int getGripperPos(){
    return gripperRead.getAverageValue();
  }

  // Limit motor ranges to avoid moving beyond safe ranges
  public double saturateLimit(double val, double l_limit, double u_limit){
    double outval = val;
    if(val > u_limit){
      outval = u_limit;
    }
    else if(val < l_limit){
      outval = l_limit;
    }
    return outval;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
