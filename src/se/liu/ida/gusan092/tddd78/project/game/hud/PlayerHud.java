package se.liu.ida.gusan092.tddd78.project.game.hud;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;

import javax.swing.colorchooser.ColorChooserComponentFactory;
import java.awt.*;

public class PlayerHud implements Hud
{
    public static final int HEIGHT = 50;
    public static final int HEALTH_WIDTH = 200;
    public static final int HEALTH_HEIGHT = 25;
    private Player player;

    public PlayerHud(final Player player) {
	this.player = player;
    }

    @Override public void render(final Graphics g) {
        int healthPerPixel = player.getMaxHealth()/HEALTH_WIDTH;
        g.setColor(Color.GRAY);
        g.fill3DRect(0,Game.HEIGHT,Game.WIDTH,HEIGHT,true);
	g.setColor(Color.RED);
	g.fillRect((Game.WIDTH - HEALTH_WIDTH) / 2, Game.HEIGHT+(HEIGHT-HEALTH_HEIGHT)/2, healthPerPixel*player.getMaxHealth(), HEALTH_HEIGHT);
	g.setColor(Color.GREEN);
	g.fillRect((Game.WIDTH-HEALTH_WIDTH)/2,Game.HEIGHT+(HEIGHT-HEALTH_HEIGHT)/2, healthPerPixel*player.getHealth(),HEALTH_HEIGHT);

	g.setColor(Color.WHITE);
	g.drawString("Score: " + player.getScore(),(Game.WIDTH+240)/2,Game.HEIGHT+(HEIGHT/2));
    }

    @Override public int getHeight() {
	return HEIGHT;
    }
}
