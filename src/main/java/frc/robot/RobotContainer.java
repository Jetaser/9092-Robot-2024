package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.Super;
import frc.robot.commands.FeedShooterCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.RunShooterCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SuperSystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  
  private SuperSystem m_super = new SuperSystem();
  private DriveSubsystem m_robotDrive = m_super.getDriveSubsystem();
  private IntakeSubsystem m_intake = m_super.getIntakeSubsystem();
  private ShooterSubsystem m_shooter = m_super.getShooterSubsystem();
  private FeederSubsystem m_feeder = m_super.getFeederSubsystem();

  private XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  private XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

  public RobotContainer() {

    m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
        -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
        -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
        -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
        true, true),
        m_robotDrive));
        
    /* 
    m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
        -MathUtil.applyDeadband(m_joystic.getY(), OIConstants.kJoysticDeadband),
        -MathUtil.applyDeadband(m_joystic.getX(), OIConstants.kJoysticDeadband),
        -MathUtil.applyDeadband(m_joystic.getZ(), OIConstants.kJoysticDeadband),
        true, true),
        m_robotDrive));
    */

    new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));

    new JoystickButton(m_operatorController, Button.kL1.value).whileTrue(new IntakeCmd(m_super, Super.kAcquire));
    new JoystickButton(m_operatorController, Button.kR1.value).whileTrue(new IntakeCmd(m_super, Super.kEject));

    new JoystickButton(m_operatorController, Button.kCross.value).whileTrue(new RunShooterCmd(m_shooter));

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
