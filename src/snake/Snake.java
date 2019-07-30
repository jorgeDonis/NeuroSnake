package snake;

import java.awt.event.KeyEvent;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import basicTypes.Coordinate;
import basicTypes.Direction;
import game.Game;

public class Snake {
	// Last element is the head
	public Stack<Coordinate> shape = new Stack<Coordinate>();
	private Stack<Direction> directions = new Stack<Direction>();
	private boolean oppositeCrash = false;

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
			return (shape.subList(1, shape.size() - 1).contains(head)) || oppositeCrash;
		return oppositeCrash;
	}

	private boolean eats(Direction dir, Coordinate foodLocation) {
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
		if (foodLocation.equals(newCoord)) {
			shape.add(0, newCoord);
			directions.set(0, dir);
			directions.add(0, dir);
			foodLocation.setLocation(new Coordinate(this));
			return true;
		}
		return false;
	}

	public void move(Direction dir, Coordinate foodLocation) {
		if (!eats(dir, foodLocation)) {
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

	public void checkOppositeCrash(Direction dir) {
		if (shape.size() > 1) {
			switch (dir) {
			case Down:
				oppositeCrash = (directions.get(0).equals(Direction.Up));
				break;
			case Left:
				oppositeCrash = (directions.get(0).equals(Direction.Right));
				break;
			case Right:
				oppositeCrash = (directions.get(0).equals(Direction.Left));
				break;
			case Up:
				oppositeCrash = (directions.get(0).equals(Direction.Down));
				break;
			}
		}
	}

	public void move(KeyEvent e, Coordinate foodLocation) {
		if (e != null) {
			if (e.getKeyCode() == (KeyEvent.VK_LEFT)) {
				checkOppositeCrash(Direction.Left);
				move(Direction.Left, foodLocation);
			}
			else if (e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
				checkOppositeCrash(Direction.Right);
				move(Direction.Right, foodLocation);
			}
			else if (e.getKeyCode() == (KeyEvent.VK_DOWN)) {
				checkOppositeCrash(Direction.Up);
				move(Direction.Up, foodLocation);
			}
			else if (e.getKeyCode() == (KeyEvent.VK_UP)) {
				checkOppositeCrash(Direction.Down);
				move(Direction.Down, foodLocation);
			}
		} else
			move(directions.get(0), foodLocation);
	}

}
