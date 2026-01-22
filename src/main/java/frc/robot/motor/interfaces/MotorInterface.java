package frc.robot.motor.interfaces;

import frc.robot.config.UniversalConfig;
import frc.robot.motor.UniversalMotor;

/**
 * A generic interface for motors.
 */
public interface MotorInterface {
    public void configure(UniversalConfig config);

    public void setVoltage(double voltage);
    public double getVoltage();
    
    public double getPosition();
    public double getVelocity();
    
    public double getTemperature();

    public default void follow(UniversalMotor master) {        
        master.addFollower(this);
    };
}
