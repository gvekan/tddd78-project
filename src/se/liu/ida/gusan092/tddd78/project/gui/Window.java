package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Window extends JFrame
{
    private Game game = null;
    private Menu menu = new Menu(this);
    private JComponent comp;


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

    private boolean hasGame() {
        return game != null;
    }

    private boolean hasComp() {
        return comp != null;
    }

    private void replaceState(ComponentState state) {
	if (hasComp()) this.remove(comp);
	else {
	    game.stopRender();
	    this.remove(game);
	}
	comp = null;
	menu.setState(state);
    }

    private void setComp(JComponent comp, ComponentState state) {
        replaceState(state);
        this.comp = comp;
        this.add(comp);
	this.pack();
        repaint();
    }

    public void newGame(final ActionEvent e) { //It has to be a param e
	if (hasGame()) {
	    boolean wasRunning = false;
	    if (game.isRunning()) {
		game.pause();
		wasRunning = true;
	    }
	    if (JOptionPane.showConfirmDialog(null, "Do you really want to start a new game?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		game.stop();
	    } else {
	        if (wasRunning) game.resume();
	        return;
	    }
	}
	replaceState(ComponentState.GAME);
	game = new Game();
	this.add(game);
	this.pack();
	game.start();
	repaint();
    }

    public void repauseGame(final ActionEvent e){
        if (game.isRunning()) game.pause();
	else game.resume();
    }

    public void toStart(final ActionEvent e){
        if (hasGame()) game.pause();
        setComp(new StartComponent(this), ComponentState.START);
    }

    public void toHighScore(final ActionEvent e){
        if (hasGame()) game.pause();
        setComp(new HighScoreComponent(), ComponentState.HIGH_SCORE);
    }

    public void toGame(final ActionEvent e) {
        if (hasGame()) {
	    replaceState(ComponentState.GAME);
	    this.add(game);
	    this.pack();
	    repaint();
	    game.startRender();
	} else newGame(e);
    }

    public static void main(String[] args) {
	new Window();
    }
}
