package ch.unibe.scg.curtys.bugreportquality.score;

import ch.unibe.scg.curtys.bugreportquality.configuration.Configuration;

import java.util.List;

/**
 * @author curtys
 */
class ScoreMappingFactory {
	static ScoreMapping createMapping(Configuration config) {
		List<ScoreLevel> levels = config.getScores();
		return new ScoreMapping(levels);
	}
}
