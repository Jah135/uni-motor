package frc.robot.motor;

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

    public int _followId = 0;
    public boolean _inverted = false;

    public IdleMode _idleMode = IdleMode.BRAKE;

    public UniversalConfig() {}

    public UniversalConfig follow(int followId) {
        this._followId = followId;
        return this;
    }
    public UniversalConfig follow(UniversalMotor motor) {
        this._followId = motor.getDeviceId();
        return this;
    }
    public UniversalConfig idleMode(IdleMode idleMode) {
        this._idleMode = idleMode;
        return this;
    }

    public UniversalConfig invert(boolean inverted) {
        this._inverted = inverted;
        return this;
    }
}
