package frc.robot.motor.interfaces;

import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Radian;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.config.UniversalConfig;

public class TalonFXInterface implements MotorInterface {
	private TalonFX motor;

	private StatusSignal<Temperature> temperatureSignal;
	private StatusSignal<Angle> positionSignal;
	private StatusSignal<Voltage> voltageSignal;
	private StatusSignal<AngularVelocity> velocitySignal;

	private void setup() {
		temperatureSignal = motor.getDeviceTemp();
		positionSignal = motor.getPosition();
		voltageSignal = motor.getMotorVoltage();
		velocitySignal = motor.getVelocity();
	}

	public TalonFXInterface(int deviceId) {
		this.motor = new TalonFX(deviceId);

		setup();
	}
	public TalonFXInterface(int deviceId, CANBus bus) { // CANBus, because String was just too convenient huh
		this.motor = new TalonFX(deviceId, bus);

		setup();
	}

	public void configure(UniversalConfig config) {
		TalonFXConfiguration talonConfig = new TalonFXConfiguration();
		talonConfig.MotorOutput.Inverted = config.isInverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
		talonConfig.MotorOutput.NeutralMode = NeutralModeValue.valueOf(config.idleMode.value);
		talonConfig.CurrentLimits.SupplyCurrentLimit = config.currentLimit;
		
		motor.getConfigurator().apply(talonConfig);
	}

	public void setVoltage(double voltage) {
		motor.setVoltage(voltage);
	}

	public double getVoltage() {
		return voltageSignal.getValueAsDouble();
	}

	public double getPosition() {
		return positionSignal.getValue().in(Radian);
	}

	public double getAngularVelocity() {
		return velocitySignal.getValue().in(RadiansPerSecond);
	}

	public double getTemperature() {
		return temperatureSignal.getValue().in(Celsius);
	}
}
