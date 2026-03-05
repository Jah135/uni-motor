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
	public class PIDConfig {
		public boolean continuous = false;
		public double kP = 0, kI = 0, kD = 0;

		public PIDConfig(double kP, double kI, double kD) {
			this.kP = kP;
			this.kI = kI;
			this.kD = kD;
		}
	}

	public IdleMode idleMode = IdleMode.BRAKE;
	public PIDConfig pidConfig = new PIDConfig(0, 0, 0);

	public int currentLimit = 0;
	public double voltageCompensation = 12;
	public boolean isInverted = false;
	
	/**
	 * @param nominalVoltage
	 * @return The config
	 */
	public UniversalConfig setVoltageCompensation(Double nominalVoltage) {
		this.voltageCompensation = nominalVoltage;
		return this;
	}
	
	/**
	 * @param currentAmps The smart current limit to set the motor.
	 * @return The config
	 */
	public UniversalConfig setSmartCurrentLimit(int currentAmps) {
		this.currentLimit = currentAmps;
		return this;
	}

	/**
	 * @param idleMode The idle mode to set the motor.
	 * @return The config
	 */
	public UniversalConfig setIdleMode(IdleMode idleMode) {
		this.idleMode = idleMode;
		return this;
	}

	/**
	 * @param inverted Whether to set the direction of the motor to be inverted.
	 * @return The config
	 */
	public UniversalConfig setInverted(boolean inverted) {
		this.isInverted = inverted;
		return this;
	}
}
