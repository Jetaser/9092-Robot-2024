package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShootCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.pathplanner.lib.auto.AutoBuilder;


public class RobotContainer {

  private final SendableChooser<Command> autoChooser;
    
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);
    IntakeSubsystem m_intake = new IntakeSubsystem();
    ShooterSubsystem m_shooter = new ShooterSubsystem();
    //Joystick m_joystic = new Joystick(1);

  public RobotContainer() {

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
    // Configure the button bindings
    configureButtonBindings();


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
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_joystic.getY(), OIConstants.kJoysticDeadband),
                -MathUtil.applyDeadband(m_joystic.getX(), OIConstants.kJoysticDeadband),
                -MathUtil.applyDeadband(m_joystic.getZ(), OIConstants.kJoysticDeadband),
                true, true),
            m_robotDrive));
        */
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));
    new JoystickButton(m_operatorController, Button.kL1.value).whileTrue(new IntakeCmd(m_intake, Intake.kAcquire));
    new JoystickButton(m_operatorController, Button.kR1.value).whileTrue(new IntakeCmd(m_intake, Intake.kEject));

    new JoystickButton(m_operatorController, Button.kCross.value).whileTrue(new ShootCmd(m_shooter));

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
