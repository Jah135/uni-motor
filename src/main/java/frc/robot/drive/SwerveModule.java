package frc.robot.drive;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.motor.UniversalMotor;

public class SwerveModule {
    static public class SwerveTuning {
        public double driveKP, driveKI, driveKD;
        public double turnKP, turnKI, turnKD;

        public SwerveTuning(double driveKP, double driveKI, double driveKD, double turnKP, double turnKI, double turnKD) {
            this.driveKP = driveKP;
            this.driveKI = driveKI;
            this.driveKD = driveKD;

            this.turnKP = turnKP;
            this.turnKI = turnKI;
            this.turnKD = turnKD;
        }
    }
    
    public UniversalMotor driveMotor;
    public UniversalMotor turnMotor;

    PIDController turnController, driveController;

    public SwerveModule(UniversalMotor drive, UniversalMotor turn, SwerveTuning tuning) {
        this.driveMotor = drive;
        this.turnMotor = turn;

        turnController = new PIDController(tuning.turnKP, tuning.turnKI, tuning.turnKD);
        turnController.enableContinuousInput(0, Math.PI * 2);
        driveController = new PIDController(tuning.driveKP, tuning.driveKI, tuning.driveKD);
    }

    public void update(double targetAngle, double targetSpeed) {
        
    }
}
