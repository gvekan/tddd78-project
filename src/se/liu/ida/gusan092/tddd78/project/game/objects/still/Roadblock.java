package se.liu.ida.gusan092.tddd78.project.game.objects.still;


import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Graphics2D;

import java.awt.*;

public class Roadblock extends StillObject
{

    public static final int WIDTH = 25;
    public static final int HEIGHT = 5;

    public Roadblock(final int x, final Type type, final Handler handler) {
	super(x, -HEIGHT, WIDTH, HEIGHT, new Color(255,69,0), type, handler);
    }
}
