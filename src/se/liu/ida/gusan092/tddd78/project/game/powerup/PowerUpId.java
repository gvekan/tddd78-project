package se.liu.ida.gusan092.tddd78.project.game.powerup;

import java.awt.Color;

/**
 * Representing each power up and the incompability between them
 */
public enum PowerUpId
{
    /**
     * Representing Ammo
     */
    AMMO(Color.MAGENTA, 1, 2),
    /**
     * Representing Ghost
     */
    GHOST(Color.PINK, 0, 2),
    /**
     * Representing Unstoppable
     */
    UNSTOPPABLE(Color.YELLOW, 1, 0);

    private final Color color;
    private final int[] incompatibles;
    private final int index;

    private PowerUpId(final Color color, final int... incompatibles) {
	this.color = color;
	this.incompatibles = incompatibles;
	this.index = ordinal();
    }

    public int getIndex(){
	return index;
    }

    public Color getColor() {
	return color;
    }

    public boolean isIncompatible(final PowerUpId test){
	for (int i:
	     incompatibles) {
	    if (i == test.index) {
	        return true;
	    }
	}
	for (int i:
	     test.incompatibles) {
	    if (i == index) {
	        return true;
	    }
	}
	return false;
    }
}
