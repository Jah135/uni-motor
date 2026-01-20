package frc.robot.motor;

import java.util.ArrayList;

import com.ctre.phoenix6.CANBus;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.motor.interfaces.SparkMaxInterface;
import frc.robot.motor.interfaces.TalonFXInterface;
import frc.robot.config.UniversalConfig;
import frc.robot.motor.interfaces.MotorInterface;

/**
 * A generic/universal interface object for motors from different brands.
 */
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

    /**
     * Constructs a universal motor interface for the specified motor brand.
     * @param brand The brand of the motor.
     * @param deviceId The device id of the motor.
     */
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
    /**
     * Constructs a <b>configured</b> UniversalMotor interface for the specified motor brand.
     * @param brand The brand of the motor.
     * @param deviceId The device id of the motor.
     * @param config The config to apply to the motor.
     */
    public UniversalMotor(MotorBrand brand, int deviceId, UniversalConfig config) {
        this(brand, deviceId); // call other constructor, this is cursed
        this.configure(config);
    }
    
    /**
     * Constructs a TalonFX based motor with the specified CANBus.
     * @param deviceId The device id of the TalonFX motor.
     * @param bus The CANBus of the TalonFX motor.
     */
    public UniversalMotor(int deviceId, CANBus bus) {
        this.brand = MotorBrand.TALON;
        this.deviceId = deviceId;

        this.motor = new TalonFXInterface(deviceId, bus);
    }
    /**
     * Constructs a <b>configured</b> TalonFX based motor with the specified CANBus.
     * @param deviceId The device id of the TalonFX motor.
     * @param bus The CANBus of the TalonFX motor.
     * @param config The config to apply to the motor.
     */
    public UniversalMotor(int deviceId, CANBus bus, UniversalConfig config) {
        this(deviceId, bus);
        this.configure(config);
    }

    /**
     * Configures this <code>UniversalMotor</code> object with the specified config.
     * @param config The config to apply.
     */
    public void configure(UniversalConfig config) {
        motor.configure(config);
    }
    /**
     * Makes this motor start following the specified <code>master</code> motor.
     * @param master The motor to follow.
     */
    public void follow(UniversalMotor master) {
        motor.follow(master);
    }

    /**
     * Sets the voltage of this motor.
     * @param voltage The voltage to set.
     */
    public void setVoltage(double voltage) {
        followers.forEach(followerMotor -> followerMotor.setVoltage(voltage));

        motor.setVoltage(voltage);
    }
    /**
     * Returns the set voltage of this motor.
     * @return The voltage of this motor.
     */
    public double getVoltage() {
        return motor.getVoltage();
    }

    /**
     * Returns the absolute position of this motor.
     * @return The absolute position of this motor.
     */
    public double getPosition() {
        return motor.getPosition();
    }

    /**
     * Returns the temperature of this motor in Celsius.
     * @return The temperature of this motor in Celsius.
     */
    public double getTemperature() {
        return motor.getTemperature();
    }

    /**
     * Returns the device id of this motor.
     * @return The device id of this motor.
     */
    public int getDeviceId() {
        return this.deviceId;
    }
    /**
     * Returns the brand of this motor.
     * @return The brand of this motor.
     */
    public MotorBrand getMotorBrand() {
        return this.brand;
    }

    /**
     * INTERNAL METHOD; DO NOT USE
     * @param motorInterface
     */
    public void addFollower(MotorInterface motorInterface) {
        if (followers.contains(motorInterface)) {
            return;
        }

        followers.add(motorInterface);
    }
    /**
     * INTERNAL METHOD; DO NOT USE
     * @param motorInterface
     */
    public void removeFollower(MotorInterface motorInterface) {
        if (!followers.contains(motorInterface)) {
            return;
        }

        followers.remove(motorInterface);
    }
}
