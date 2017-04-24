package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Window extends JFrame
{
    private Game game = null;
    private Menu menu = new Menu(this);
    private Component comp;

    public Window() {
        super("Game");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	this.setResizable(false);
 	comp = new StartComponent(this);
 	this.add(comp);
	this.setJMenuBar(menu);
 	this.pack();
 	this.setVisible(true);
 	this.setFocusable(true);
    }

    public Window(final Game game) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(game);
	this.setJMenuBar(menu);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        this.game = game;
        game.start();
    }


    public void newGame(final ActionEvent e) { //It has to be a param e
	if (game != null) {
	    game.pause();
	    if (JOptionPane.showConfirmDialog(null, "Vill du verkligen avsluta?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		game.stop();
		this.remove(game);
	    } else {
		game.resume();
		return;
	    }
	}
	if (comp != null) {
	    this.remove(comp);
	}
	menu.setState(ComponentState.GAME);
	game = new Game();
	this.add(game);
	this.pack();
	repaint();
	game.start();
    }

    public void pauseGame(final ActionEvent e){
        game.pause();
    }

    public void resumeGame(final ActionEvent e){
        game.resume();
    }

    public static void main(String[] args) {
	new Window();
    }
}
