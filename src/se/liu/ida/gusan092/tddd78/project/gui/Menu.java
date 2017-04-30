package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.gui.Component.State;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu extends JMenuBar
{
    private Map<State, List<JMenuItem>> enableForState = new EnumMap<>(State.class);
    private Map<State, List<JMenuItem>> disableForState = new EnumMap<>(State.class);


    private State state = State.START;

    public Menu(final Window window) {
        super();
	List<JMenuItem> startDisable = new ArrayList<>();
	List<JMenuItem> highScoreDisable = new ArrayList<>();
	List<JMenuItem> submitDisable = new ArrayList<>();
	List<JMenuItem> gameDisable = new ArrayList<>();
	List<JMenuItem> gameEnable = new ArrayList<>();

	JMenu menu = new JMenu("Navigate to");

	JMenuItem item = new JMenuItem("Start", KeyEvent.VK_S);
	item.setMnemonic(KeyEvent.VK_S);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
 	item.addActionListener(window::toStart);
 	item.setEnabled(false);
 	startDisable.add(item);
 	menu.add(item);

	item = new JMenuItem("High Scores", KeyEvent.VK_H);
	item.setMnemonic(KeyEvent.VK_H);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
 	item.addActionListener(window::toHighScore);
 	item.setEnabled(true);
 	highScoreDisable.add(item);
 	menu.add(item);

	this.add(menu);

        menu = new JMenu("Game");

	item = new JMenuItem("Continue", KeyEvent.VK_C);
	item.setMnemonic(KeyEvent.VK_C);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
 	item.addActionListener(window::toGame);
 	gameDisable.add(item);
 	submitDisable.add(item);
 	menu.add(item);

	item = new JMenuItem("New", KeyEvent.VK_N);
	item.setMnemonic(KeyEvent.VK_N);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
 	item.addActionListener(window::newGame);
        menu.add(item);

	item = new JMenuItem("Pause/Resume", KeyEvent.VK_P);
	item.setMnemonic(KeyEvent.VK_P);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
 	item.addActionListener(window::repauseGame);
 	item.setEnabled(false);
 	gameEnable.add(item);
 	menu.add(item);

	item = new JMenuItem("Save", KeyEvent.VK_S);
	item.setMnemonic(KeyEvent.VK_S);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
 	item.addActionListener(window::saveGame);
 	item.setEnabled(false);
 	gameEnable.add(item);
 	menu.add(item);

 	this.add(menu);

	disableForState.put(State.START, startDisable);
	disableForState.put(State.HIGH_SCORE, highScoreDisable);
	disableForState.put(State.SUBMIT, submitDisable);
	disableForState.put(State.GAME, gameDisable);
	enableForState.put(State.GAME, gameEnable);

    }

    public void setState(final State newState) {
	List<JMenuItem> disableItems = enableForState.get(state);
	if (disableItems != null) {
	    for (JMenuItem item : disableItems) {
		item.setEnabled(false);
	    }
	}

	List<JMenuItem> enableItems = disableForState.get(state);
	if (enableItems != null) {
	    for (JMenuItem item : enableItems) {
		item.setEnabled(true);
	    }
	}

	state = newState;
	disableItems = disableForState.get(state);
	if (disableItems != null) {
	    for (JMenuItem item :disableItems) {
		item.setEnabled(false);
	    }
	}

	enableItems = enableForState.get(state);
	if (enableItems != null) {
	    for (JMenuItem item : enableItems) {
		item.setEnabled(true);
	    }
	}
	repaint();
    }
}
