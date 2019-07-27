package snake;

import java.util.concurrent.ThreadLocalRandom;

public class Coordinate {
	
	public int x;
	public int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Random coordinate created somewhere the snake is not at
	public Coordinate(Snake snake) {
		do {
			x = ThreadLocalRandom.current().nextInt(1, Constants.WIDTH - 1);
			y = ThreadLocalRandom.current().nextInt(1, Constants.HEIGHT - 1);
		} while (snake.isAt(this));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
