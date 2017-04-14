package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    private Game game;

    public Window() {
        super("Game");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	this.setResizable(true);
 	this.add(new GameButton(this));
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

    @Override public Dimension getPreferredSize() {
	return new Dimension(1000,1000);
    }

    public static void main(String[] args) {
	new Window();
    }
}
