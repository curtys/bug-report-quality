package ch.unibe.scg.curtys.bugreportquality.network;

import ch.unibe.scg.curtys.bugreportquality.configuration.Configuration;
import ch.unibe.scg.curtys.bugreportquality.configuration.ConfigurationException;
import ch.unibe.scg.curtys.bugreportquality.configuration.NodeConfiguration;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author curtys
 */
class NetworkFactory {

	static BayesianNetwork createBayesianNetwork(Configuration networkConfiguration)
			throws ConfigurationException {
		final Set<Node> nodes = new HashSet<>();
		networkConfiguration.getNodes().forEach((ThrowingConsumer<NodeConfiguration>) nodeConf -> {
			Class cls = nodeConf.getNodeClass();
			if(!Node.class.isAssignableFrom(cls))
				throw new ConfigurationException("Unsupported node class: Node class must inherit from " + Node.class.getCanonicalName());
			try {
				Constructor<Node> nodeConstructor = cls.getConstructor(String.class, String.class);
				Node n = nodeConstructor.newInstance(nodeConf.getId(), nodeConf.getName());
//				Node n = (Node) nodeConf.getNodeClass()
//						.getConstructor(String.class, String.class).newInstance(nodeConf.getId(), nodeConf.getName());
				n.setProbabilities(nodeConf.getProbabilities());
				nodes.add(n);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new ConfigurationException("Could not create instance for node " + nodeConf.getId() + ": " + e.getMessage());
			}
		});

		if (nodes.size() != networkConfiguration.getNodes().size())
			throw new ConfigurationException("Duplicate node IDs: IDs must be unique");

		networkConfiguration.getNodes().forEach((ThrowingConsumer<NodeConfiguration>) nodeConf -> {
			Node n1 = getNodeFromSet(nodeConf.getId(), nodes);
			Set<String> connections = nodeConf.getConnections();
			if(connections != null) {
				connections.forEach((ThrowingConsumer<String>) id -> {
					Node n2 = getNodeFromSet(id, nodes);
					if (n1.equals(n2)) throw new ConfigurationException("Node " + n1.getId() + " cannot connect to itself");
					connectNodes(n1, n2);
				});
			}
		});

		Map<Integer, Set<Node>> resolvedMap = resolveMapping(networkConfiguration.getVectorMapping(), nodes);
		BayesianNetwork net = new BayesianNetwork(nodes);
		net.setVectorMapping(resolvedMap);

		return net;
	}

	private static Node getNodeFromSet(String id, Set<Node> set) {
		Optional<Node> node = set.stream().filter(n -> n.getId().equals(id)).findFirst();
		return node.isPresent() ? node.get() : null;
	}

	private static void connectNodes(Node n1, Node n2) {
		n1.connect(n2);
		n2.connect(n1);
	}

	private static Map<Integer,Set<Node>> resolveMapping(Map<Integer, List<String>> vectorMapping, Set<Node> nodes) {
		Map<Integer, Set<Node>> map = new HashMap<>();
		vectorMapping.forEach((key, val) -> {
			Set<Node> linkedNodes = new LinkedHashSet<>();
			val.forEach(n -> linkedNodes.add(getNodeFromSet(n, nodes)));
			map.put(key, linkedNodes);
		});
		return map;
	}

	@FunctionalInterface
	private interface ThrowingConsumer<T> extends Consumer<T> {

		@Override
		default void accept(final T elem) {
			try {
				acceptThrows(elem);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		void acceptThrows(T elem) throws Exception;
	}

}
