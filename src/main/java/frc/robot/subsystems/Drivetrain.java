/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private WPI_TalonFX rightFront = new WPI_TalonFX(Constants.rightFrontDrive);
  private WPI_TalonFX rightBack = new WPI_TalonFX(Constants.rightBackDrive);
  private WPI_TalonFX leftFront = new WPI_TalonFX(Constants.leftFrontDrive);
  private WPI_TalonFX leftBack = new WPI_TalonFX(Constants.leftBackDrive);

  private MecanumDrive mecanumDrive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);

  public Drivetrain() {

    rightFront.configFactoryDefault();
    rightBack.configFactoryDefault();
    leftFront.configFactoryDefault();
    leftBack.configFactoryDefault();

    rightFront.setNeutralMode(NeutralMode.Brake);
    rightBack.setNeutralMode(NeutralMode.Brake);
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftBack.setNeutralMode(NeutralMode.Brake);

    // TODO: may need inversions

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive (double ySpeed, double xSpeed, double zRotation) {
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  }


}
