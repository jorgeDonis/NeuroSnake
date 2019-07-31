package genetic;

import java.util.List;

import brain.Brain;

public class Genome {
	//Represents the weights of the neural network
	private List<double[][]> dna;

	public Genome() {
		dna = null;
	}
	
	public Genome(Brain brain) {
		dna = brain.getWeights();
	}

	public List<double[][]> getDna() {
		return dna;
	}

	public void setDna(List<double[][]> dna) {
		this.dna = dna;
	}
}
