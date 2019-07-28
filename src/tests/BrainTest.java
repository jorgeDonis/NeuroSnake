package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import brain.Brain;

public class BrainTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBrain() {
		Brain brain = new Brain(2, 1, 2, 2); 
	}

}
