package se.liu.ida.gusan092.tddd78.project.properties;

import java.util.ArrayList;
import java.util.List;

public class HighScoreList
{
    private List<Score> scores = new ArrayList<>();

    public int add(Score newScore) {
	for (int i = 0; i < scores.size(); i++) {
	    final Score score = scores.get(i);
	    if (newScore.getPoints() >= score.getPoints()) {
	        scores.add(i, newScore);
	        return i;
	    }
	}
	scores.add(newScore);
	return scores.size() - 1;

    }

    public Score get(int index) {
        return scores.get(index);
    }

    public int size() {
        return scores.size();
    }
}
