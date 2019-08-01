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

	public double[][] getWeights() {
		double[][] layerWeights = new double[neurons.length][];
		for (int i = 0; i < neurons.length; i++) {
			int n = ((DynamicNeuron) neurons[i]).getWeights().length;
			layerWeights[i] = new double[n];
			for (int j = 0; j < n; j++) {
				layerWeights[i][j] = ((DynamicNeuron) neurons[i]).getWeights()[j];
			}
		}
		return layerWeights;
	}

	public void setWeights(double[][] layerWeights) {
		for (int i = 0; i < neurons.length; i++)
			((DynamicNeuron) neurons[i]).setWeights(layerWeights[i]);
	}
}
