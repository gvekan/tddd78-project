package se.liu.ida.gusan092.tddd78.project.properties;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Used as a instance and handling the highscore properties
 */
public final class HighScoreProperties extends AppProperties
{
    private static final HighScoreProperties INSTANCE = new HighScoreProperties();
    private HighScoreList highScoreList = new HighScoreList();
    private static final String SPLIT = ",";

    private HighScoreProperties() {
        super("high_scores.properties");
	System.out.println(prop.size());
	for (int i = 0; i < prop.size(); i++) {
	    System.out.println(i);
	    String data = prop.getProperty(Integer.toString(i));
	    String[] parts = data.split(SPLIT);
	    String name;
	    if (parts.length > 3) {
		StringBuilder builder = new StringBuilder();
		for (int j = 0; j < parts.length-2; j++) {
		    builder.append(parts[i]);
		}
		name = builder.toString();
	    } else name = parts[0];
	    highScoreList.add(new Score(name, Integer.parseInt(parts[1]), LocalDate.parse(parts[2])));
	}
    }

    public static HighScoreProperties getInstance() {
        return INSTANCE;
    }

    public List<Score> getHighScores() {
        return getHighScores(highScoreList.size());
    }

    public List<Score> getHighScores(int max) {
        List<Score> res = new ArrayList<>();
	int limit = Game.clamp(max,0, highScoreList.size());
	for (int i = 0; i < limit; i++) {
	    res.add(highScoreList.get(i));
	}
	return res;
    }

    public void addHighScore(Score score) {
        int index = highScoreList.add(score);
        String newPlaceHolder = score.getName() + SPLIT + score.getPoints() + SPLIT + score.getDate();
	for (int i = index; i < prop.size(); i++) {
	    String place = Integer.toString(i);
	    String oldPlaceHolder = prop.getProperty(place);
	    prop.setProperty(place,newPlaceHolder);
	    newPlaceHolder = oldPlaceHolder;
	}
	prop.setProperty(Integer.toString(prop.size()), newPlaceHolder);
	write();
    }


}
