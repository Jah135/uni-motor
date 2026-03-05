package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.MotorS;

public class MotorC extends Command {
	private MotorS subsystem;

	public MotorC(MotorS subsystem) {
		addRequirements(subsystem);
		this.subsystem = subsystem;
	}

	@Override
	public void execute() {
		subsystem.setVoltage(RobotContainer.DRIVER_CONTROLLER.getLeftY() * 3.5);
	}
}
