package se.liu.ida.gusan092.tddd78.project.gui;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu extends JMenuBar
{
    private Map<ComponentState, List<JMenuItem>> enableForState = new EnumMap<>(ComponentState.class);

    private ComponentState state = ComponentState.GUI;

    public Menu(final Window window) {
        super();
        List<JMenuItem> gameButtons = new ArrayList<>();
        JMenu menu = new JMenu("Options");

	JMenuItem item = new JMenuItem("New",KeyEvent.VK_N);
	item.setMnemonic(KeyEvent.VK_N);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
 	item.addActionListener(window::newGame);
        menu.add(item);

	item = new JMenuItem("Pause/Resume",KeyEvent.VK_P);
	item.setMnemonic(KeyEvent.VK_P);
	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
 	item.addActionListener(window::pauseGame);
 	item.setEnabled(false);
 	gameButtons.add(item);
 	menu.add(item);

 	this.add(menu);

 	enableForState.put(ComponentState.GAME, gameButtons);
	setState(state);
    }

    public void setState(final ComponentState newState) {
	List<JMenuItem> list = enableForState.get(state);
	if (list != null) {
	    for (JMenuItem item :list) {
		item.setEnabled(false);
	    }
	}
	state = newState;
	list = enableForState.get(state);
	if (list != null) {
	    for (JMenuItem item : list) {
		item.setEnabled(true);
	    }
	}
	repaint();
    }
}
