package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.config.UniversalConfig;
import frc.robot.motor.UniversalMotor;
import frc.robot.motor.UniversalMotor.MotorBrand;

public class TestS extends SubsystemBase {
    UniversalMotor masterMotor;
    UniversalMotor followerMotor;

    public TestS() {
        masterMotor = new UniversalMotor(MotorBrand.CAN_BRUSHLESS, 30);

        followerMotor = new UniversalMotor(MotorBrand.CAN_BRUSHLESS, 32);
        followerMotor.configure(new UniversalConfig().invert(true));
        followerMotor.follow(masterMotor);
    }

    public void setVoltage(double voltage) {
        masterMotor.setVoltage(voltage);
    }
}
