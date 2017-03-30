package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;

import java.awt.Color;

public class Ammo extends PowerUp
{
    private int ammo = 10;

    public Ammo(final Handler handler) {
	super(handler, PowerUpId.AMMO, Color.MAGENTA);
    }

    @Override public void use() {
	handler.add(new Bullet(controlledObject.getX()+controlledObject.getWidth()/2,controlledObject.getY()-4,handler,controlledObject));
	ammo--;
	if (ammo == 0) {
	    reset();
	}
    }

    @Override public void stop() {
    }

    @Override public void interrupt() {
    }

    @Override public void resume() {
    }

    @Override public void collisionHasSamePowerUp() {
	ammo += 10;
    }

    @Override public String description() {
	return "Ammo: " + ammo;
    }
}