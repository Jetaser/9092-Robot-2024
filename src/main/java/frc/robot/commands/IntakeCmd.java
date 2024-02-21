// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Super;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SuperSystem;

public class IntakeCmd extends Command {

  private SuperSystem m_super;
  private FeederSubsystem m_feeder;
  private IntakeSubsystem m_intake;
  private int m_action;

  public IntakeCmd(SuperSystem _super, int _action) {
    m_super = _super;
    m_feeder = m_super.getFeederSubsystem();
    m_intake = m_super.getIntakeSubsystem();
    addRequirements(m_intake);
    addRequirements(m_feeder);
    m_action = _action;
  }


  @Override
  public void initialize() {}


  @Override
  public void execute() {
    m_super.intakeAction(m_action);
  }


  @Override
  public void end(boolean interrupted) {
    m_super.intakeAction(Super.kStop);
  }


  @Override
  public boolean isFinished() {
    return m_feeder.hasGamePiece();
  }
}
