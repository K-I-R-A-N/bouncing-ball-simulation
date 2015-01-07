package bouncingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball extends Entity {

	private static final int DIAMETER = 100;
	private static final Point START_POS = new Point(640, 0);
	private static final double START_X_VEL = 0;
	private static final double START_Y_VEL = 0;
	// Coefficient of restitution is how much speed is retained after bounce.
	private static final double COR = 0.5;
	// True gravity is the gravitational acceleration to the floor.
	private static final double TRUE_GRAVITY = 9.81;
	// Gravity is the acceleration per tick.
	private static final double GRAVITY = TRUE_GRAVITY / Simulation.TICK_RATE;

	// These are the variables for the x and y velocities at any one time.
	private double velX = START_X_VEL;
	private double velY = START_Y_VEL;

	private Simulation s;

	public Ball(Simulation s) {
		this.s = s;
		setWidth(DIAMETER);
		setHeight(DIAMETER);
		setX(START_POS.getX());
		setY(START_POS.getY());
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawOval((int) getX(),(int) getY(),(int) getWidth(),(int) getHeight());
	}

	@Override
	public void tick() {
		if (getY() >= s.window.getHeight() - 20 - DIAMETER) {	
			velY = -velY * COR;
		}
		
		velY += GRAVITY;
		setX(getX() + velX);
		setY(getY() + velY);	
	}

	public double getVelX() {
		return this.velX;
	}

	public double getVelY() {
		return this.velY;
	}
}