// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class ArcadeDrive extends CommandBase {
  public final RomiDrivetrain drivetrain;
  public final Supplier<Double> xaxisSpeedSupplier;
  public final Supplier<Double> zaxisRotateSupplier;
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(RomiDrivetrain dt, Supplier<Double> x, Supplier<Double> z) {
    drivetrain = dt;
    xaxisSpeedSupplier = x;
    zaxisRotateSupplier = z;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.arcadeDrive(xaxisSpeedSupplier.get(), zaxisRotateSupplier.get());
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
