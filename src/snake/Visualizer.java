package snake;

import basicTypes.Coordinate;
import game.Game;
import snake.Constants;

public class Visualizer {
	
	private static char WALL = '#';
	private static char SNAKE_BODY = 'o';
	private static char SNAKE_HEAD = 'x';
	private static char FOOD = '$';
	
	private static char[][] mapStr;
	
	public static Console console;
	
	private static Snake snake;
	
	public Visualizer(Snake snake){
		Visualizer.mapStr = new char[Constants.HEIGHT][Constants.WIDTH];
		Visualizer.snake = snake;
	}
	
	private static void print() {
		String foo = "";
		//Draw top padding
		for (int i = 0; i < Constants.TOP_PADDING; i++) foo += "\n";
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int k = 0; k < Constants.LEFT_PADDING; k++) foo += " ";
			for (int j = 0; j < Constants.WIDTH; j++) {
				foo += Visualizer.mapStr[i][j];
				foo += " ";
			}
			foo += "\n";
		}
		console.textArea.setText(foo);
	}
	
	public static void draw() {
		//Draw the borders and the snake
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				if ((i == 0) || (i == Constants.HEIGHT - 1))
					Visualizer.mapStr[i][j] = WALL;
				else if ((j == 0) || (j == Constants.WIDTH - 1))
					Visualizer.mapStr[i][j] = WALL;
				else if (snake.isAt(new Coordinate(j,i)))
					Visualizer.mapStr[i][j] = SNAKE_BODY;
				else
					Visualizer.mapStr[i][j] = ' ';
			}
		}
		//Draw the snake's head
		Coordinate headPosition = Visualizer.snake.getHeadPos();
		Visualizer.mapStr[headPosition.y][headPosition.x] = SNAKE_HEAD;
		//Draw the food
		Visualizer.mapStr[Game.foodPosition.y][Game.foodPosition.x] = FOOD;
		Visualizer.print();
	}
}
