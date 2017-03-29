package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

public class ControlledCollision implements CollisionHandlerControlled
{

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {
	switch (side) {
	    case FRONT:
	        collisionFront(game, handler, controlledObject, collision);
	        break;
	    case BACK:
	        collisionBack(game, handler, controlledObject, collision);
	        break;
	    case LEFT:
	        collisionLeft(game, handler, controlledObject, collision);
	        break;
	    case RIGHT:
	        collisionRight(game, handler, controlledObject, collision);
	        break;
	}
    }

    @Override public void collisionActivated(final Game game, final Handler handler, final ControlledObject controlledObject,
					     final GameObject collision, final Side side)
    {

    }


    public void collisionFront(Game game, Handler handler, ControlledObject controlledObject, GameObject collision) {
        Side side = Side.FRONT;
	Type collisionId = collision.getType();
	int velY = controlledObject.getVelY();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	switch (collisionId) {
	    case TRAFFIC_BARRIER:
		if (velY == 0) {
		    healthChange = -1;
		} else if (velY < 0) {
		    healthChange = -speedIncreaseDiff / 4;
		    if (healthChange == 0) {
			healthChange = -1;
		    }
		}
		speedChange = healthChange * 2;
		controlledObject.setVelY(0);
		controlledObject.setY(Game.clamp(controlledObject.getY() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case ROADBLOCK:
		if (velY <= 0) {
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
	    	collision.collision(controlledObject, side);
		controlledObject.setY(Game.clamp(controlledObject.getY() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case POWERUP:
	        collision.collision(controlledObject, side);
	        break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
    }

    public void collisionBack(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	Side side = Side.BACK;
	Type collisionId = collision.getType();
	int velY = controlledObject.getVelY();
	switch (collisionId) {
	    case TRAFFIC_BARRIER: //Backcollision can only happen from the side
	    case ROADBLOCK: //Backcollision can only happen from the side
		int velX = controlledObject.getVelX();
		if (velX < 0) {
		    collisionLeft(game, handler, controlledObject, collision);
		} else if (velX > 0) {
		    collisionRight(game, handler, controlledObject, collision);
		}
		break;
	    case POWERUP:
	        collision.collision(controlledObject, side);
	        break;
	}
    }

    public void collisionLeft(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	Side side = Side.LEFT;
	Type collisionId = collision.getType();
	int velX = controlledObject.getVelX();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	switch (collisionId) {
	    case TRAFFIC_BARRIER:
		if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		healthChange = -1;
		speedChange = -1;
		controlledObject.setX(Game.clamp(controlledObject.getX() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case ROADBLOCK:
		if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		healthChange = -speedIncreaseDiff / 8;
		if (healthChange == 0) {
		    healthChange = -1;
		}
		speedChange = healthChange;
		controlledObject.setVelX(0);
		collision.collision(controlledObject, side);
		controlledObject.setX(Game.clamp(controlledObject.getX() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case POWERUP:
	        collision.collision(controlledObject, side);
	        break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);

    }

    public void collisionRight(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	Side side = Side.RIGHT;
	Type collisionId = collision.getType();
	int velX = controlledObject.getVelX();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	switch (collisionId) {
	    case TRAFFIC_BARRIER:
		if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		healthChange = -1;
		speedChange = -1;
		controlledObject.setX(Game.clamp(controlledObject.getX() - 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case ROADBLOCK:
		if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, controlledObject, collision);
		    break;
		}
		handler.remove(collision);
		healthChange = -speedIncreaseDiff / 8;
		if (healthChange == 0) {
		    healthChange = -1;
		}
		speedChange = healthChange;
		controlledObject.setVelX(0);
		collision.collision(controlledObject, side);
		controlledObject.setX(Game.clamp(controlledObject.getX() - 1, 0, Game.HEIGHT - controlledObject.getHeight()));
		break;
	    case POWERUP:
	        collision.collision(controlledObject, side);
	        break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);

    }
}