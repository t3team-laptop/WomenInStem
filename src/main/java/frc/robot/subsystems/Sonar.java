// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sonar extends SubsystemBase {
  /** The analog input that the ultrasonic sensor is in */
  private final AnalogInput sensor;

  /**Sensor voltage to millimeter scaling factor */
  private final double scalingFactor;

  /** true if averaging oversample bits */
  private boolean average = false;

  /**
   * Analog ultrasonic sensors work by returning a voltage value that is proportional
   * to the range value, you will need to find out what the scaling factor is
   * for your specific ultrasonic sensor, this is most often found on the datasheet
   * released by the manufacturer,
   *
   * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI Analog Rangefinders</>
   *
   * @param analogChannel the channel to initialize the ultrasonic sensor on
   * @param scalingFactor the scaling factor for the ultrasonic sensor
   */
  
  
  AnalogPotentiometer pot;  
  
  /** Creates a new Sonar. */
  public Sonar(AnalogInput s, double sF) {
    sensor = s;
    scalingFactor = sF;
    //enables 2-bit averaging
    sensor.setAverageBits(2);

    /** Initializes an AnalogPotentiometer on analog port 0
     * The full range of motion (in meaningful external units) is 0-180 (in degrees)
     * The "starting point" of the motion, i.e. where the mechanism is located when the potentiometer reads 0v, is 30.
     */
    pot = new AnalogPotentiometer(sensor, 180, 30);
  }

  public double getInches(){
    return getCentimeters() / 2.54;
  }

  public double getCentimeters(){
    return get() * 10;
  }

  public Double get(){
    return average ? sensor.getAverageVoltage() * scalingFactor : sensor.getVoltage() * scalingFactor;
  }

  /**
   * Set the sensor to average the oversampled reading
   *
   * When setting to this mode, it is also important to
   * specify the amount of bits used to oversample
   *
   * @param oversampleBits the number of bits used in oversampling
   */
  public void setModeAverage(int oversampleBits){
    sensor.setOversampleBits(oversampleBits);
    average = false;
  }

  /**
   * Set the sensor to read the absolute value (this is enabled by default)
   */
  public void setModeAbsolute(){
      average = true;
  }

  /** get the analog input of the sensor */
  public AnalogInput getSensor() {
      return sensor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
