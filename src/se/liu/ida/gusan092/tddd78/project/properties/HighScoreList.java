package se.liu.ida.gusan092.tddd78.project.properties;

import java.util.ArrayList;
import java.util.List;

public class HighScoreList
{
    private List<HighScore> highScores = new ArrayList<>();

    public int add(HighScore newHighScore) {
	for (int i = 0; i < highScores.size(); i++) {
	    final HighScore highScore = highScores.get(i);
	    if (newHighScore.getPoints() >= highScore.getPoints()) {
	        highScores.add(i, newHighScore);
	        return i;
	    }
	}
	highScores.add(newHighScore);
	return highScores.size()-1;

    }

    public HighScore get(int index) {
        return highScores.get(index);
    }
}
