package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Color;
import java.util.List;

public class Ammo extends PowerUp
{
    private int ammo = 10;

    public Ammo(final int x, final Handler handler) {
	super(x, Color.MAGENTA, handler, PowerUpId.AMMO);
    }

    @Override public void use() {
	handler.add(new Bullet(controlledObject.getX()+getWidth()/2,controlledObject.getY()-4,handler,controlledObject));
	ammo--;
	if (ammo == 0) {
	    reset();
	}
    }

    @Override public void stop() {

    }

    @Override public void collisionHasSamePowerUp() {
        super.collisionHasSamePowerUp();
	ammo += 10;
    }

    @Override public String decription() {
	return "Ammo: " + ammo;
    }

    @Override public void collisionWithControlled(final ControlledObject collision, final Side side) {
	super.collisionWithControlled(collision, side);
	List<PowerUp> powerUps = collision.getPowerUps();
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.getId() == id) {
		powerUp.collisionHasSamePowerUp();
		return;
	    }
	}
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.getId() == PowerUpId.GHOST) {
		powerUps.remove(powerUp);
		powerUp.stop();
		interruptedPowerUp = powerUp;
		break;
	    }
	}
	activate(collision);
    }




}
