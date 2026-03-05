package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveS;

public class SwerveC extends Command {
    private SwerveS subsystem;

    public SwerveC(SwerveS subsystem) {
        addRequirements(subsystem);
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        // subsystem.setVoltage(RobotContainer.DRIVER_CONTROLLER.getLeftY() * 3.5);
        subsystem.set(0, 1.0);
    }
}
