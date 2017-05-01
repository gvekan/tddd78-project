package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;

public abstract class StillObject extends GameObject
{
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    private boolean halfTick = true;

    protected StillObject(final int x, final int y, final int width, final int height, final Color color, final Type type,
			  final Handler handler)
    {
	super(x, y, width, height, color, type, handler);
	setVelY(1);
    }

    protected StillObject(final int width, final int height, final Color color, final Type type, final Handler handler, final String saveValues)
        {
    	super(width, height, color, type, handler, saveValues);
    	setVelY(1);
        }

    @Override public void tick() {
        if (y == Game.HEIGHT) {
            handler.removeAfterTick(this);
            return;
	}
        if (halfTick) {
	    y += velY;
	}
	halfTick = !halfTick;
    }

    @Override public void setSaveValues(final String[] saveValues) {
	if (Objects.equals(saveValues[0], TRUE)) halfTick = true;
	else halfTick = false;
	setStillSaveValues(Arrays.copyOfRange(saveValues, 1, saveValues.length));
    }

    @Override public String getSaveValues() {
	String sHalfTick;
 	if (halfTick) sHalfTick = TRUE;
 	else sHalfTick = FALSE;
 	return super.getSaveValues() + SavedProperties.VALUE_SPLIT + sHalfTick;
    }

    public abstract void setStillSaveValues(final String[] saveValues);
}
