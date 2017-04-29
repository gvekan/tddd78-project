package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

public class Ammo extends PowerUp
{
    private Handler handler;
    private int ammo = 10;

    public Ammo(final Player player, final Handler handler) {
	super(PowerUpId.AMMO, player);
	this.handler = handler;
	add();
    }

    @Override public void use() {
	handler.add(new Bullet(player.getX() + player.getWidth() / 2, player.getY() - 4, handler, player));
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

    @Override public String getSaveValues() {
	return super.getSaveValues() + SavedProperties.ENUM_SPLIT + Integer.toString(ammo);
    }
}