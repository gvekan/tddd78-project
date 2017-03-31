package se.liu.ida.gusan092.tddd78.project.game.powerup;

import java.awt.Color;

public enum PowerUpId
{
    AMMO(Color.MAGENTA, 0, 1),
    GHOST(Color.PINK, 1, 0, 2),
    UNSTOPPABLE(Color.YELLOW, 2, 1);

    private final Color color;
    private final int id;
    private final int[] incompatibles;

    private PowerUpId(final Color color, final int id, final int... incompatibles) {
        this.color = color;
        this.id = id;
	this.incompatibles = incompatibles;
    }

    public Color getColor() {
	return color;
    }

    public boolean isIncompatible(final PowerUpId test){
	for (int i:
	     incompatibles) {
	    if (i == test.id) {
	        return true;
	    }
	}
	return false;
    }
}
