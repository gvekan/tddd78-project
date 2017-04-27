package se.liu.ida.gusan092.tddd78.project.gui.Component;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;

public abstract class WComponent extends JComponent
{
    private final State state;

    public WComponent(final State state) {
	this.state = state;
    }

    public State getState() {
	return state;
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(Game.WIDTH, Game.HEIGHT);
    }
}
