package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

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
