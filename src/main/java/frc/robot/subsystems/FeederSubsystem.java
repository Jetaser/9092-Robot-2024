// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Feeder;

public class FeederSubsystem extends SubsystemBase {

  private CANSparkMax m_feeder = new CANSparkMax(Feeder.kCANId, MotorType.kBrushless);
  private DigitalInput m_sensor = new DigitalInput(Feeder.kSensorPortId);

  public FeederSubsystem() {
    m_feeder.restoreFactoryDefaults();
    m_feeder.setInverted(false);
    m_feeder.setIdleMode(IdleMode.kBrake);
    m_feeder.setSmartCurrentLimit(Feeder.kCurrentLimit);
    m_feeder.burnFlash();

  }

  public void runManual(double _speed) {m_feeder.set(_speed);}

  public boolean hasGamePiece() {return !m_sensor.get();}

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Sensor Reading", !m_sensor.get());
  }
}
