// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */



public class Robot extends TimedRobot {
    XboxController robotController = new XboxController(constants.DifferentialDrive.port);

    SparkMax leftmotor1 =
        new SparkMax(constants.DifferentialDrive.leftmotor1, MotorType.kBrushless);
    SparkMax leftmotor2 =
        new SparkMax(constants.DifferentialDrive.leftmotor2, MotorType.kBrushless);
    SparkMax rightmotor1 =
        new SparkMax(constants.DifferentialDrive.rightmotor1, MotorType.kBrushless);
    SparkMax rightmotor2 =
        new SparkMax(constants.DifferentialDrive.rightmotor2, MotorType.kBrushless);

    DifferentialDrive Drive = new DifferentialDrive(leftmotor1, rightmotor1);

    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();



    /** Called once at the beginning of the robot program. */
    public Robot() {}

    @Override
    public void robotInit() {
        leftFollowerConfig.follow(leftmotor1);
        rightFollowerConfig.follow(rightmotor1);

        rightLeaderConfig.inverted(true);

        leftmotor2.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightmotor2.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightmotor1.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
     

    }


    /** This function is run once each time the robot enters autonomous mode. */
    @Override
    public void autonomousInit() {
    
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {

     }

    /** This function is called once each time the robot enters teleoperated mode. */
    @Override
    public void teleopInit() {}

    /** This function is called periodically during teleoperated mode. */
    @Override
    public void teleopPeriodic() {

        Drive.arcadeDrive(-robotController.getLeftY(), -robotController.getRightX());
    }

    /** This function is called once each time the robot enters test mode. */
    @Override
    public void testInit() {}

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
