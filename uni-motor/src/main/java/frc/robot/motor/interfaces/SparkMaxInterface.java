package frc.robot.motor.interfaces;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.motor.UniversalConfig;

public class SparkMaxInterface implements MotorInterface {
    private SparkMax motor;

    public SparkMaxInterface(int deviceId, MotorType motorType) {
        this.motor = new SparkMax(deviceId, motorType);
    }

    public void configure(UniversalConfig config) {
        SparkMaxConfig sparkConfig = new SparkMaxConfig();
        sparkConfig.follow(config._followId); // thank you revrobotics for having a follow method
        sparkConfig.inverted(config._isInverted);
        sparkConfig.idleMode(IdleMode.fromId(config._idleMode.value));
        sparkConfig.voltageCompensation(config._voltageCompensation);
        sparkConfig.smartCurrentLimit(config._currentLimit);

        motor.configure(sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public double getTemperature() {
        return motor.getMotorTemperature();
    }
}
