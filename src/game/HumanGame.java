package game;

import snake.KeyReader;
import snake.Visualizer;
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
				 Visualizer.draw();
				 Thread.sleep(Constants.SLEEP_TIME);
				 snake.move(kr.readKey);
			} catch (InterruptedException e) {}
		} while(!snake.hasCrashed());
		System.out.println("\n \n \t \t GAME OVER!");
	}	
}
