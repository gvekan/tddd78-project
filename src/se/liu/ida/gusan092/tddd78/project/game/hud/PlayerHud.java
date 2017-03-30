package se.liu.ida.gusan092.tddd78.project.game.hud;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup.PowerUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.util.List;

public class PlayerHud implements Hud
{
    public static final int HEIGHT = 50;
    public static final int HEALTH_WIDTH = 200;
    public static final int HEALTH_HEIGHT = 25;
    private Player player;
    private GradientPaint gradientPaint = new GradientPaint(0,Game.HEIGHT-50, new Color(0,0,0, 0),0,Game.HEIGHT+HEIGHT-50, new Color(0,0,0));

    public PlayerHud(final Player player) {
	this.player = player;
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

        int healthPerPixel = player.getMaxHealth()/HEALTH_WIDTH;
        Color color = player.getColor();
        Color negative = new Color(255-color.getRed(),255-color.getGreen(),255-color.getBlue());
/*        g2d.setColor(Color.GRAY);
        g2d.fill3DRect(0,Game.HEIGHT,Game.WIDTH,HEIGHT,true);*/
	g2d.setPaint(gradientPaint);
	g2d.fillRect(0,Game.HEIGHT-50,Game.WIDTH,HEIGHT+50);
	g2d.setColor(negative);
	g2d.fillRect((Game.WIDTH - HEALTH_WIDTH) / 2, Game.HEIGHT+(HEIGHT-HEALTH_HEIGHT)/2, healthPerPixel*player.getMaxHealth(), HEALTH_HEIGHT);
	g2d.setColor(color);
	g2d.fillRect((Game.WIDTH-HEALTH_WIDTH)/2,Game.HEIGHT+(HEIGHT-HEALTH_HEIGHT)/2, healthPerPixel*player.getHealth(),HEALTH_HEIGHT);

	g2d.setColor(Color.WHITE);
	g2d.drawString("Score: " + player.getScore(),(Game.WIDTH+240)/2,Game.HEIGHT+(HEIGHT/2));

	List<PowerUp> powerUps = player.getPowerUps();
	int powerHeight = Game.HEIGHT + (HEIGHT / 2);
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    g2d.drawString(powerUp.decription(), 10, powerHeight);
	    powerHeight += 10;
	}

    }

    @Override public int getHeight() {
	return HEIGHT;
    }
}
