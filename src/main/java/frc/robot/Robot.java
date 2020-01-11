
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot {

  I2C.Port i2cPort = I2C.Port.kOnboard;
  ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  ColorMatch colors = new ColorMatch();
  Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  XboxController controller = new XboxController(0);

  //Mecanum Practice Bot Motors
  /*WPI_TalonSRX frontRight = new WPI_TalonSRX(1); 
  WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
  WPI_TalonSRX backLeft = new WPI_TalonSRX(4);
  WPI_TalonSRX backRight = new WPI_TalonSRX(2);
  SpeedControllerGroup left;
  SpeedControllerGroup right;
  DifferentialDrive driveTrain;*/

  double rightStickX= 0.0;
  double leftStickX = 0.0;
  double leftStickY = 0.0;
  double deadzone = 0.3;
  boolean buttonA = false;
  boolean buttonB = false;
  
  //Shooter Testing
  /*WPI_TalonSRX topFlyWheel = new WPI_TalonSRX(0);
  WPI_TalonSRX bottomFlyWheel = new WPI_TalonSRX(1);
  WPI_TalonSRX hoodWheel = new WPI_TalonSRX(2);*/

  //6-Wheel Arcade Drive Bot
  WPI_TalonSRX topRight = new WPI_TalonSRX(1);
  WPI_TalonSRX bottomRight = new WPI_TalonSRX(2);
  WPI_TalonSRX topLeft = new WPI_TalonSRX(3);
  WPI_TalonSRX bottomLeft = new WPI_TalonSRX(4);
  SpeedControllerGroup left;
  SpeedControllerGroup right;
  DifferentialDrive driveTrain;

  @Override
  public void robotInit() {
    
    //Mecanum Practice Bot
    /*backLeft.setInverted(true);
    backRight.setInverted(true);
    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
    driveTrain = new DifferentialDrive(left, right);*/

    //6-Wheel Arcade Drive Bot
    left = new SpeedControllerGroup(topLeft, bottomLeft);
    right = new SpeedControllerGroup(topRight, bottomRight);
    driveTrain = new DifferentialDrive(left, right);

    colors.addColorMatch(blueTarget);
    colors.addColorMatch(greenTarget);
    colors.addColorMatch(redTarget);
    colors.addColorMatch(yellowTarget); 
  }

  @Override
  public void robotPeriodic() {
    
    Color detectedColor = colorSensor.getColor();
    String colorString;
    ColorMatchResult match = colors.matchClosestColor(detectedColor);

    if (match.color == blueTarget) {
      colorString = "Blue";
    } else if (match.color == redTarget) {
      colorString = "Red";
    } else if (match.color == greenTarget) {
      colorString = "Green";
    } else if (match.color == yellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    
    rightStickX = controller.getRawAxis(4);
    leftStickX = controller.getRawAxis(0);
    leftStickY = controller.getRawAxis(1);
    if (Math.abs(rightStickX) < deadzone){
      rightStickX = 0;
    }
    if (Math.abs(leftStickX) < deadzone){
      leftStickX = 0;
    }
    if (Math.abs(leftStickY) < deadzone){
      leftStickY = 0;
    }
    //Mecanum Arcade Drive
    //driveTrain.arcadeDrive(leftStickY, -rightStickX);

    //Shooter Code
    /*buttonA = controller.getRawButton(1);
    buttonB = controller.getRawButton(2);
    if(buttonA == true){
      topFlyWheel.set(1.0);
      bottomFlyWheel.set(1.0);
    } else if(buttonB == true){
      hoodWheel.set(1.0);
    } else{
      topFlyWheel.set(0.0);
      bottomFlyWheel.set(0.0);
      hoodWheel.set(0.0);
    }*/

    //6-Wheel Arcade Drive
    driveTrain.arcadeDrive(leftStickY, leftStickX);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

  }

  
  @Override
  public void teleopPeriodic() {
  }


  @Override
  public void testPeriodic() {
  }
}
