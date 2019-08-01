package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import brain.Brain;
import genetic.Genome;

public class GenomeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Brain brain = new Brain(2, 1, 2, 2);
		brain.randomInit(true);
		Genome species1dna = new Genome(brain);
		brain.predict();
		brain.print();
		species1dna.mutate(10);
		brain.setWeights(species1dna.getDna());
		brain.predict();
		System.out.println("\n");
		brain.print();
	}

}
