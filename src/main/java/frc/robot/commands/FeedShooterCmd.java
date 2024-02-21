// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Feeder;
import frc.robot.subsystems.FeederSubsystem;

public class FeedShooterCmd extends Command {

  private FeederSubsystem m_feeder;

  public FeedShooterCmd(FeederSubsystem _feeder) {
    m_feeder = _feeder;
    addRequirements(m_feeder);
  }


  @Override
  public void initialize() {

  }


  @Override
  public void execute() {
    m_feeder.runManual(Feeder.kSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_feeder.runManual(0);
  }


  @Override
  public boolean isFinished() {
    return !m_feeder.hasGamePiece();
  }
}
