package se.liu.ida.gusan092.tddd78.project.game.hud;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.util.List;
import java.awt.Font;

public class Hud
{
    public static final int HUD_HEIGHT = 100;
    public static final int HUD_Y = Game.HEIGHT - HUD_HEIGHT;
    public static final int HEALTH_HEIGHT = 25;
    public static final int HEALTH_Y = Game.HEIGHT - HEALTH_HEIGHT - 20;
    public static final int RGB = 255;
    public static final int BETWEEN_MARGIN = 10;
    public static final int DOWN_MARGIN = 15;

    private int healthWidth;
    private int healthMargin;

    private Player[] players;
    private GradientPaint gradientPaint =
	    new GradientPaint(0, Game.HEIGHT - HUD_HEIGHT, new Color(0, 0, 0, 0), 0, HEALTH_Y, new Color(0, 0, 0));

    public Hud(final Player... players) {
	this.players = players;
	healthWidth = Game.WIDTH / (2 * players.length);
	healthMargin = (Game.WIDTH - healthWidth * players.length) / (players.length + 1);
    }

    public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setPaint(gradientPaint);
	g2d.fillRect(0, HUD_Y, Game.WIDTH, HUD_HEIGHT);

	int healthX = healthMargin;
	for (Player player : players) {
	    float healthPerPixel = (float) healthWidth / player.getMaxHealth();
	    int health = Math.round(player.getHealth() * healthPerPixel);
	    Color color = player.getColor();
	    Color negative = new Color(RGB - color.getRed(), RGB - color.getGreen(), RGB - color.getBlue());
	    g2d.setColor(negative);
	    g2d.fillRect(healthX, HEALTH_Y, healthWidth, HEALTH_HEIGHT);
	    g2d.setColor(color);
	    g2d.fillRect(healthX, HEALTH_Y, health, HEALTH_HEIGHT);


	    if (players.length == 1) {
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score: " + player.getScore(), healthX + healthWidth + BETWEEN_MARGIN, HEALTH_Y + DOWN_MARGIN);

		List<PowerUp> powerUps = player.getPowerUps();
		int powerY = HEALTH_Y + DOWN_MARGIN;
		for (int i = 0; i < powerUps.size(); i++) {
		    PowerUp powerUp = powerUps.get(i);
		    g2d.drawString(powerUp.description(), BETWEEN_MARGIN, powerY);
		    powerY += DOWN_MARGIN;
		}
	    }
	    healthX += healthMargin + healthWidth;
	}
    }
}
