package frc.robot.motor.interfaces;

import frc.robot.config.UniversalConfig;
import frc.robot.motor.UniversalMotor;

public abstract class MotorInterface {
    boolean isFollower = false;

    public abstract void configure(UniversalConfig config);

    public abstract void setVoltage(double voltage);
    public abstract double getVoltage();
    
    public abstract double getPosition();
    
    public abstract double getTemperature();

    public void follow(UniversalMotor master) {
        isFollower = true;
        
        master.addFollower(this);
    };
}
