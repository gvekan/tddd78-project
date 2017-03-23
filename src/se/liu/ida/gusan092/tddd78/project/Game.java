package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;

    private Player player;

    public Game(final Player player) {
	this.player = player;
    }

    public Player getPlayer() {
	return player;
    }
}
