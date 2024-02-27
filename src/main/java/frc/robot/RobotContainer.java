package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.Super;
import frc.robot.commands.FeedShooterCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.RunShooterCmd;
import frc.robot.commands.StopShooterCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SuperSystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  
  private final SendableChooser<Command> autoChooser;

  private SuperSystem m_super = new SuperSystem();
  private DriveSubsystem m_robotDrive = m_super.getDriveSubsystem();
  private IntakeSubsystem m_intake = m_super.getIntakeSubsystem();
  private ShooterSubsystem m_shooter = m_super.getShooterSubsystem();
  private FeederSubsystem m_feeder = m_super.getFeederSubsystem();

  private XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  private XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

  public RobotContainer() {

    NamedCommands.registerCommand("FeedShooter", new FeedShooterCmd(m_feeder));
    NamedCommands.registerCommand("Intake", new IntakeCmd(m_super, Super.kAcquire));
    NamedCommands.registerCommand("RunShooter", new RunShooterCmd(m_shooter));
    NamedCommands.registerCommand("StopShooter", new StopShooterCmd(m_shooter));

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);


    m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
        -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband * 0.5),
        -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband * 0.5),
        -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband * 0.1),
        false, true, true),
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

    new JoystickButton(m_operatorController, Buttons.kA).whileTrue(new IntakeCmd(m_super, Super.kAcquire));
    new JoystickButton(m_operatorController, Buttons.kB).whileTrue(new IntakeCmd(m_super, Super.kEject));
    new JoystickButton(m_operatorController, Buttons.kY).onTrue( new StopShooterCmd(m_shooter));
    new JoystickButton(m_operatorController, Buttons.kX).onTrue(new RunShooterCmd(m_shooter));
    new JoystickButton(m_operatorController, Buttons.kLB).whileTrue(new FeedShooterCmd(m_feeder));

    new JoystickButton(m_driverController, Buttons.kRB).whileTrue(
            new RunCommand(
        () -> m_robotDrive.drive(
        -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband * 0.5),
        -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband * 0.5),
        -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband * 0.1),
        true, false, true),
        m_robotDrive)); 


  }

  public Command getAutonomousCommand() {
  
    
    return autoChooser.getSelected();
  }
}
