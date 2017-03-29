package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

import java.awt.*;

public class Ammo extends PowerUp
{
    private int ammo = 10;

    public Ammo(final int x, final Handler handler) {
	super(x, Color.MAGENTA, handler);
    }

    @Override public void use() {
	handler.add(new Bullet(controlledObject.getX()+getWidth()/2,controlledObject.getY()-4,handler,controlledObject));
	ammo--;
	if (ammo == 0) {
	    reset();
	}
    }
}
