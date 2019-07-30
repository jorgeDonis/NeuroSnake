package game;

import javax.swing.JFrame;

import basicTypes.Coordinate;
import snake.Console;
import snake.Snake;
import snake.Visualizer;

public abstract class Game extends JFrame implements Runnable {

	public Snake snake;
	public Coordinate foodPosition;
	public static Console console;
	public static Visualizer vis;
	
	public Game() {
		super();
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		
		console = new Console();
		console.init(null);
		console.getFrame().setLocation(getX() + getWidth() + getInsets().right, getY());
		snake = new Snake();
		new Visualizer(snake);
		Visualizer.console = console;
		foodPosition = new Coordinate(snake);
	}
	
	@Override
	public abstract void run();
}
