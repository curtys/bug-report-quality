package ch.unibe.scg.curtys.bugreportquality.score;

/**
 * @author curtys
 */
public class ScoreLevel {
	private double threshold;
	private int score;
	private String id;

	public ScoreLevel() {}

	boolean fits(double d) {
		return d >= threshold;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
