package game;

import snake.KeyReader;
import gui.Visualizer;
import snake.Constants;

public class HumanGame extends Game {
	
	public KeyReader kr;

	public HumanGame() {
		super();
		kr = new KeyReader();
		console.init(kr);
	}

	@Override
	public void run() {
		do {
			 try {
				 Visualizer.draw(foodPosition);
				 Thread.sleep(Constants.SLEEP_TIME);
				 snake.move(kr.readKey, foodPosition);
			} catch (InterruptedException e) {}
		} while(!snake.hasCrashed());
		System.out.println("\n \n \t \t GAME OVER!");
	}	
}
