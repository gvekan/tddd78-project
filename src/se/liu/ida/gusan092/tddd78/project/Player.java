package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Player extends GameObject
{
    private static final double WIDTH = 20;
    private static final double HEIGHT = 40;

    private double x = (Game.WIDTH-WIDTH)/2;
    private double y = Game.HEIGHT-HEIGHT;


    public Player() {
        super(new Rectangle2D.Double((Game.WIDTH-WIDTH)/2,Game.HEIGHT-HEIGHT,WIDTH,HEIGHT));
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        shape = new Rectangle2D.Double(this.x,this.y,width,height);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.CYAN);
        graphics2D.fill(shape);
    }
}
