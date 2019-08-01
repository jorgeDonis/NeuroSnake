package training;

import basicTypes.Coordinate;
import brain.Brain;
import genetic.Genome;
import static snake.Constants.*;
import snake.Snake;

public class Trainer implements Runnable {

	private int bestFitness;
	private int mutationRate;
	private int numberSpecimens;
	public boolean keepTraining;
	private Genome[] bestGenomes;
	private int[] bestFitnesses;
	private boolean doFirstGen;

	public Trainer() {
		setBestFitness(0);
		setNumberSpecimens(0);
		bestFitness = 0;
		keepTraining = false;
		doFirstGen = true;
	}

	public int getBestFitness() {
		return bestFitness;
	}

	public void setBestFitness(int bestFitness) {
		this.bestFitness = bestFitness;
	}

	public int getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(int mutationRate) {
		this.mutationRate = mutationRate;
	}

	@Override
	public void run() {
		try {
			System.out.println("Training thread started");
			while (!Thread.currentThread().isInterrupted()) {
				if (doFirstGen) {
					firstGen();
					System.out.println("First generation generated");
					doFirstGen = false;
				}
				evolve();
			}
		} catch (Exception e) {
			System.out.println("Trainer thread stopped");
		}

	}

	public int getNumberSpecimens() {
		return numberSpecimens;
	}

	public void setNumberSpecimens(int numberSpecimens) {
		this.numberSpecimens = numberSpecimens;
	}

	private int getFitness(Genome genome) {
		Brain brain = new Brain();
		genome.setPhenotype();
		brain.setWeights(genome.getDna());

		Snake snake = new Snake();
		Coordinate foodPosition = new Coordinate(snake);
		int movesLeft = MAX_MOVES;
		do {
			brain.readInput(snake, foodPosition);
			snake.move(brain.predict(), foodPosition);
			movesLeft--;
		} while (!snake.hasCrashed() && movesLeft > 0);
		return snake.fitness;
	}
	
	private void compete(Genome genome) {
		for (int i = 0; i < SPECIMENTS_KEPT_PER_ITERATION; i++) {
			Genome superior = bestGenomes[i];
			int competitorFitness = getFitness(genome);
			if (competitorFitness >= getFitness(superior)) {
				bestGenomes[i] = genome;
				bestFitness = Math.max(bestFitness, competitorFitness);
				return;
			}
		}
	}
	
	private void evolve() {
		int familySize = SPECIMENS_PER_ITERATION / SPECIMENTS_KEPT_PER_ITERATION;
		for (int i = 0; i < SPECIMENTS_KEPT_PER_ITERATION; i++) {
			Genome father = bestGenomes[i];
			for (int j = 0; j < familySize; j++) {
				Genome child = new Genome(father);
				child.mutate(mutationRate);
				numberSpecimens++;
				compete(child);
			}
		}
	}

	private void firstGen() {
		Brain brain = new Brain();
		brain.randomInit(false);
		Genome ancestor = new Genome(brain);
		bestGenomes = new Genome[SPECIMENTS_KEPT_PER_ITERATION];
		bestFitnesses = new int[SPECIMENTS_KEPT_PER_ITERATION];
		for (int i = 0; i < SPECIMENTS_KEPT_PER_ITERATION; i++) {
			Genome child = new Genome(ancestor);
			child.mutate(this.mutationRate);
			bestFitnesses[i] = getFitness(child);
			bestGenomes[i] = child;
			bestFitness = Math.max(bestFitness, bestFitnesses[i]);
			numberSpecimens++;
		}
	}
}
