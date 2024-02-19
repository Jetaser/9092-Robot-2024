package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.shooter;

public class ShooterSubsystem extends SubsystemBase {

  private CANSparkFlex m_leftMotor = new CANSparkFlex(shooter.kLeftMotorControllerId, MotorType.kBrushless);
  private CANSparkFlex m_rightMotor = new CANSparkFlex(shooter.kRightMotorControllerId, MotorType.kBrushless);

  private RelativeEncoder m_leftEncoder = m_leftMotor.getEncoder();
  private RelativeEncoder m_rightEncoder = m_rightMotor.getEncoder();

  private double m_setpoint = 0;

  public ShooterSubsystem() {
    m_leftMotor.restoreFactoryDefaults();
    m_leftMotor.setSmartCurrentLimit(shooter.kSparkFlexCurrentLimit);
    m_leftMotor.setInverted(shooter.kLeftIsInverted);
    m_leftMotor.setIdleMode(IdleMode.kCoast);

    m_rightMotor.restoreFactoryDefaults();
    m_rightMotor.setSmartCurrentLimit(shooter.kSparkFlexCurrentLimit);
    m_rightMotor.setInverted(shooter.kRightIsInverted);
    m_rightMotor.setIdleMode(IdleMode.kCoast);

    SmartDashboard.putNumber("Setpoint", m_setpoint);
  }

  public void runManual () {
    m_leftMotor.setVoltage(12.0 * m_setpoint / 6784.0 + 12 * .0002 * (m_setpoint - m_leftEncoder.getVelocity()));
    m_rightMotor.setVoltage(12.0 * m_setpoint / 6784.0 + 12 * .0002 * (m_setpoint - m_rightEncoder.getVelocity()));
  }

  public void stop() {
    m_leftMotor.setVoltage(0);
    m_rightMotor.setVoltage(0);
  }

  @Override
  public void periodic() {
    m_setpoint = SmartDashboard.getNumber("Setpoint", 0);
    SmartDashboard.putNumber("Left RPM", m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right RPM", m_rightEncoder.getVelocity());
  }

}
