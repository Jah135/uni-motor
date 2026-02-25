package frc.robot.config;

public class UniversalConfig {
    public enum IdleMode {
        COAST(0),
        BRAKE(1);

        public final int value;

        IdleMode(int value) {
            this.value = value;
        }

        public static IdleMode fromId(int id) {
            if (id == 0) {
                return COAST;
            }

            return BRAKE;
        }
    }

    public int _currentLimit = 0;
    public double voltageCompensation = 12;
    public boolean isInverted = false;

    public IdleMode idleMode = IdleMode.BRAKE;
    
    public UniversalConfig voltageCompensation(Double nominalVoltage) {
        this.voltageCompensation = nominalVoltage;
        return this;
    }
    public UniversalConfig smartCurrentLimit(int currentAmps) {
        this._currentLimit = currentAmps;
        return this;
    }

    public UniversalConfig idleMode(IdleMode idleMode) {
        this.idleMode = idleMode;
        return this;
    }

    public UniversalConfig invert(boolean inverted) {
        this.isInverted = inverted;
        return this;
    }
}
