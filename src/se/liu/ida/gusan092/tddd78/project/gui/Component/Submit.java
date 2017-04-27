package se.liu.ida.gusan092.tddd78.project.gui.Component;

import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;
import se.liu.ida.gusan092.tddd78.project.gui.Window;
import se.liu.ida.gusan092.tddd78.project.properties.AppProperties;
import se.liu.ida.gusan092.tddd78.project.properties.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class Submit extends WComponent
{
    private int score;
    private LocalDate date;
    private JTextField name = new JTextField(20);
    private Window window;

    public Submit(final Window window, final Player player) {
	super(State.SUBMIT);
	score = player.getScore();
    	date = LocalDate.now();
    	this.window = window;
    	this.setLayout(new GridLayout(2,0));
    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(new JLabel(Integer.toString(score)), BorderLayout.CENTER);
    	this.add(panel);
	panel = new JPanel(new FlowLayout());
	JLabel label = new JLabel("Name: ");
	JButton button = new JButton("Submit");
	button.addActionListener(this::submit);
	panel.add(label);
    	panel.add(name);
    	panel.add(button);
	this.add(panel);
    }

    public void submit(final ActionEvent e){
	AppProperties.getInstance().addHighScore(new Score(name.getText(), score, date));
	window.removeGame();
	window.toHighScore(e);
    }
}
