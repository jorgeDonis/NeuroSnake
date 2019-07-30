package brain;

abstract class Layer {
	protected Neuron[] neurons;
	
	public Layer(int numberNeurons) {
		neurons = new Neuron[numberNeurons];
	}
	
	public void randomInit() {
		for (int i = 0; i < neurons.length; i++)
			neurons[i].randomInit();
	}
	
	public String toString() {
		String foo = "";
		for (int i = 0; i < neurons.length; i++) {
			Double d = Double.parseDouble(String.format("%.2f", neurons[i].rawValue));
			foo += "[" + d + "] ";
		}
		return foo;
	}
	
	public double[] getRawValues() {
		double[] rawValues = new double[neurons.length];
		for (int i = 0; i < neurons.length; i++)
			rawValues[i] = neurons[i].rawValue;
		return rawValues;
	}
}
