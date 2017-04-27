package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.properties.AppProperties;
import se.liu.ida.gusan092.tddd78.project.properties.HighScore;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class HighScoreComponent extends JComponent
{
/*    private JPanel panelLeft = new JPanel(new GridLayout());
    private JPanel panelCenter = new JPanel(new GridLayout());
    private JPanel panelRight = new JPanel(new GridLayout());*/

    public HighScoreComponent() {
	super();
	List<HighScore> highScores = AppProperties.getInstance().getHighScores();
	this.setLayout(new GridLayout(highScores.size(),4));

	for (int i = 0; i < highScores.size(); i++) {
	    final HighScore highScore = highScores.get(i);
	    this.add(new JLabel(Integer.toString(i+1) + "."));
	    this.add(new JLabel(highScore.getName()));
	    this.add(new JLabel(Integer.toString(highScore.getPoints())));
	    this.add(new JLabel(highScore.getDate().toString()));
	}
/*	this.add(panelLeft, BorderLayout.WEST);
	this.add(panelCenter, BorderLayout.CENTER);
	this.add(panelRight, BorderLayout.EAST);*/
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(Game.WIDTH, Game.HEIGHT);
    }
}
