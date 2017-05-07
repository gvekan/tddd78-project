package se.liu.ida.gusan092.tddd78.project.game.objects.collision;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

/**
 * The default CollisionHandler to use with single player
 */
public class DefaultCollision implements CollisionHandler
{

    @Override public void collision(final Game game, final Handler handler, final Player player,
				    final GameObject collision, final Side side)
    {
        switch (collision.getType()) {
	    case ANIMAL:
	    case ROADBLOCK: //Both should be removed after a collision
		handler.removeAfterTick(collision);
		break;
	    case CONTAINER: //Side is not important when colliding with Container
	        collision.collisionWithPlayer(player, side);
	        break;
	}
	switch (side) {
	    case FRONT:
	        collisionFront(game, handler, player, collision);
	        break;
	    case BACK:
	        collisionBack(game, handler, player, collision);
	        break;
	    case LEFT:
	        collisionLeft(game, handler, player, collision);
	        break;
	    case RIGHT:
	        collisionRight(game, handler, player, collision);
	        break;
	}
    }

    /**
     * Will decrease Game.amountOfTicks and Player.health depending on side it collided with
     * handler could be used by some GameObject in the future
     */
    private void collisionFront(final Game game, final Handler handler, final Player controlledObject,
				final GameObject collision) {
	Type collisionId = collision.getType();
	int velY = controlledObject.getVelY();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	switch (collisionId) {
	    case ANIMAL:
	    case ROADBLOCK:
		if (velY <= 0) {
		    int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
		    if (velY == 0) {
			healthChange = -speedIncreaseDiff / 8;
		    } else {
			healthChange = -speedIncreaseDiff / 6;
		    }
		    if (healthChange == 0) {
			healthChange = -1;
		    }
		}
		speedChange = healthChange * 2;
		if (velY < 0) {
		    controlledObject.setVelY(0);
		} else {
		    controlledObject.setVelY(1);
		}
		controlledObject.setY(controlledObject.getY() + 1);
		break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
    }

    /**
     * Backcollision with still GameObjects is impossible, it can only have happend from the side, look at Player.tick() and getLeftBounds()
     */
    private void collisionBack(final Game game, final Handler handler, final Player controlledObject,
			       final GameObject collision) {
	Type collisionId = collision.getType();
	switch (collisionId) {
	    case ANIMAL:
	    case ROADBLOCK:
		int velX = controlledObject.getVelX();
		if (velX < 0) {
		    collisionLeft(game, handler, controlledObject, collision);
		} else if (velX > 0) {
		    collisionRight(game, handler, controlledObject, collision);
		}
		break;
	}
    }

    private void collisionLeft(final Game game, final Handler handler, final Player controlledObject,
			       final GameObject collision) {
	Type collisionId = collision.getType();
	int velX = controlledObject.getVelX();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	switch (collisionId) {
	    case ANIMAL:
	    case ROADBLOCK:
		if (velX >= 0) { //Correct defect because side-collisionWithGameObject can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
		healthChange = -speedIncreaseDiff / 8;
		if (healthChange == 0) {
		    healthChange = -1;
		}
		speedChange = healthChange;
		controlledObject.setVelX(0);
		controlledObject.setX(controlledObject.getX() + 1);
		break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);

    }

    private void collisionRight(final Game game, final Handler handler, final Player controlledObject,
				final GameObject collision) {
	Type collisionId = collision.getType();
	int velX = controlledObject.getVelX();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	switch (collisionId) {
	    case ANIMAL:
	    case ROADBLOCK:
		if (velX <= 0) { //Correct defect because side-collisionWithGameObject can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
		healthChange = -speedIncreaseDiff / 8;
		if (healthChange == 0) {
		    healthChange = -1;
		}
		speedChange = healthChange;
		controlledObject.setVelX(0);
		controlledObject.setX(controlledObject.getX() - 1);
		break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);

    }
}
