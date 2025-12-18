package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

public class TestC extends Command {
    private TestS subsystem;

    public TestC(TestS subsystem) {
        addRequirements(subsystem);
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        subsystem.setVoltage(RobotContainer.DRIVER_CONTROLLER.getLeftY() * 3.5);
    }
}
