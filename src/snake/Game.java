package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Game extends JFrame {

	public static Snake snake;
	public static Visualizer vis;
	public static Coordinate foodPosition;
	
	public static Console console;

	public Game() {
		super();
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		console = new Console();
		console.init();
		setVisible(false);
		console.getFrame().setLocation(getX() + getWidth() + getInsets().right, getY());
	}

	public static void init() {
		snake = new Snake();
		Game.foodPosition = new Coordinate(snake);
		Visualizer vis = new Visualizer(snake);
	}

	public static void play() {
		init();
		do {
			KeyReader kr = new KeyReader();
			Thread keyReader = new Thread(kr);
			keyReader.start();
			 try {
				 Visualizer.draw();
				 Thread.sleep(Constants.SLEEP_TIME);
				 snake.move(kr.readKey);
				 console.clear();
			} catch (InterruptedException e) {}
		} while(!snake.hasCrashed());
		System.out.println("\n \n \t \t GAME OVER!");
	}
}
