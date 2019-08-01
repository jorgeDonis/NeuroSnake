package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import brain.Brain;
import snake.Constants;

public class Genome {
	// Represents the weights of the neural network
	private List<double[][]> dna;
	private double[] genes;

	public Genome() {
		dna = null;
	}

	public void flattenDna() {
		int n = 0;
		for (double[][] matrix : dna)
			n += (matrix.length * matrix[0].length);
		genes = new double[n];
		int idx = 0;
		for (int i = 0; i < dna.size(); i++) {
			for (int j = 0; j < dna.get(i).length; j++) {
				for (int k = 0; k < dna.get(i)[j].length; k++) {
					genes[idx] = dna.get(i)[j][k];
					idx++;
				}
			}
		}
	}

	public void setPhenotype() {
		int idx = 0;
		for (int i = 0; i < dna.size(); i++) {
			double[][] matrix = new double[dna.get(i).length][dna.get(i)[0].length];
			for (int j = 0; j < dna.get(i).length; j++) {
				for (int k = 0; k < dna.get(i)[j].length; k++) {
					matrix[j][k] = genes[idx];
					idx++;
				}
			}
			dna.set(i, matrix);
		}
	}

	public Genome(Brain brain) {
		dna = brain.getWeights();
		flattenDna();
	}

	public Genome(Genome genome) {
		genes = new double[genome.genes.length];
		for (int i = 0; i < genome.genes.length; i++)
			genes[i] = genome.genes[i];

		dna = new ArrayList<double[][]>();
		for (double[][] matrix : genome.dna)
			dna.add(matrix);
	}

	public List<double[][]> getDna() {
		return dna;
	}

	public void setDna(List<double[][]> dna) {
		this.dna = dna;
	}

	/**
	 * Will modify the current genome randomly
	 * 
	 * @param mutationRate Parametre which ranges from 1 to 100. It is the chance
	 *                     that a gene gets modified. It is also the percentage of
	 *                     the step taken.
	 */
	public void mutate(int mutationRate) {
		for (int i = 0; i < genes.length; i++) {
			if (ThreadLocalRandom.current().nextInt(1, 101) <= mutationRate) 
				genes[i] += Constants.MAX_MUTATION_DELTA * mutationRate / 100;				
		}
		setPhenotype();
	}
}
