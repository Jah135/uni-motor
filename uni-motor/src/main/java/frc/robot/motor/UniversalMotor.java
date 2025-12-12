package frc.robot.motor;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.Temperature;

public class UniversalMotor {
    public enum MotorBrand {
        CAN_BRUSHED,
        CAN_BRUSHLESS,
        TALON,
    }

    private int deviceId;

    public MotorBrand brand;
    private GenericMotor motor;

    public UniversalMotor(MotorBrand brand, int deviceId) {
        this.brand = brand;
        this.deviceId = deviceId;

        switch (brand) {
            case CAN_BRUSHED:
                this.motor = (GenericMotor) new SparkMax(deviceId, MotorType.kBrushed);
                break;
            case CAN_BRUSHLESS:
                this.motor = (GenericMotor) new SparkMax(deviceId, MotorType.kBrushless);
                break;
            case TALON:
                this.motor = (GenericMotor) new TalonFX(deviceId);
                break;
            default:
                throw new IllegalArgumentException("Invalid motor brand.");
        }
    }

    public void configure(UniversalConfig config) {
        switch (brand) {
            case CAN_BRUSHED:
            case CAN_BRUSHLESS:
                SparkMaxConfig spark_config = new SparkMaxConfig();
                spark_config.follow(config._followId);
                spark_config.inverted(config._inverted);
                spark_config.idleMode(IdleMode.fromId(config._idleMode.value));

                motor.configure(spark_config);

                break;
            default:
                break;
        }
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public double getTemperature() {
        switch (brand) {
            case CAN_BRUSHED:
            case CAN_BRUSHLESS:
                return this.motor.getMotorTemperature();
            case TALON:
                return this.motor.getDeviceTemp().getValueAsDouble();
            default:
                throw new IllegalArgumentException("getTemperature() is not implemented for this brand's motor.");
        }
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    private interface GenericMotor {
        public void setVoltage(double voltage);

        public void configure(SparkMaxConfig config);

        public double getMotorTemperature();

        public StatusSignal<Temperature> getDeviceTemp();
    }

}
