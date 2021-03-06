package se.liu.ida.gusan092.tddd78.project.game;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.List;

/**
 * Displaying information about players
 */
public class Hud
{
    /**
     * The height of the hud
     */
    public static final int HUD_HEIGHT = 100;
    /**
     * The Y position of the hud
     */
    public static final int HUD_Y = Game.HEIGHT - HUD_HEIGHT;
    /**
     * The height of the healthbar
     */
    public static final int HEALTH_HEIGHT = 25;
    /**
     * The Y position of the healthbar
     */
    public static final int HEALTH_Y = Game.HEIGHT - HEALTH_HEIGHT - 20;
    /**
     * The maxvalue for RGB
     */
    public static final int RGB = 255;
    /**
     * The horizontal margin between objects drawn in render
     */
    public static final int BETWEEN_MARGIN = 10;
    /**
     * The margin under objects drawn in render
     */
    public static final int DOWN_MARGIN = 15;

    private int healthWidth;
    private int healthMargin;


    private List<Player> players = new ArrayList<>();
    private GradientPaint gradientPaint =
	    new GradientPaint(0, Game.HEIGHT - HUD_HEIGHT, new Color(0, 0, 0, 0), 0, HEALTH_Y, new Color(0, 0, 0));

    public Hud(final List<Player> players) {
	this.players = players;
	healthWidth = Game.WIDTH / (2 * players.size());
	healthMargin = (Game.WIDTH - healthWidth * players.size()) / (players.size() + 1);
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


	    if (players.size() == 1) {
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score: " + player.getScore(), healthX + healthWidth + BETWEEN_MARGIN, HEALTH_Y + DOWN_MARGIN);

		int powerY = HEALTH_Y + DOWN_MARGIN;
		for (int i = 0; i < player.getPowerUpsSize(); i++) {
		    g2d.drawString(player.getPowerUpDescription(i), BETWEEN_MARGIN, powerY);
		    powerY += DOWN_MARGIN;
		}
	    }
	    healthX += healthMargin + healthWidth;
	}
    }
}
