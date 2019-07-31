package brain;

import java.util.List;
import java.util.Random;

public class DynamicNeuron extends Neuron {
	
	private Neuron[] predecesors;
	private double[] weights;
	private double theta;
	
	public DynamicNeuron(Neuron[] predecesors) {
		super();
		theta = 0;
		this.predecesors = predecesors;
		setWeights(new double[predecesors.length]);
	}

	@Override
	void randomInit() {
		Random rng = new Random();
		for (int i = 0; i < getWeights().length; i++)
			getWeights()[i] = (rng.nextDouble() * 20) - 10;
	}
	
	protected void propagate() {
		for (int i = 0; i < predecesors.length; i++)
			rawValue += predecesors[i].rawValue * getWeights()[i];
		rawValue += theta;
		rawValue = Brain.sigmoid(rawValue);
	}

	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}
	
}
