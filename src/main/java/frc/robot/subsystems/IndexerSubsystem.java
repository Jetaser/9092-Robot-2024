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
import frc.robot.Constants.indexer;

public class IndexerSubsystem extends SubsystemBase {
  private CANSparkMax m_indexer = new CANSparkMax(indexer.kIndexerMotorId, MotorType.kBrushless);
    private DigitalInput m_sensor = new DigitalInput(0);

  public IndexerSubsystem() {
    m_indexer.restoreFactoryDefaults();
    m_indexer.setInverted(false);
    m_indexer.setIdleMode(IdleMode.kBrake);
    m_indexer.setSmartCurrentLimit(indexer.kCurrentLimit);
    m_indexer.burnFlash();

  }

  public void runManual(double _speed)
   {

//    if (m_sensor.get() == true) {
       m_indexer.set(_speed);
   // }
   // else if (m_sensor.get() == false) {
   // m_indexer.set(0.0);
   // }

  }
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Sensor Reading", m_sensor.get());
  }
}
