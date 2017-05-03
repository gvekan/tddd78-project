package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUpId;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;



/**
 * A still object that carries a PowerUp and will give that id to the player it collides with
 */
public class Container extends StillObject
{
    /**
     * The size of the container
     */
    public static final int SIZE = 20;

    /**
     * The PowerUpId it carries
     * Ignore error: Can not be initalized because it is set by setStillSaveValues
     */
    private PowerUpId id;

    public Container(final int x, final Handler handler, final PowerUpId id)
    {
	super(x, -SIZE, SIZE, SIZE, id.getColor(), Type.CONTAINER, handler);
	this.id = id;
    }

    /**
     * Used to restore a saved game
     * Note that the id and color will be set by setStillSaveValues
     */
    public Container(final Handler handler, final String saveValues)
    {
	super(SIZE, SIZE, null, Type.CONTAINER, handler);
	id = null;
	restoreSaveValues(saveValues);
    }

    /**
     * @return the PowerUpIds index
     */
    @Override public String getSaveValues() {
	return super.getSaveValues() + SavedProperties.VALUE_SPLIT + Integer.toString(id.getIndex());
    }

    /**
     * @param saveValues a list with the PowerUpIds index
     * sets the id and color
     */
    @Override public void setStillSaveValues(final String[] saveValues) {
	id = PowerUpId.values()[Integer.parseInt(saveValues[0])];
	System.out.println(id);
	color = id.getColor();
    }

    /**
     * @param collision the player it collided with
     * @param side ignored
     * creates a new powerup for the player
     */
    @Override public void collisionWithPlayer(final Player collision, final Side side) {
	handler.removeAfterTick(this);
	collision.newPowerUp(id);
    }
}
