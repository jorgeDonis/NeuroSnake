package brain;

import java.util.Random;

public class InputNeuron extends Neuron {
	@Override
	void randomInit() {
		Random random = new Random();
		rawValue = (random.nextDouble() * 10);
	}
}
