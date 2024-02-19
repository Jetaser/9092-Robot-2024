package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Intake;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends Command {

  private IntakeSubsystem m_intake;
  private int m_action;
  private double speed;
  private IndexerSubsystem m_indexer;

  public IntakeCmd(IntakeSubsystem _intake, int _action, IndexerSubsystem _indexer) {
    m_intake = _intake;
    m_action = _action;
    m_indexer = _indexer;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    if(m_action == Intake.kAcquire) {speed = Intake.kManualSpeed;}
    if(m_action == Intake.kEject) {speed = -1.0 * Intake.kManualSpeed;}
  }

  
  @Override
  public void execute() {
    m_indexer.runManual(speed);
    m_intake.runManual(speed);
  }


  @Override
  public void end(boolean interrupted) {
    m_indexer.runManual(0);
    m_intake.runManual(0);
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}

