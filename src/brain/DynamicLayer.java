package brain;

import java.util.ArrayList;

public class DynamicLayer extends Layer {

	protected DynamicLayer(int neurons) {
		super(neurons);
	}

	public void connectPredecesor(Layer predecesor) {
		for (int i = 0; i < neurons.length; i++)
			neurons[i] = new DynamicNeuron(predecesor.neurons);
	}
	
	public void propagate() {
		for (int i = 0; i < neurons.length; i++)
			((DynamicNeuron) neurons[i]).propagate();
	}
}
