package se.liu.ida.gusan092.tddd78.project;

import java.awt.Shape;

public abstract class AbstractGameObject
{
    protected Shape shape;

    protected double x;
    protected double y;

    public AbstractGameObject(final Shape shape, final double x, final double y) {
	this.shape = shape;
	this.x = x;
	this.y = y;
    }
}
