package brain;

import java.util.ArrayList;
import java.util.List;

public class Brain {

	public InputLayer input;
	private DynamicLayer[] dynamicLayers;

	protected double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public Brain(int inputSize, int numberHiddenLayers, int neuronsPerHiddenLayer,
			int outputSize) {
		input = new InputLayer(inputSize);
		//dynamic = hidden + output(1)
		dynamicLayers = new DynamicLayer[numberHiddenLayers + 1];
		DynamicLayer firstHidden = new DynamicLayer(neuronsPerHiddenLayer);
		firstHidden.connectPredecesor(input);
		dynamicLayers[0] = firstHidden;
		for (int i = 1; i < numberHiddenLayers; i++) {
			DynamicLayer dynamicLayer = new DynamicLayer(neuronsPerHiddenLayer);
			dynamicLayer.connectPredecesor(dynamicLayers[i-1]);
			dynamicLayers[i] = dynamicLayer;
		}
		DynamicLayer outputLayer = new DynamicLayer(outputSize);
		outputLayer.connectPredecesor(dynamicLayers[numberHiddenLayers - 1]);
		dynamicLayers[numberHiddenLayers] = outputLayer;
	}
}
