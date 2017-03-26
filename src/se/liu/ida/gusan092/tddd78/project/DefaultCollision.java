package se.liu.ida.gusan092.tddd78.project;

public class DefaultCollision implements CollisionHandeler
{
    @Override public void collisionFront(Game game, Handeler handeler ,GameObject go, GameObject collision) {
	int velY = go.getVelY();
	switch (collision.getId()) {
	    case ROADBLOCK:
	        handeler.removeGameObjects(collision);
		int crash = (int) Math.round((game.getAmountOfTicks() - Game.MIN_AMOUNT_OF_TICKS) / 2);
		if (velY == 1) {
		    crash = Math.round(crash/2);
		} else if (velY == -1) {
		    crash *= 2;
		}
		game.setAmountOfTicks(Game.MIN_AMOUNT_OF_TICKS + crash);
		game.addHealth(-crash);
		go.setVelY(0);
		go.setVelX(0);
		go.setY(Game.clamp(go.getY() + 1, 0, Game.HEIGHT - go.getHeight()));
		break;
	}
    }

    @Override
    public void collisionBack(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	int velY = go.getVelY();
	go.setY(Game.clamp(go.getY() - 1, 0, Game.HEIGHT - go.getHeight()));
    }

    @Override
    public void collisionLeft(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	int velX = go.getVelX();
	go.setX(Game.clamp(go.getX() + 1, 0, Game.HEIGHT - go.getHeight()));

    }

    @Override
    public void collisionRight(final Game game, final Handeler handeler, final GameObject go, final GameObject collision) {
	int velX = go.getVelX();
	go.setX(Game.clamp(go.getX() - 1, 0, Game.HEIGHT - go.getHeight()));

    }
}
