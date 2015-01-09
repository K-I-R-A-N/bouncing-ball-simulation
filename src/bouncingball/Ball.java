package bouncingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Ball extends Entity {

	private static final int DIAMETER = 30;
	private static final Point START_POS = new Point(0, 0);
	private static final double START_X_VEL = 30;
	private static final double START_Y_VEL = 100;
	// Coefficient of restitution is how much speed is retained after bounce.
	private static final double COR = 0.8;
	// True gravity is the gravitational acceleration to the floor.
	private static final double TRUE_GRAVITY = 9.81;
	// Gravity is the acceleration per tick.
	private static final double GRAVITY = TRUE_GRAVITY / Simulation.TICK_RATE;
	// Friction variable. This is arbitrary until I've got a way to calculate
	// velocity lost on a surface
	private static final double FRICTION = 0.005;

	// These are the variables for the x and y velocities at any one time.
	private double velX = START_X_VEL;
	private double velY = START_Y_VEL;

	private Simulation s;

	private ArrayList<Point> points = new ArrayList<>();

	public Ball(Simulation s) {
		this.s = s;
		setWidth(DIAMETER);
		setHeight(DIAMETER);
		setX(START_POS.getX());
		setY(START_POS.getY());
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		for (Point p : points) {
			g.fillOval((int) (p.getX() + getWidth() / 2),
					(int) (p.getY() + getHeight() / 2), 2, 2);
		}
		g.setColor(Color.GREEN);
		g.fillOval((int) getX(), (int) getY(), (int) getWidth(),
				(int) getHeight());
	}

	@Override
	public void tick() {
		if (getY() >= s.getHeight() - getHeight()) {
			if (getY() > s.getHeight() - getHeight())
				setY(s.getHeight() - getHeight());
			velY = -velY * COR;
		} else if (getY() <= 0) {
			if (getY() < 0)
				setY(0);
			velY = -velY * COR;
		}
		
		if (getX() >= s.getWidth() - getWidth()) {
			if (getX() > s.getWidth() - getWidth())
				setX(s.getWidth() - getWidth());
			velX = -velX * COR;
		} else if (getX() <= 0) {
			if (getX() < 0)
				setX(0);
			velX = -velX * COR;
		}

		if (velX != 0 && getY() >= s.getHeight() - getHeight()) {
			if (velX > FRICTION)
				velX -= FRICTION;
			else if (velX < -FRICTION)
				velX += FRICTION;
			else {
				velX = 0;
			}
		}

		velY += GRAVITY;
		setX(getX() + velX);
		setY(getY() + velY);

		points.add(new Point((int) getX(), (int) getY()));
	}

	public double getVelX() {
		return this.velX;
	}

	public double getVelY() {
		return this.velY;
	}
}