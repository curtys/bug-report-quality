package ch.unibe.scg.curtys.bugreportquality.network;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author curtys
 */
public abstract class Node {

	private String name;
	private String id;
	private List<Double> probabilities;
	protected Set<Node> connections;
	private boolean active;

	public Node(String id, String name) {
		this.id = id;
		this.name = name;
		this.probabilities = new ArrayList<>();
		this.connections = new HashSet<>();
	}

	public void connect(Node target) {
		this.connections.add(target);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void activate() {
		this.active = true;
	}

	public void deactivate() {
		this.active = false;
	}

	public boolean isActive() {
		return this.active;
	}

	public abstract double compute();

	public abstract double inhibitor();

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Node node = (Node) o;

		return id.equals(node.id);
	}

	@Override public int hashCode() {
		return id.hashCode();
	}

	public List<Double> getProbabilities() {
		return probabilities;
	}

	public void setProbabilities(List<Double> probabilities) {
		this.probabilities = probabilities;
	}
}
