package frc.robot.drive;

public class SwerveDrive {
	SwerveModule tlModule, trModule, blModule, brModule;

	SwerveDrive(SwerveModule topLeft, SwerveModule topRight, SwerveModule bottomLeft, SwerveModule bottomRight) {
		tlModule = topLeft;
		trModule = topRight;
		blModule = bottomLeft;
		brModule = bottomRight;
	}

	public void update(double targetAngle, double targetSpeed) {
		tlModule.update(targetAngle, targetSpeed);
		trModule.update(targetAngle, targetSpeed);
		blModule.update(targetAngle, targetSpeed);
		brModule.update(targetAngle, targetSpeed);
	}
}
