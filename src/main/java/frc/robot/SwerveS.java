package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.drive.SwerveModule;
import frc.robot.drive.SwerveModule.SwerveTuning;
import frc.robot.motor.UniversalMotor;
import frc.robot.motor.UniversalMotor.MotorBrand;

public class SwerveS extends SubsystemBase {
    private SwerveModule module;

    public SwerveS() {
        UniversalMotor driveMotor = new UniversalMotor(MotorBrand.TALON, 0);
        UniversalMotor turnMotor = new UniversalMotor(MotorBrand.TALON, 1);

        SwerveTuning control = new SwerveTuning(0, 0, 0, 0, 0, 0);
        module = new SwerveModule(driveMotor, turnMotor, control);
    }

    public void set(double angle, double speed) {
        module.update(angle, speed);
    }
}
