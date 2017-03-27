package se.liu.ida.gusan092.tddd78.project;

public class DefaultCollision implements CollisionHandeler
{
    @Override public void collisionFront(Game game, Handeler handeler, GameObject go, GameObject collision) {
	Identity collisionId = collision.getIdentity();
	if (collisionId != Identity.ENVIROMENT) {
	    int velY = go.getVelY();
	    int health = 0;
	    int reduction = 0;
	    int amountOfTicks = (int) game.getAmountOfTicks();
	    int increase = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	    switch (collisionId) {
		case TRAFFIC_BARRIER:
		    if (velY == 0) {
			health = 1;
		    } else if (velY < 0) {
			health = increase / 4;
			if (health == 0) {
			    health = 1;
			}
		    }
		    reduction = health * 2;
		    go.setVelY(0);
		    break;
		case ROADBLOCK:
		    handeler.removeGameObjects(collision);
		    if (velY <= 0) {
			if (velY == 0) {
			    health = increase / 8;
			} else {
			    health = increase / 6;
			}
			if (health == 0) {
			    health = 1;
			}
		    }
		    reduction = health * 2;
		    if (velY < 0) {
			go.setVelY(0);
		    } else {
		        go.setVelY(1);
		    }
		    break;
	    }
	    game.addHealth(-health);
	    game.setAmountOfTicks(amountOfTicks - reduction);
	    go.setY(Game.clamp(go.getY() + 1, 0, Game.HEIGHT - go.getHeight()));
	}
    }

    @Override
    public void collisionBack(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	if (collisionId != Identity.ENVIROMENT) {
	    int velY = go.getVelY();
	    switch (collisionId) {
		case TRAFFIC_BARRIER: //Backcollision can only happen from the side
		case ROADBLOCK: //Backcollision can only happen from the side
		    int velX = go.getVelX();
		    if (velX < 0) {
			collisionLeft(game, handeler, go, collision);
		    } else if (velX > 0) {
			collisionRight(game, handeler, go, collision);
		    }
		    break;
	    }
	}
    }

    @Override
    public void collisionLeft(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	if (collisionId != Identity.ENVIROMENT) {
	    int velX = go.getVelX();
	    int health = 0;
	    int reduction = 0;
	    int amountOfTicks = (int) game.getAmountOfTicks();
	    int increase = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	    switch (collisionId) {
		case TRAFFIC_BARRIER:
		    if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handeler, go, collision);
			break;
		    }
		    health = 1;
		    reduction = 1;
		    break;
		case ROADBLOCK:
		    if (velX >= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handeler, go, collision);
			break;
		    }
		    handeler.removeGameObjects(collision);
		    health = increase / 8;
		    if (health == 0) {
			health = 1;
		    }
		    reduction = health;
		    go.setVelX(0);
		    break;
	    }
	    game.addHealth(-health);
	    game.setAmountOfTicks(amountOfTicks - reduction);
	    go.setX(Game.clamp(go.getX() + 1, 0, Game.HEIGHT - go.getHeight()));
	}
    }

    @Override
    public void collisionRight(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	Identity collisionId = collision.getIdentity();
	if (collisionId != Identity.ENVIROMENT) {
	    int velX = go.getVelX();
	    int health = 0;
	    int reduction = 0;
	    int amountOfTicks = (int) game.getAmountOfTicks();
	    int increase = amountOfTicks - (int) Game.MIN_AMOUNT_OF_TICKS;
	    switch (collisionId) {
		case TRAFFIC_BARRIER:
		    if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handeler, go, collision);
			break;
		    }
		    health = 1;
		    reduction = 1;
		    break;
		case ROADBLOCK:
		    if (velX <= 0) { //Correct defect because side-collision can not happen if velX == 0
			collisionFront(game, handeler, go, collision);
			break;
		    }
		    handeler.removeGameObjects(collision);
		    health = increase / 8;
		    if (health == 0) {
			health = 1;
		    }
		    reduction = health;
		    go.setVelX(0);
		    break;
	    }
	    game.addHealth(-health);
	    game.setAmountOfTicks(amountOfTicks - reduction);
	    go.setX(Game.clamp(go.getX() - 1, 0, Game.HEIGHT - go.getHeight()));
	}
    }
}
