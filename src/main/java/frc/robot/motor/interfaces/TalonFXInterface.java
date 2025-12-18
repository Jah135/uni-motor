package frc.robot.motor.interfaces;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.config.UniversalConfig;

public class TalonFXInterface extends MotorInterface {
    private TalonFX motor;

    public TalonFXInterface(int deviceId) {
        this.motor = new TalonFX(deviceId);
    }
    public TalonFXInterface(int deviceId, CANBus bus) { // CANBus, because String was just too convenient huh
        this.motor = new TalonFX(deviceId, bus);
    }

    public void configure(UniversalConfig config) {
        TalonFXConfiguration talonConfig = new TalonFXConfiguration();
        talonConfig.MotorOutput.Inverted = config._isInverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        // why is it called valueOf???? this makes no sense
        // why is configuring talons such a JOY i LOVE talons, i am so JOLLY right now!
        talonConfig.MotorOutput.NeutralMode = NeutralModeValue.valueOf(config._idleMode.value); // will break if for whatever reason COAST != 0 and IDLE != 1, or some other neutral mode is invented.
        talonConfig.CurrentLimits.SupplyCurrentLimit = config._currentLimit;
        // THERE IS NO FOLLOW METHOD FOR TALONS DO WE HAVE TO MAKE OUR OWN FUCK
        // talonConfig.CurrentLimits.
        
        motor.getConfigurator().apply(talonConfig);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public double getVoltage() {
        return motor.getMotorVoltage().getValueAsDouble();
    }

    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }

    public double getTemperature() {
        return motor.getDeviceTemp().getValueAsDouble();
    }
}
