package ch.unibe.scg.curtys.bugreportquality.network;

/**
 * @author curtys
 */
public class EffectNode extends Node {
	public EffectNode(String id, String name) {
		super(id, name);
	}

	@Override public double compute() {
		final double[] inhibitorTerm = { 1 };
		connections.stream().filter(Node::isActive).forEach(node -> inhibitorTerm[0] = inhibitorTerm[0] * node.inhibitor());
		return 1-inhibitorTerm[0];
	}

	@Override public double inhibitor() {
		return 0;
	}
}
