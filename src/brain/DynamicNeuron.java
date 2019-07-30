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
		weights = new double[predecesors.length];
	}

	@Override
	void randomInit() {
		Random rng = new Random();
		for (int i = 0; i < weights.length; i++)
			weights[i] = (rng.nextDouble() * 20) - 10;
	}
	
	protected void propagate() {
		for (int i = 0; i < predecesors.length; i++)
			rawValue += predecesors[i].rawValue * weights[i];
		rawValue += theta;
		rawValue = Brain.sigmoid(rawValue);
	}
	
}
