package brain;

public abstract class Neuron {
	protected double rawValue;
	public Neuron() {
		rawValue = 0;
	}
	abstract void randomInit();
}
