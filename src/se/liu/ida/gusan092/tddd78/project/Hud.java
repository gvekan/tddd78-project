package se.liu.ida.gusan092.tddd78.project;

import java.awt.Graphics;
import java.awt.Color;

public class Hud
{
    public static final int HEALTH = 200;
    private int health = HEALTH;
    private int score = 0;

    public void tick() {
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
	g.fillRect((Game.WIDTH-HEALTH)/2,Game.HEIGHT-100, HEALTH, 25);
	g.setColor(Color.GREEN);
	g.fillRect((Game.WIDTH-HEALTH)/2,Game.HEIGHT-100, health,25);

	g.setColor(Color.WHITE);
 	g.drawString("Score: " + score,(Game.WIDTH+HEALTH+40)/2,Game.HEIGHT-80);
    }

    public void addHealth(int health) {
	this.health = Game.clamp(this.health + health, 0, HEALTH);
    }

    public void addScore(int score) {
	this.score += score;
    }
}
