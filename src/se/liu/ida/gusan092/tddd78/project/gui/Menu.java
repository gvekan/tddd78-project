package se.liu.ida.gusan092.tddd78.project.gui;

import java.awt.event.KeyEvent;

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
        JMenu menu = new JMenu("File");

	JMenuItem item = new JMenuItem("New game",KeyEvent.VK_N);
 	item.addActionListener(window::newGame);
        menu.add(item);

	item = new JMenuItem("Pause game",KeyEvent.VK_P);
 	item.addActionListener(window::pauseGame);
 	item.setEnabled(false);
 	gameButtons.add(item);
 	menu.add(item);

	item = new JMenuItem("Resume game",KeyEvent.VK_R);
 	item.addActionListener(window::resumeGame);
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
