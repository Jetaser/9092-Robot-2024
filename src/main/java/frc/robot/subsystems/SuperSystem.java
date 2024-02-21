// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Feeder;
import frc.robot.Constants.Intake;
import frc.robot.Constants.Super;

public class SuperSystem extends SubsystemBase {

  private DriveSubsystem m_robotDrive = new DriveSubsystem();
  private IntakeSubsystem m_intake = new IntakeSubsystem();
  private ShooterSubsystem m_shooter = new ShooterSubsystem();
  private FeederSubsystem m_feeder = new FeederSubsystem();

  public DriveSubsystem getDriveSubsystem(){return m_robotDrive;}
  public IntakeSubsystem getIntakeSubsystem(){return m_intake;}
  public ShooterSubsystem getShooterSubsystem(){return m_shooter;}
  public FeederSubsystem getFeederSubsystem(){return m_feeder;}

  public SuperSystem() {

  }

  public void intakeAction(int _action){
    if (_action == Super.kAcquire) {
      m_intake.runManual(Intake.kManualSpeed);
      m_feeder.runManual(Intake.kManualSpeed);
    }
    if (_action == Super.kEject) {
      m_intake.runManual(-1.0 * Intake.kManualSpeed);
      m_feeder.runManual(-1.0 * Intake.kManualSpeed);
    }
    if (_action == Super.kStop) {
      m_feeder.runManual(0);
      m_intake.runManual(0);
    }
  }

  @Override
  public void periodic() {

  }
}
