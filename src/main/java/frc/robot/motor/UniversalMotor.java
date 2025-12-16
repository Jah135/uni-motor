package frc.robot.motor;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.motor.interfaces.SparkMaxInterface;
import frc.robot.motor.interfaces.TalonInterface;
import frc.robot.motor.interfaces.MotorInterface;

public class UniversalMotor {
    private static MotorInterface getMotorForBrand(MotorBrand brand, int deviceId) {
        switch (brand) {
            case CAN_BRUSHED:
                return new SparkMaxInterface(deviceId, MotorType.kBrushed);
            case CAN_BRUSHLESS:
                return new SparkMaxInterface(deviceId, MotorType.kBrushless);
            case TALON:
                return new TalonInterface(deviceId);
            default:
                throw new IllegalArgumentException("Unsupported motor brand.");
        }
    }

    public enum MotorBrand {
        CAN_BRUSHED,
        CAN_BRUSHLESS,
        TALON,
    }

    private int deviceId;

    private MotorBrand brand;
    private MotorInterface motor;

    public UniversalMotor(MotorBrand brand, int deviceId) {
        this.brand = brand;
        this.deviceId = deviceId;

        this.motor = getMotorForBrand(brand, deviceId);
    }
    public UniversalMotor(MotorBrand brand, int deviceId, UniversalConfig config) {
        this(brand, deviceId); // call other constructor, this is cursed
        this.configure(config);
    }

    public void configure(UniversalConfig config) {
        motor.configure(config);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public double getTemperature() {
        return motor.getTemperature();
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public MotorBrand getMotorBrand() {
        return this.brand;
    }
}
