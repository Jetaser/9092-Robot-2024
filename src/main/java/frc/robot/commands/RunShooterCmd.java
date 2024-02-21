// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RunShooterCmd extends Command {

  private ShooterSubsystem m_shooter; 

  public RunShooterCmd(ShooterSubsystem _shooter) {
    m_shooter = _shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_shooter.runManual();
  }

 
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
