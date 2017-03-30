package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PowerUpId
{
    AMMO(0, 1),
    GHOST(1, 0,2),
    UNSTOPPABLE(2, 1);

    private final int id;
    private final int[] incompatibles;

    private PowerUpId(final int id, final int... incompatibles) {
        this.id = id;
	this.incompatibles = incompatibles;
    }
    public boolean isIncompatible(PowerUpId test){
	for (int i:
	     incompatibles) {
	    if (i == test.id) {
	        return true;
	    }
	}
	return false;
    }
}
