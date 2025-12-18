package frc.robot.motor;

import java.util.ArrayList;

import com.ctre.phoenix6.CANBus;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.motor.interfaces.SparkMaxInterface;
import frc.robot.motor.interfaces.TalonFXInterface;
import frc.robot.config.UniversalConfig;
import frc.robot.motor.interfaces.MotorInterface;

public class UniversalMotor {
    public enum MotorBrand {
        CAN_BRUSHED,
        CAN_BRUSHLESS,
        TALON,
    }

    private ArrayList<MotorInterface> followers;
    private int deviceId;

    private MotorBrand brand;
    private MotorInterface motor;

    // generic motor constructors
    public UniversalMotor(MotorBrand brand, int deviceId) {
        this.brand = brand;
        this.deviceId = deviceId;

        switch (brand) {
            case CAN_BRUSHED:
                this.motor = new SparkMaxInterface(deviceId, MotorType.kBrushed);
            case CAN_BRUSHLESS:
                this.motor = new SparkMaxInterface(deviceId, MotorType.kBrushless);
            case TALON:
                this.motor = new TalonFXInterface(deviceId);
            default:
                throw new IllegalArgumentException("Unsupported motor brand.");
        }
    }
    public UniversalMotor(MotorBrand brand, int deviceId, UniversalConfig config) {
        this(brand, deviceId); // call other constructor, this is cursed
        this.configure(config);
    }
    
    // CANBus motor constructors (basically just talons)
    public UniversalMotor(int deviceId, CANBus bus) {
        this.brand = MotorBrand.TALON;
        this.deviceId = deviceId;

        this.motor = new TalonFXInterface(deviceId, bus);
    }
    public UniversalMotor(int deviceId, CANBus bus, UniversalConfig config) {
        this(deviceId, bus);
        this.configure(config);
    }

    public void configure(UniversalConfig config) {
        motor.configure(config);
    }
    public void follow(UniversalMotor master) {
        motor.follow(master);
    }

    public void setVoltage(double voltage) {
        followers.forEach(followerMotor -> followerMotor.setVoltage(voltage));

        motor.setVoltage(voltage);
    }
    public double getVoltage() {
        return motor.getVoltage();
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

    public void addFollower(MotorInterface motorInterface) {
        if (followers.contains(motorInterface)) {
            return;
        }

        followers.add(motorInterface);
    }
    public void removeFollower(MotorInterface motorInterface) {
        if (!followers.contains(motorInterface)) {
            return;
        }

        followers.remove(motorInterface);
    }
}
