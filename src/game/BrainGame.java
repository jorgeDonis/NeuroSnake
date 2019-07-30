package game;

import brain.Brain;
import snake.Constants;
import snake.Visualizer;

public class BrainGame extends Game {

	private Brain brain;

	public BrainGame() {
		super();
		brain = new Brain();
		brain.randomInit(false);
	}

	@Override
	public void run() {
		do {
			try {
				Visualizer.draw(foodPosition);
				brain.readInput(snake, foodPosition);
				Thread.sleep(Constants.SLEEP_TIME);
				snake.move(brain.predict(), this.foodPosition);
			} catch (InterruptedException e) {
			}
		} while (!snake.hasCrashed());
		System.out.println("\n \n \t \t GAME OVER!");
	}
}
