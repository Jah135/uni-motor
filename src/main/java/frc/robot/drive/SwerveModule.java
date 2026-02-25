package frc.robot.drive;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.motor.UniversalMotor;

public class SwerveModule {
    static public class SwerveConstants {
        public double driveKP, driveKI, driveKD;
        public double turnKP, turnKI, turnKD;
        public double wheelRadius;

        public SwerveConstants(double driveKP, double driveKI, double driveKD, double turnKP, double turnKI, double turnKD, double wheelRadius) {
            this.driveKP = driveKP;
            this.driveKI = driveKI;
            this.driveKD = driveKD;

            this.turnKP = turnKP;
            this.turnKI = turnKI;
            this.turnKD = turnKD;

            this.wheelRadius = wheelRadius;
        }
    }
    
    public UniversalMotor driveMotor;
    public UniversalMotor turnMotor;

    SwerveConstants constants;
    PIDController turnController, driveController;

    public SwerveModule(UniversalMotor drive, UniversalMotor turn, SwerveConstants constants) {
        this.driveMotor = drive;
        this.turnMotor = turn;

        this.constants = constants;

        turnController = new PIDController(constants.turnKP, constants.turnKI, constants.turnKD);
        turnController.enableContinuousInput(0, Math.PI * 2);
        driveController = new PIDController(constants.driveKP, constants.driveKI, constants.driveKD);

    }

    public void update(double targetAngle, double targetSpeed) {
        turnMotor.setVoltage(turnController.calculate(turnMotor.getPosition() * Math.PI * 2, targetAngle));
        driveMotor.setVoltage(driveController.calculate(driveMotor.getAngularVelocity() * constants.wheelRadius, targetSpeed));
    }
}
