package se.liu.ida.gusan092.tddd78.project.gui;




import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu extends JMenuBar
{
    private Map<ComponentState, List<JMenuItem>> enableForState = new EnumMap<>(ComponentState.class);
    private Map<ComponentState, List<JMenuItem>> disableForState = new EnumMap<>(ComponentState.class);


    private ComponentState state = ComponentState.START;

    public Menu(final Window window) {
        super();
	List<JMenuItem> startDisable = new ArrayList<>();
	List<JMenuItem> gameEnable = new ArrayList<>();
	List<JMenuItem> gameDisable = new ArrayList<>();

	JMenu menu = new JMenu("Navigate to");

	JMenuItem item = new JMenuItem("Start", KeyEvent.VK_S);
	item.setMnemonic(KeyEvent.VK_S);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
 	item.addActionListener(window::toStart);
 	item.setEnabled(false);
 	startDisable.add(item);
 	menu.add(item);

	this.add(menu);

        menu = new JMenu("Game");

	item = new JMenuItem("CONTINUE", KeyEvent.VK_C);
	item.setMnemonic(KeyEvent.VK_C);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
 	item.addActionListener(window::toGame);
 	item.setEnabled(true);
 	gameDisable.add(item);
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

 	this.add(menu);

 	enableForState.put(ComponentState.GAME, gameEnable);
	disableForState.put(ComponentState.START, startDisable);
	disableForState.put(ComponentState.GAME, gameDisable);

    }

    public void setState(final ComponentState newState) {
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
