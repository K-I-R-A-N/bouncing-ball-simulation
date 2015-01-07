package bouncingball;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final double WIDTH = 1280;
	public static final double HEIGHT = 720;
	public static final String TITLE = "Bouncing Ball Simulation";
	
	public Window() {
		Simulation game = new Simulation(this);
		game.setPreferredSize(new Dimension((int) WIDTH, (int) HEIGHT));
		game.setMinimumSize(new Dimension((int) WIDTH, (int) HEIGHT));
		game.setMaximumSize(new Dimension((int) WIDTH, (int) HEIGHT));
		add(game);
		pack();
		game.requestFocus();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setVisible(true);
		game.startGame();
	}
}