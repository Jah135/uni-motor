package frc.robot.motor.interfaces;

import frc.robot.motor.UniversalConfig;

public interface MotorInterface {
    public void setVoltage(double voltage);
    public void configure(UniversalConfig config);
    public double getTemperature();
}
