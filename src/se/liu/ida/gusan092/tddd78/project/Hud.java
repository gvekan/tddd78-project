package se.liu.ida.gusan092.tddd78.project;

import java.awt.Graphics;
import java.awt.Color;

public class Hud
{
    public static final int WIDTH = 200;
    public static final int HEALTH = 50;
    private int health = HEALTH;
    private int score = 0;

    public void tick() {
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
	g.fillRect((Game.WIDTH-WIDTH)/2,Game.HEIGHT-100, WIDTH, 25);
	g.setColor(Color.GREEN);
	int width = Math.round(health / HEALTH) * WIDTH;
	g.fillRect((Game.WIDTH-WIDTH)/2,Game.HEIGHT-100,width,25);

	g.setColor(Color.WHITE);
 	g.drawString("Score: " + score,(Game.WIDTH+WIDTH+40)/2,Game.HEIGHT-80);
    }

    public void addHealth(int health) {
	this.health = Game.clamp(this.health - health, 0, HEALTH);
    }

    public void addScore(int score) {
	this.score += score;
    }
}
