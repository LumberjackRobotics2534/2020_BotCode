/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Drive extends CommandBase {
  Drivetrain drivetrain;
  double ySpeed;
  double xSpeed;
  double zRotation;
  public Drive(Drivetrain drivetrain, double ySpeed, double xSpeed, double zRotation) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.ySpeed = ySpeed;
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.drive(ySpeed, xSpeed, zRotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}