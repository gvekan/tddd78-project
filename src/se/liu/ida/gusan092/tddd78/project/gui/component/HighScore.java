package se.liu.ida.gusan092.tddd78.project.gui.component;

import se.liu.ida.gusan092.tddd78.project.properties.HighScoreProperties;
import se.liu.ida.gusan092.tddd78.project.properties.Score;

import javax.swing.*;
import java.util.List;
import java.awt.*;

/**
 * A component displaying the highscores
 */
public class HighScore extends AComponent
{

    public HighScore() {
	super(State.HIGH_SCORE);
	List<Score> scores = HighScoreProperties.getInstance().getHighScores();
	setLayout(new GridLayout(scores.size(), 0));

	for (int i = 0; i < scores.size(); i++) {
	    final Score score = scores.get(i);
	    add(new JLabel(Integer.toString(i+1) + "."));
	    add(new JLabel(score.getName()));
	    add(new JLabel(Integer.toString(score.getPoints())));
	    add(new JLabel(score.getDate().toString()));
	}
    }
}
