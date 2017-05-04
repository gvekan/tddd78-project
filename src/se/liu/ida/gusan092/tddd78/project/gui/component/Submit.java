package se.liu.ida.gusan092.tddd78.project.gui.component;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.gui.App;
import se.liu.ida.gusan092.tddd78.project.properties.HighScoreProperties;
import se.liu.ida.gusan092.tddd78.project.properties.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

/**
 * A component used to submit a users game to the highscore property
 */
public class Submit extends AComponent
{
    /**
     * The amount of columns in the textfield
     */
    public static final int COLUMNS = 20;
    private int score;
    private LocalDate date;
    private JTextField name = new JTextField(COLUMNS);
    private App app;

    public Submit(final App app, final Player player) {
	super(State.SUBMIT);
	score = player.getScore();
    	date = LocalDate.now();
    	this.app = app;
    	setLayout(new GridLayout(2,0));
    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(new JLabel(Integer.toString(score)), BorderLayout.CENTER);
    	add(panel);
	panel = new JPanel(new FlowLayout());
	JLabel label = new JLabel("Name: ");
	JButton button = new JButton("Submit");
	button.addActionListener(this::submit);
	panel.add(label);
    	panel.add(name);
    	panel.add(button);
	add(panel);
    }

    public void submit(final ActionEvent e){
	HighScoreProperties.getInstance().addHighScore(new Score(name.getText(), score, date));
	app.removeGame();
	app.toHighScore(e);
    }
}
