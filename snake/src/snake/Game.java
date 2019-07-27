package snake;

import javax.swing.JFrame;

public class Game extends JFrame {

	public static Snake snake;
	public static Visualizer vis;
	public static Coordinate foodPosition;
	
	public static Console console;

	public KeyReader kr;
	
	public Game() {
		super();
		kr = new KeyReader();
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		console = new Console();
		console.init(kr);
		setVisible(false);
		console.getFrame().setLocation(getX() + getWidth() + getInsets().right, getY());
	}

	public static void init() {
		snake = new Snake();
		new Visualizer(Game.snake);
		Game.foodPosition = new Coordinate(snake);
		Visualizer.console = console;
	}

	public void play() {
		init();
		do {
			 try {
				 Visualizer.draw();
				 Thread.sleep(Constants.SLEEP_TIME);
				 snake.move(kr.readKey);
			} catch (InterruptedException e) {}
		} while(!snake.hasCrashed());
		System.out.println("\n \n \t \t GAME OVER!");
	}
}
