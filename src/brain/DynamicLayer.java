package brain;

import java.util.ArrayList;

public class DynamicLayer extends Layer {

	protected DynamicLayer(int neurons) {
		super(neurons);
		for (int i = 0; i < this.neurons.length; i++)
			this.neurons[i] = new DynamicNeuron(null);
	}

	public void connectPredecesor(Layer predecesor) {
		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new DynamicNeuron(predecesor.neurons);
		}
	}
}
