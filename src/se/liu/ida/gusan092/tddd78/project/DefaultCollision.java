package se.liu.ida.gusan092.tddd78.project;

public class DefaultCollision implements CollisionHandler
{
    @Override public void collisionFront(Game game, Handler handler, GameObject gameObject, GameObject collision) {
	Identity collisionId = collision.getIdentity();
	int velY = gameObject.getVelY();
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
		gameObject.setVelY(0);
		break;
	    case ROADBLOCK:
		handler.removeGameObjects(collision);
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
		    gameObject.setVelY(0);
		} else {
		    gameObject.setVelY(1);
		}
		break;
	}
	gameObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
	gameObject.setY(Game.clamp(gameObject.getY() + 1, 0, Game.HEIGHT - gameObject.getHeight()));
    }

    @Override
    public void collisionBack(final Game game, final Handler handler, final GameObject gameObject, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	int velY = gameObject.getVelY();
	switch (collisionId) {
	    case TRAFFIC_BARRIER: //Backcollision can only happen from the side
	    case ROADBLOCK: //Backcollision can only happen from the side
		int velX = gameObject.getVelX();
		if (velX < 0) {
		    collisionLeft(game, handler, gameObject, collision);
		} else if (velX > 0) {
		    collisionRight(game, handler, gameObject, collision);
		}
		break;
	}
    }

    @Override
    public void collisionLeft(final Game game, final Handler handler, final GameObject gameObject, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	if (collisionId != Identity.ENVIROMENT) {
	    int velX = gameObject.getVelX();
	    int healthChange = 0;
	    int speedChange = 0;
	    int amountOfTicks = (int) game.getAmountOfTicks();
	    int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	    switch (collisionId) {
		case TRAFFIC_BARRIER:
		    if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handler, gameObject, collision);
			break;
		    }
		    healthChange = -1;
		    speedChange = -1;
		    break;
		case ROADBLOCK:
		    if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handler, gameObject, collision);
			break;
		    }
		    handler.removeGameObjects(collision);
		    healthChange = -speedIncreaseDiff / 8;
		    if (healthChange == 0) {
			healthChange = -1;
		    }
		    speedChange = healthChange;
		    gameObject.setVelX(0);
		    break;
	    }
	    gameObject.addHealth(healthChange);
	    game.setAmountOfTicks(amountOfTicks + speedChange);
	    gameObject.setX(Game.clamp(gameObject.getX() + 1, 0, Game.HEIGHT - gameObject.getHeight()));
	}
    }

    @Override
    public void collisionRight(final Game game, final Handler handler, final GameObject gameObject, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	int velX = gameObject.getVelX();
	int healthChange = 0;
	int speedChange = 0;
	int amountOfTicks = (int) game.getAmountOfTicks();
	int speedIncreaseDiff = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	switch (collisionId) {
	    case TRAFFIC_BARRIER:
		if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, gameObject, collision);
		    break;
		}
		healthChange = -1;
		speedChange = -1;
		break;
	    case ROADBLOCK:
		if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
		    collisionFront(game, handler, gameObject, collision);
		    break;
		}
		handler.removeGameObjects(collision);
		healthChange = -speedIncreaseDiff / 8;
		if (healthChange == 0) {
		    healthChange = -1;
		}
		speedChange = healthChange;
		gameObject.setVelX(0);
		break;
	}
	gameObject.addHealth(healthChange);
	game.setAmountOfTicks(amountOfTicks + speedChange);
	gameObject.setX(Game.clamp(gameObject.getX() - 1, 0, Game.HEIGHT - gameObject.getHeight()));

    }
}
