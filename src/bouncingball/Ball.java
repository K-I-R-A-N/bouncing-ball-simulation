package bouncingball;

import java.awt.Point;

public class Ball extends Entity {

	private static final int DIAMETER = 20;
	private static final Point START_POS = new Point(640, 540);
	private static final double START_X_VEL = 0;
	private static final double START_Y_VEL = 0;
	// Coefficient of restitution is how much speed is retained after bounce.
	private static final double COR = 0.6;

	private Simulation s;

	public Ball(Simulation s) {
		this.s = s;
	}

}