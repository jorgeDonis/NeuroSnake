package brain;

public class InputLayer extends Layer {

	protected InputLayer(int neurons) {
		super(neurons);
		for (int i = 0; i < neurons; i++)
			this.neurons[i] = new InputNeuron();
	}
	
	protected void readInput(double[] inputData) {
		if (inputData.length != neurons.length)
			throw new RuntimeException("Input data length and model input data length mismatch!");
		for (int i = 0; i < neurons.length; i++)
			neurons[i].rawValue = inputData[i];
	}

}
