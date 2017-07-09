package ch.unibe.scg.curtys.bugreportquality.network;

/**
 * @author curtys
 */
public class BinaryNode extends Node {
	public BinaryNode(String id, String name) {
		super(id, name);
	}

	@Override public double compute() {
		return 1-inhibitor();
	}

	@Override public double inhibitor() {
		return 1-getProbabilities().get(0);
	}
}
