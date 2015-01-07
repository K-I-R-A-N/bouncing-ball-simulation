package bouncingball;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Simulation extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final double TICK_RATE = 60;

	private static final Font FONT = new Font("Trebuchet MS", Font.BOLD, 50);

	public static boolean isRunning = false;
	public static boolean debugMode = true;

	private Thread thread;
	Window window;

	private Ball ball;

	public Simulation(Window w) {
		window = w;
	}

	private void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();

		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.black);
		g.drawString(
				"X: " + (int) ball.getX() + " Y: " + (int) ball.getY() + " X Velocity: "
						+ (int) ball.getVelX() + " Y Velocity: " + (int) ball.getVelY(), 5,
				20);

		ball.render(g);

		if (debugMode) {
			// Debug info here!
		}

		g.dispose();
		bufferStrategy.show();
	}

	private void tick() {
		ball.tick();
	}

	@Override
	public void run() {
		long previousTime = System.nanoTime();
		double nanoSeconds = 1000000000 / TICK_RATE;
		double timeElapsed = 0;

		long gameTimer = System.currentTimeMillis();

		double ticks = 0;
		double fps = 0;

		while (isRunning) {
			long currentTime = System.nanoTime();
			timeElapsed += (currentTime - previousTime) / nanoSeconds;
			previousTime = currentTime;

			while (timeElapsed >= 1) {
				tick();
				ticks++;
				timeElapsed--;
			}

			render();
			fps++;

			if (System.currentTimeMillis() - gameTimer > 1000) {
				gameTimer += 1000;

				if (debugMode) {
					System.out.println("FPS: " + (int) fps + " TICKS: "
							+ (int) ticks);
					window.setTitle(Window.TITLE + " | FPS: " + (int) fps
							+ " TICKS: " + (int) ticks);
				} else {
					window.setTitle(Window.TITLE);
				}

				fps = 0;
				ticks = 0;
			}
		}
		stopGame();
	}

	public synchronized void startGame() {
		if (isRunning)
			return;
		isRunning = true;

		ball = new Ball(this);

		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stopGame() {
		if (!isRunning)
			return;
		isRunning = false;
		thread = null;
		System.exit(0);
	}
}