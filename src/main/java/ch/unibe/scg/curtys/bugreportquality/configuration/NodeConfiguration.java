package ch.unibe.scg.curtys.bugreportquality.configuration;

import java.util.List;
import java.util.Set;

/**
 * @author curtys
 */
public class NodeConfiguration {
	private String id;
	private Class nodeClass;
	private String name;
	private List<Double> probabilities;
	private Set<String> connections;

	public NodeConfiguration() {}

	NodeConfiguration(String id, Class nodeClass, String name, List<Double> probabilities, Set<String> connections)
			throws ConfigurationException {
		this.id = id;
		this.nodeClass = nodeClass;
		this.name = name;
		this.probabilities = probabilities;
		this.connections = connections;
		if (!checkProbabilities()) throw new ConfigurationException("NodeID " + id + ": Sum of probabilities exceeds 1");
	}

	private boolean checkProbabilities() {
		double sum = probabilities.stream().mapToDouble(Double::doubleValue).sum();
		if(sum > 1) return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(Class nodeClass) {
		this.nodeClass = nodeClass;
	}

	public List<Double> getProbabilities() {
		return probabilities;
	}

	public void setProbabilities(List<Double> probabilities) {
		this.probabilities = probabilities;
	}

	public Set<String> getConnections() {
		return connections;
	}

	public void setConnections(Set<String> connections) {
		this.connections = connections;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
