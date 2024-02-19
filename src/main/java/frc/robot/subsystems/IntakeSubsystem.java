package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax m_leftmotor = new CANSparkMax(Intake.LeftCANId, MotorType.kBrushless);
  private CANSparkMax m_rightmotor = new CANSparkMax(Intake.RightCANId, MotorType.kBrushless);


  public IntakeSubsystem() {
    m_leftmotor.restoreFactoryDefaults();
    m_leftmotor.setInverted(false);
    m_leftmotor.setSmartCurrentLimit(Intake.CurrentLimit);
    m_leftmotor.setIdleMode(IdleMode.kBrake);
    m_leftmotor.burnFlash();

    m_rightmotor.restoreFactoryDefaults();
    m_rightmotor.setInverted(false);
    m_rightmotor.setSmartCurrentLimit(Intake.CurrentLimit);
    m_rightmotor.setIdleMode(IdleMode.kBrake);
    m_rightmotor.burnFlash();
  }

  public void runManual (double _speed) {
    m_leftmotor.set(_speed);
    m_rightmotor.set(_speed);
  }

  @Override
  public void periodic() {

  }
}
