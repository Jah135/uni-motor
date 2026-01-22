package frc.robot.motor.interfaces;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;

import frc.robot.config.UniversalConfig;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class SparkMaxInterface implements MotorInterface {
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

        motor.configure(sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public double getVoltage() {
        return motor.getBusVoltage() * motor.getAppliedOutput();
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    public double getTemperature() {
        return motor.getMotorTemperature();
    }
}
