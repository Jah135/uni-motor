package frc.robot.drive;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.motor.UniversalMotor;


public class SwerveModule {
    static public class SwerveControl {
        public double driveKP, driveKI, driveKD;
        public double turnKP, turnKI, turnKD;

        public SwerveControl(double driveKP, double driveKI, double driveKD, double turnKP, double turnKI, double turnKD) {
            this.driveKP = driveKP;
            this.driveKI = driveKI;
            this.driveKD = driveKD;

            this.turnKP = turnKP;
            this.turnKI = turnKI;
            this.turnKD = turnKD;
        }
    }

    private UniversalMotor driveMotor;
    private UniversalMotor turnMotor;

    private PIDController turnPID;
    private PIDController drivePID;

    private SwerveControl control;

    public SwerveModule(UniversalMotor drive, UniversalMotor turn, SwerveControl control) {
        // SwerveControl test = new 

        this.driveMotor = drive;
        this.turnMotor = turn;
        this.control = control;

        drivePID = new PIDController(control.driveKP, control.driveKI, control.driveKD);
        turnPID = new PIDController(control.turnKP, control.turnKI, control.turnKD);
        turnPID.enableContinuousInput(-Math.PI, Math.PI);
    }

    private double clamp(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public void updateControl() {
        drivePID.setPID(control.driveKP, control.driveKI, control.driveKD);
        turnPID.setPID(control.turnKP, control.turnKI, control.turnKD);
    }

    public void update(double targetAngle, double targetSpeed) {
        turnMotor.setVoltage(
            clamp(
                turnPID.calculate(turnMotor.getPosition(), targetAngle),
                -10.0,
                10.0
            )
        );

        driveMotor.setVoltage(drivePID.calculate(driveMotor.getVelocity(), targetSpeed));
    }
}
