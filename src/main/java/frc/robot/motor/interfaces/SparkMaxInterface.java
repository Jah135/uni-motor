package frc.robot.motor.interfaces;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.config.UniversalConfig;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class SparkMaxInterface extends MotorInterface {
    private SparkMax motor;
    private SparkAbsoluteEncoder encoder;

    public SparkMaxInterface(int deviceId, MotorType motorType) {
        this.motor = new SparkMax(deviceId, motorType);
        this.encoder = motor.getAbsoluteEncoder();
    }

    public void configure(UniversalConfig config) {
        SparkMaxConfig sparkConfig = new SparkMaxConfig();
        // sparkConfig.follow(config._followId); // thank you revrobotics for having a
        // follow method
        sparkConfig.inverted(config._isInverted);
        sparkConfig.idleMode(IdleMode.fromId(config._idleMode.value));
        sparkConfig.voltageCompensation(config._voltageCompensation);
        sparkConfig.smartCurrentLimit(config._currentLimit);

        motor.configure(sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    // should this modify the config to use follow?
    // i'm just gonna make it use the default implementation for all interfaces
    // public void follow(UniversalMotor master) {

    // }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public double getVoltage() {
        return motor.getBusVoltage() * motor.getAppliedOutput();
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public double getTemperature() {
        return motor.getMotorTemperature();
    }
}
