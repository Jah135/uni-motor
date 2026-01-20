package frc.robot.drive;

import frc.robot.motor.UniversalMotor;

public class SwerveModule {
    private UniversalMotor driveMotor;
    private UniversalMotor turnMotor;

    public SwerveModule(UniversalMotor drive, UniversalMotor turn) {
        this.driveMotor = drive;
        this.turnMotor = turn;
    }


}
