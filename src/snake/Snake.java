package snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
	// Last element is the head
	public Stack<Coordinate> shape = new Stack<Coordinate>();
	private Stack<Direction> directions = new Stack<Direction>();

	private void move(int coordIndex, Direction dir) {
		Coordinate coord = shape.get(coordIndex);
		switch (dir) {
		case Up:
			coord = new Coordinate(coord.x, coord.y + 1);
			break;
		case Down:
			coord = new Coordinate(coord.x, coord.y - 1);
			break;
		case Left:
			coord = new Coordinate(coord.x - 1, coord.y);
			break;
		case Right:
			coord = new Coordinate(coord.x + 1, coord.y);
			break;
		default:
			break;
		}
		shape.set(coordIndex, coord);
	}

	public Snake() {
		int x = ThreadLocalRandom.current().nextInt(1, Constants.WIDTH - 1);
		int y = ThreadLocalRandom.current().nextInt(1, Constants.HEIGHT - 1);
		shape.add(new Coordinate(x, y));
		directions.add(Direction.Still);
	}

	public boolean isAt(Coordinate coord) {
		return shape.contains(coord);
	}

	public Coordinate getHeadPos() {
		return shape.get(0);
	}

	public boolean hasCrashed() {
		Coordinate head = getHeadPos();
		boolean wallCollision = (head.x == 0 || head.x == Constants.WIDTH - 1 || head.y == 0
				|| head.y == Constants.HEIGHT - 1);
		if (wallCollision)
			return true;
		else if (shape.size() != 1)
			return (shape.subList(1, shape.size() - 1).contains(head));
		return false;
	}

	private boolean eats(Direction dir) {
		Coordinate head = shape.get(0);
		Coordinate newCoord = null;
		switch (dir) {
		case Down:
			newCoord = new Coordinate(head.x, head.y - 1);
			break;
		case Left:
			newCoord = new Coordinate(head.x - 1, head.y);
			break;
		case Right:
			newCoord = new Coordinate(head.x + 1, head.y);
			break;
		case Up:
			newCoord = new Coordinate(head.x, head.y + 1);
			break;
		default:
			return false;
		}
		if (Game.foodPosition.equals(newCoord)) {
			shape.add(0, newCoord);
			directions.set(0, dir);
			directions.add(0, dir);
			Game.foodPosition = new Coordinate(this);
			return true;
		}
		return false;
	}

	public void move(Direction dir) {
		if (!eats(dir)) {
			directions.set(0, dir);
			move(0, dir);
			for (int i = 1; i < shape.size(); i++) {
				move(i, directions.get(i));
				Direction oldDirection = directions.get(i);
				directions.set(i, dir);
				dir = oldDirection;
			}
		}
	}

	public void move(KeyEvent e) {
		if (e != null) {
			if (e.getKeyCode() == (KeyEvent.VK_LEFT))
				move(Direction.Left);
			else if (e.getKeyCode() == (KeyEvent.VK_DOWN))
				move(Direction.Up);
			else if (e.getKeyCode() == (KeyEvent.VK_UP))
				move(Direction.Down);
			else if (e.getKeyCode() == (KeyEvent.VK_RIGHT))
				move(Direction.Right);
		} else
			move(directions.get(0));
	}

}
