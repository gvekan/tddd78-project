package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Window extends JFrame
{
    private Game game = null;
    private JMenuBar menu;
    private Component comp;

    public Window() {
        super("Game");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	this.setResizable(false);
 	comp = new StartComponent(this);
 	this.add(comp);
 	this.pack();
 	this.setVisible(true);
 	this.setFocusable(true);
    }

    public Window(final Game game) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(game);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.game = game;
        game.start();
    }


    public void newGame(final ActionEvent e) { //It has to be a param e
	if (game != null) {
	    game.paus();
	    if (JOptionPane.showConfirmDialog(null, "Vill du verkligen avsluta?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		game.stop();
	    } else {
		game.resume();
		return;
	    }
	}
	game = new Game();
	if (comp != null) {
	    this.remove(comp);
	}
	this.add(game);
	this.pack();
	game.start();
	this.repaint();
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(1000,1000);
    }

    public static void main(String[] args) {
	new Window();
    }
}
