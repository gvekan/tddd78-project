package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;

import java.util.List;
import java.awt.Color;

/**
 * A GameObject created by a player with the PowerUp Ammo, works like a extension of the player at removes when it collids with a GameObject
 */
public class Bullet extends GameObject
{
    /**
     * the size of the bullet
     */
    public static final int SIZE = 3;
    /**
     * Score given for shooting a animal
     */
    public static final int ANIMAL_SCORE = 200;
    private Player player;

    public Bullet(final int x, final int y, final Handler handler, final Player player)
    {
	super(x, y, SIZE, SIZE, Color.BLACK, Type.BULLET, handler);
	this.player = player;
	setVelY(-1);
    }

    /**
     * @param handler the handler it is in
     * @param player the player that created the bullet
     * @param saveValues values to use when restoring a saved file
     */
    public Bullet(final Handler handler, final Player player, final String saveValues)
        {
    	super(SIZE, SIZE, Color.BLACK, Type.BULLET, handler);
    	this.player = player;
    	setSaveValues(saveValues);
        }

    @Override public void tick() {
	y += velY;
	List<GameObject> collisions = handler.getCollisions(this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		collisionWithGameObject(collision, Side.FRONT);
		handler.removeAfterTick(this);
	    }
	}
	if (y == 0) {
	    handler.removeAfterTick(this);
	}
    }

    @Override public void collisionWithGameObject(final GameObject collision, final Side side) {
	switch (collision.getType()) {
	    case ROADBLOCK:
		handler.removeAfterTick(collision);
		player.addScore(100);
		break;
	    case ANIMAL:
		handler.removeAfterTick(collision);
		player.addScore(ANIMAL_SCORE);
		break;
	    case PLAYER:
		collision.collisionWithGameObject(collision, Side.FRONT);
		break;
	    case CONTAINER:
	        collision.collisionWithPlayer(player, Side.FRONT);
	}

    }

    @Override public void setSaveValues(final String[] saveValues) {

    }
}
