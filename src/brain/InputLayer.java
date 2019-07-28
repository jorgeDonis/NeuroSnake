package brain;

import java.util.ArrayList;

public class InputLayer extends Layer {

	protected InputLayer(int neurons) {
		super(neurons);
		for (int i = 0; i < neurons; i++)
			this.neurons[i] = new InputNeuron();
	}

}
