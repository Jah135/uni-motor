package frc.robot.drive;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.motor.UniversalMotor;

public class SwerveModule {
    private UniversalMotor driveMotor;
    private UniversalMotor turnMotor;

    private PIDController turnController;
    private PIDController driveController;

    public SwerveModule(UniversalMotor drive, UniversalMotor turn) {
        this.driveMotor = drive;
        this.turnMotor = turn;

        this.driveController = new PIDController(0, 0, 0);
        this.turnController = new PIDController(0, 0, 0);
    }

    public double getTurnAngle() {
        return turnMotor.getPosition();
    }

    public void setTurnVoltage(double voltage) {
        turnMotor.setVoltage(voltage);
    }

    public void setDriveVoltage(double voltage) {
        driveMotor.setVoltage(voltage);
    }

    public double getDriveVelocity() {
        return driveMotor.getVelocity();
    }
}
