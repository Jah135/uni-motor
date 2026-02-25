package frc.robot.motor.interfaces;

import frc.robot.config.UniversalConfig;
import frc.robot.motor.UniversalMotor;

/**
 * A generic interface for motors.
 */
public interface MotorInterface {
    /**
     * Should apply *most*, if not all, configurations defined inside UniversalConfig.
     */
    public void configure(UniversalConfig config);

    /**
     * Should set the voltage being applied to the motor.
     */
    public void setVoltage(double voltage);

    /**
     * Should return the voltage being applied to the motor.
     */
    public double getVoltage();
    
    /**
     * Should return the absolute position of the motor in rotations.
     */
    public double getPosition();
    /**
     * Should return the velocity of the motor in rotations per second.
     */
    public double getAngularVelocity();
    
    /**
     * Should return the temperature of the motor in Celsius.
     */
    public double getTemperature();

    public default void follow(UniversalMotor master) {        
        master.addFollower(this);
    };
}
