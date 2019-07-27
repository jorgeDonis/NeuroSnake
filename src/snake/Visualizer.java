package snake;

import snake.Constants;

public class Visualizer {
	
	private static char WALL = '#';
	private static char SNAKE_BODY = 'c';
	private static char SNAKE_HEAD = 'x';
	private static char FOOD = '?';
	
	private static char[][] mapStr;
	
	private static Snake snake;
	
	public Visualizer(Snake snake){
		Visualizer.mapStr = new char[Constants.HEIGHT][Constants.WIDTH];
		Visualizer.snake = snake;
	}
	
	private static void print() {
		String foo = "";
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				foo += Visualizer.mapStr[i][j];
			}
			foo += "\n";
		}
		System.out.println(foo);
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
