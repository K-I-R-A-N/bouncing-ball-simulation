package bouncingball;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
	private double x;
	private double y;
	private double width;
	private double height;

	public abstract void render(Graphics2D g);

	public abstract void tick();

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}