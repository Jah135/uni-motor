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

	private final double ROTS_PER_MIN_TO_RADS_PER_SEC = 1 / 60 * Math.PI * 2;
	private final double ROTS_TO_RADS = Math.PI * 2;

	public SparkMaxInterface(int deviceId, MotorType motorType) {
		this.motor = new SparkMax(deviceId, motorType);
		this.encoder = motor.getAbsoluteEncoder();
	}

	public void configure(UniversalConfig config) {
		SparkMaxConfig sparkConfig = new SparkMaxConfig();
		sparkConfig.inverted(config.isInverted);
		sparkConfig.idleMode(IdleMode.fromId(config.idleMode.value));
		sparkConfig.voltageCompensation(config.voltageCompensation);
		sparkConfig.smartCurrentLimit(config.currentLimit);

		motor.configure(sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
	}

	public void setVoltage(double voltage) {
		motor.setVoltage(voltage);
	}

	public double getVoltage() {
		return motor.getBusVoltage() * motor.getAppliedOutput();
	}

	public double getPosition() {
		return encoder.getPosition() * ROTS_TO_RADS;
	}

	public double getAngularVelocity() {
		return encoder.getVelocity() * ROTS_PER_MIN_TO_RADS_PER_SEC;
	}

	public double getTemperature() {
		return motor.getMotorTemperature();
	}
}
