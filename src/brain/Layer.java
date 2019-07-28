package brain;

import java.util.ArrayList;
import java.util.List;

abstract class Layer {
	protected Neuron[] neurons;
	
	public Layer(int numberNeurons) {
		neurons = new Neuron[numberNeurons];
	}
}
