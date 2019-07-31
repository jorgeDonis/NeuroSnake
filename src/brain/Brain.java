package brain;

import basicTypes.Coordinate;
import basicTypes.Direction;
import snake.Snake;

import static snake.Constants.*;

import java.util.ArrayList;
import java.util.List;

public class Brain {

	public InputLayer input;
	private DynamicLayer[] dynamicLayers;

	public static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public Brain(int inputSize, int numberHiddenLayers, int neuronsPerHiddenLayer, int outputSize) {
		input = new InputLayer(inputSize);
		// dynamic = hidden + output(1)
		dynamicLayers = new DynamicLayer[numberHiddenLayers + 1];
		DynamicLayer firstHidden = new DynamicLayer(neuronsPerHiddenLayer);
		firstHidden.connectPredecesor(input);
		dynamicLayers[0] = firstHidden;
		for (int i = 1; i < numberHiddenLayers; i++) {
			DynamicLayer dynamicLayer = new DynamicLayer(neuronsPerHiddenLayer);
			dynamicLayer.connectPredecesor(dynamicLayers[i - 1]);
			dynamicLayers[i] = dynamicLayer;
		}
		DynamicLayer outputLayer = new DynamicLayer(outputSize);
		outputLayer.connectPredecesor(dynamicLayers[numberHiddenLayers - 1]);
		dynamicLayers[numberHiddenLayers] = outputLayer;
	}

	public void randomInit(boolean initInput) {
		if (initInput)
			input.randomInit();
		for (int i = 0; i < dynamicLayers.length; i++)
			dynamicLayers[i].randomInit();
	}

	public void propagate() {
		for (int i = 0; i < dynamicLayers.length; i++)
			dynamicLayers[i].propagate();
	}

	public void print() {
		System.out.println("Inputs : " + input);
		for (int i = 0; i < dynamicLayers.length - 1; i++)
			System.out.println("Hidden Layer " + (i + 1) + ": " + dynamicLayers[i]);
		System.out.println("Output : " + dynamicLayers[dynamicLayers.length - 1]);
	}

	// Snake brain
	public Brain() {
		this((WIDTH - 2) * (HEIGHT - 2), NUMBER_HIDDEN_LAYERS, NEURONS_PER_HIDDEN_LAYER, 4);
	}
	
	//Initializes input parameters for neural network with snake and food data.
	//0 = blank, 1 = body, 2 = head, 3 = food
	public void readInput(Snake snake, Coordinate foodLocation) {
		if (snake.hasCrashed()) return;
		int n = (WIDTH - 2) * (HEIGHT - 2);
		double[] input = new double[n];
		for (Coordinate coord : snake.shape)
			input[(coord.x - 1) + (coord.y - 1) * (WIDTH - 2)] = 1;
		input[(snake.getHeadPos().x - 1) + (snake.getHeadPos().y - 1) * (WIDTH - 2)] = 2;
		input[(foodLocation.x - 1) + (foodLocation.y - 1) * (WIDTH - 2)] = 3;
		this.input.readInput(input);
	}
	
	public Direction predict() {
		Layer outputLayer = dynamicLayers[dynamicLayers.length - 1];
		propagate();
		double[] output = outputLayer.getRawValues();
		double maxOutput = 0;
		int predictionIndex = 0;
		for (int i = output.length - 1; i >= 0; i--) {
			if (output[i] > maxOutput) {
				maxOutput = output[i];
				predictionIndex = i;
			}
		}
		switch (predictionIndex) {
		case 0:
			return Direction.Up;
		case 1:
			return Direction.Down;
		case 2:
			return Direction.Left;
		case 3:
			return Direction.Right;
		default:
			throw new RuntimeException("Unexpected neural network output");
		}
	}
	
	public List<double[][]> getWeights(){
		List<double[][]> allWeights = new ArrayList<double[][]>(dynamicLayers.length);
		for (DynamicLayer layer : dynamicLayers)
			allWeights.add(layer.getWeights());
		return allWeights;
	}
}
