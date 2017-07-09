package ch.unibe.scg.curtys.bugreportquality.configuration;


import ch.unibe.scg.curtys.bugreportquality.score.ScoreLevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * @author curtys
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

	private List<NodeConfiguration> nodes;
	private Map<Integer, List<String>> vectorMapping;
	private List<ScoreLevel> scores;

	public Configuration() {}

	public Configuration(List<NodeConfiguration> nodes)
			throws ConfigurationException {
		this.nodes = nodes;
		if (!checkNodes()) throw new ConfigurationException("Duplicate Node ID");
	}

	private boolean checkNodes() {
		int numNodes = nodes.size();
		for (int i = 0; i < numNodes; i++) {
			String nodeId = nodes.get(i).getId();
			for (int y = i+1; y < numNodes; y++) {
				if (nodeId.equals(nodes.get(y).getId())) return false;
			}
		}
		return true;
	}

	public List<NodeConfiguration> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeConfiguration> nodes) {
		this.nodes = nodes;
	}

	public Map<Integer, List<String>> getVectorMapping() {
		return vectorMapping;
	}

	public void setVectorMapping(Map<Integer, List<String>> vectorMapping) {
		this.vectorMapping = vectorMapping;
	}

	public List<ScoreLevel> getScores() {
		return scores;
	}

	public void setScores(List<ScoreLevel> scores) {
		this.scores = scores;
	}
}
