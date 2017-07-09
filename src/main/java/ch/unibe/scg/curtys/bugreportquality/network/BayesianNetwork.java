package ch.unibe.scg.curtys.bugreportquality.network;

import ch.unibe.scg.curtys.bugreportquality.configuration.Configuration;
import ch.unibe.scg.curtys.bugreportquality.configuration.ConfigurationException;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author curtys
 */
public class BayesianNetwork {

	private Set<Node> nodes;
	private Map<Integer, Set<Node>> vectorMapping;

	private BayesianNetwork() {
	}

	BayesianNetwork(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public static BayesianNetwork create(Configuration networkConfiguration) throws ConfigurationException {
		return NetworkFactory.createBayesianNetwork(networkConfiguration);
	}

	public Node node(String id) {
		Optional<Node> optional = nodes.stream().filter(n -> n.getId().equals(id)).findFirst();
		return (optional.isPresent()) ? optional.get() : null;
	}

	public double query(int[] vector, String targetNode) {
		activateNodes(vector);
		return node(targetNode).compute();
	}

	private void activateNodes(int[] activations) {
		resetNodeActivations();
		for (int i = 0; i < activations.length; i++) {
			if (activations[i] == 0) continue;
			Set<Node> linkedNodes = vectorMapping.get(i);
			if (linkedNodes != null)
				linkedNodes.forEach(Node::activate);
		}
	}

	private void resetNodeActivations() {
		nodes.forEach(Node::deactivate);
	}

	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setVectorMapping(Map<Integer, Set<Node>> vectorMapping) {
		this.vectorMapping = vectorMapping;
	}
}
