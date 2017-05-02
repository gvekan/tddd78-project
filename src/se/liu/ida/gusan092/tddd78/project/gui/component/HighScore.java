package se.liu.ida.gusan092.tddd78.project.gui.component;

import se.liu.ida.gusan092.tddd78.project.properties.HighScoreProperties;
import se.liu.ida.gusan092.tddd78.project.properties.Score;

import javax.swing.*;
import java.util.List;
import java.awt.*;

/**
 * A component displaying the highscores
 */
public class HighScore extends WComponent
{

    public HighScore() {
	super(State.HIGH_SCORE);
	List<Score> scores = HighScoreProperties.getInstance().getHighScores();
	this.setLayout(new GridLayout(scores.size(), 0));

	for (int i = 0; i < scores.size(); i++) {
	    final Score score = scores.get(i);
	    this.add(new JLabel(Integer.toString(i+1) + "."));
	    this.add(new JLabel(score.getName()));
	    this.add(new JLabel(Integer.toString(score.getPoints())));
	    this.add(new JLabel(score.getDate().toString()));
	}
    }
}
