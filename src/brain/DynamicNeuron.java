package brain;

import java.util.List;

public class DynamicNeuron extends Neuron {
	
	private Neuron[] predecesors;
	private double[] weights;
	private double theta;
	
	public DynamicNeuron(Neuron[] predecesors) {
		super();
		theta = 0;
		this.predecesors = predecesors;
	}
	
}
