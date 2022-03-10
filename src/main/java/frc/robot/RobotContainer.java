// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CompressorConfigType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FireCannon;
import frc.robot.subsystems.ExampleSubsystem;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private static WPI_VictorSPX l1,l2, r1,r2;
  public static DifferentialDrive d;
  public static Joystick stick;
  private static JoystickButton btn2;
  private static JoystickButton btn8;
  private static JoystickButton btn10;
  private static JoystickButton btn12;
  private static JoystickButton btn1;
  private static DriveCommand driveCommand;
  public static Solenoid leftValve, middleValve, rightValve;
  public static FireCannon fireLeft, fireRight, fireMiddle, fireAll;
  public static Compressor c;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    c = new Compressor(PneumaticsModuleType.CTREPCM);
    c.start();
    
    driveCommand = new DriveCommand();
    stick = new Joystick(0);
    btn2 = new JoystickButton(stick, 2);
    btn8 = new JoystickButton(stick, 8);
    btn10 = new JoystickButton(stick, 10);
    btn12 = new JoystickButton(stick, 12);
    btn1 = new JoystickButton(stick, 1);
    try {
      l1 = new WPI_VictorSPX(Constants.l1);
      l2 = new WPI_VictorSPX(Constants.l2);
      r1 = new WPI_VictorSPX(Constants.r1);
      r2 = new WPI_VictorSPX(Constants.r2);
      l2.follow(l1);  
      r2.follow(r1);
    } catch(Exception e){}
    System.out.println("LEFT----------------------------------------------------------------");
    leftValve = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.v_left);
    System.out.println("LEFT----------------------------------------------------------------");
    System.out.println("MID----------------------------------------------------------------");
    middleValve = new Solenoid(PneumaticsModuleType.CTREPCM,Constants.v_middle);
    System.out.println("MID----------------------------------------------------------------");
    System.out.println("RIGHT----------------------------------------------------------------");

    rightValve = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.v_right);
    System.out.println("RIGHT----------------------------------------------------------------");
    rightValve.set(false);
    middleValve.set(false);
    leftValve.set(false);
    fireAll = new FireCannon(new Solenoid[] {leftValve, middleValve, rightValve});
    fireLeft = new FireCannon(new Solenoid[]{leftValve});
    fireMiddle = new FireCannon(new Solenoid[]{middleValve});
    fireRight = new FireCannon(new Solenoid[]{rightValve});
    d = new DifferentialDrive(l1, r1);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   // c.start();
    System.out.println(c.enabled());
    btn2.whenPressed(driveCommand);
    btn1.whenPressed(fireAll);
    btn8.whenPressed(fireLeft);
    btn10.whenPressed(fireMiddle);
    btn12.whenPressed(fireRight);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
