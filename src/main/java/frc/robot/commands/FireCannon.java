// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.Solenoid;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FireCannon extends CommandBase {
  /** Creates a new FireCannon. */
  private Solenoid[] solenoids;
  private Timer t;
  // private Date = new Date()

  public FireCannon(Solenoid[] solenoids) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.solenoids = solenoids;
    t = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for (int i = 0; i < solenoids.length; i++) {
      solenoids[i].set(true);
    }
    try {
      Thread.currentThread().sleep(150);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    for (int i = 0; i < solenoids.length; i++) {
      System.out.println("END---------------------------------------------------------");
      solenoids[i].set(false);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
