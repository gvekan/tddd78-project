package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Player implements Vehicle
{
    private double x;
    private double y;
    private double width;
    private double height;

    private Shape shape;

    public Player(final double width, final double height) {
	this.width = width;
	this.height = height;
        x = (Game.WIDTH-width)/2;
        y = Game.HEIGHT-height;
        shape = new Rectangle2D.Double(x,y,width,height);
    }

    public void move(int x, int y) {
        this.x += x*5;
        this.y += y*5;
        shape = new Rectangle2D.Double(this.x,this.y,width,height);
    }

    public void moveDown() {
        y+=5;
        shape = new Rectangle2D.Double(x,y,width,height);
    }

    public void moveLeft() {
        x-=5;
        shape = new Rectangle2D.Double(x,y,width,height);
    }

    public void moveRight() {
        x+=5;
        shape = new Rectangle2D.Double(x,y,width,height);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.CYAN);
        graphics2D.fill(shape);
    }
}
