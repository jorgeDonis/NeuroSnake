package training;

public class Trainer {

	private int bestFitness;

	public Trainer() {
		setBestFitness(0);
	}

	public int getBestFitness() {
		return bestFitness;
	}

	public void setBestFitness(int bestFitness) {
		this.bestFitness = bestFitness;
	}
}
