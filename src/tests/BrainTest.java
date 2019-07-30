package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import basicTypes.Coordinate;
import brain.Brain;
import snake.Snake;

public class BrainTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBrain() {
		Brain brain = new Brain();
		Snake snake = new Snake();
		Coordinate foodPosition = new Coordinate(snake);
		brain.randomInit(false);
		brain.readInput(snake, foodPosition);
		brain.propagate();
		System.out.println("\n");
		brain.print();
	}

}
