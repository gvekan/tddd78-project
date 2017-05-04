package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.gui.component.State;
import se.liu.ida.gusan092.tddd78.project.gui.component.HighScore;
import se.liu.ida.gusan092.tddd78.project.gui.component.Start;
import se.liu.ida.gusan092.tddd78.project.gui.component.Submit;
import se.liu.ida.gusan092.tddd78.project.gui.component.AComponent;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;


/**
 * The frame of the app, displaying different components
 */
public class App extends JFrame
{
    private Game game = null;
    private Menu menu = new Menu(this);
    private AComponent comp;


    public App() {
        super("Game");
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	setResizable(false);
 	comp = new Start(this);
 	add(comp);
	setJMenuBar(menu);
 	pack();
 	setVisible(true);
 	setFocusable(true);
    }

    private boolean hasGame() {
        return game != null;
    }

    private boolean hasComp() {
        return comp != null;
    }

    private void replaceState(State state) {
	if (hasComp()) remove(comp);
	else {
	    game.stopRender();
	    remove(game);
	}
	comp = null;
	menu.setState(state);
    }

    private void setComp(AComponent comp) {
        replaceState(comp.getState());
        this.comp = comp;
        add(comp);
	pack();
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
	replaceState(State.GAME);
	game = new Game(this);
	add(game);
	pack();
	game.start();
	repaint();
    }

    public void repauseGame(final ActionEvent e){ //It has to be a param e
        if (game.isRunning()) game.pause();
	else game.resume();
    }

    public void saveGame(final ActionEvent e) { //It has to be a param e
	if (game.isRunning()) game.pause();
	SavedProperties.getInstance().saveGame(game);
	setComp(new Start(this));
    }

    public void toStart(final ActionEvent e){ //It has to be a param e
        if (hasGame()) game.pause();
        setComp(new Start(this));
    }

    public void toHighScore(final ActionEvent e){ //It has to be a param e
        if (hasGame()) game.pause();
        setComp(new HighScore());
    }

    public void continueGame(final ActionEvent e) {
        if (hasGame()) {
            if (game.isGameOver()) {
                game.gameOver();
                return;
	    }
	    replaceState(State.GAME);
	    add(game);
	    pack();
	    repaint();
	    game.startRender();
	}
	else if (SavedProperties.getInstance().hasGame()) {
	    game = SavedProperties.getInstance().getGame(this);
	    replaceState(State.GAME);
	    add(game);
	    pack();
	    repaint();
	    game.startRender();
	}
	else newGame(e);
    }

    public void gameOver(Player player) {
        setComp(new Submit(this, player));
    }

    /**
     * Not done, will be used in a extension for multiplaying
     * Should only be used when multiplaying
     * @param players the players in a game
     */
    public void gameOver(List<Player> players) {
    }

    public void removeGame() {
        game = null;
    }

    public static void main(String[] args) {
	App app = new App();
    }
}
