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
    public double _voltageCompensation = 12;
    public boolean _isInverted = false;

    public IdleMode _idleMode = IdleMode.BRAKE;
    
    public UniversalConfig voltageCompensation(Double nominalVoltage) {
        this._voltageCompensation = nominalVoltage;
        return this;
    }
    public UniversalConfig smartCurrentLimit(int currentAmps) {
        this._currentLimit = currentAmps;
        return this;
    }

    public UniversalConfig idleMode(IdleMode idleMode) {
        this._idleMode = idleMode;
        return this;
    }

    public UniversalConfig invert(boolean inverted) {
        this._isInverted = inverted;
        return this;
    }
}
