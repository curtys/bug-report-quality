package ch.unibe.scg.curtys.bugreportquality.score;

import ch.unibe.scg.curtys.bugreportquality.configuration.Configuration;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author curtys
 */
public class ScoreMapping {

	private List<ScoreLevel> levels;

	public static ScoreMapping create(Configuration configuration) {
		return ScoreMappingFactory.createMapping(configuration);
	}

	public ScoreMapping(List<ScoreLevel> scoreLevels) {
		this.levels = scoreLevels;
	}

	public int score(double d) {
		Optional<ScoreLevel> max = levels.stream().filter(l -> l.fits(d))
				.max(Comparator.comparingInt(ScoreLevel::getScore));
		return max.get().getScore();
	}

	public void setLevels(List<ScoreLevel> levels) {
		this.levels = levels;
	}
}
