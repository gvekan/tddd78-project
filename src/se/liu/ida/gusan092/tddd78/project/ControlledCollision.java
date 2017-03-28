package se.liu.ida.gusan092.tddd78.project;

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

    @Override public void collisionControlled(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {

    }


    public void collisionFront(Game game, Handler handler, ControlledObject controlledObject, GameObject collision) {
	ObjectType collisionId = collision.getObjectType();
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
		break;
	    case ROADBLOCK:
		handler.remove(collision);
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
	    	collision.collision(controlledObject, Side.FRONT);
		break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
	controlledObject.setY(Game.clamp(controlledObject.getY() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
    }

    public void collisionBack(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	ObjectType collisionId = collision.getObjectType();
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
	}
    }

    public void collisionLeft(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	ObjectType collisionId = collision.getObjectType();
	if (collisionId != ObjectType.ENVIROMENT) {
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
		    collision.collision(controlledObject, Side.LEFT);
		    break;
	    }
	    controlledObject.addHealth(healthChange);
	    game.setAmountOfTicks(amountOfTicks + speedChange);
	    controlledObject.setX(Game.clamp(controlledObject.getX() + 1, 0, Game.HEIGHT - controlledObject.getHeight()));
	}
    }

    public void collisionRight(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision) {
	ObjectType collisionId = collision.getObjectType();
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
		collision.collision(controlledObject, Side.RIGHT);
		break;
	}
	controlledObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
	controlledObject.setX(Game.clamp(controlledObject.getX() - 1, 0, Game.HEIGHT - controlledObject.getHeight()));

    }
}
