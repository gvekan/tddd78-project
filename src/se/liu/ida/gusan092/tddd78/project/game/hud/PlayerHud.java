package se.liu.ida.gusan092.tddd78.project.game.hud;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;

import java.awt.*;

public class PlayerHud implements Hud
{
    private Player player;
    private Game game;

    public PlayerHud(final Player player, final Game game) {
	this.player = player;
	this.game = game;
    }

    @Override public void maxTick() {

    }

    @Override public void render(final Graphics g) {
        int healthPerPixel = player.getMaxHealth()/200;
	g.setColor(Color.RED);
	g.fillRect((Game.WIDTH-200)/2,Game.HEIGHT-100, player.getMaxHealth(), 25);
	g.setColor(Color.GREEN);
	g.fillRect((Game.WIDTH-200)/2,Game.HEIGHT-100, healthPerPixel*player.getHealth(),25);

	g.setColor(Color.WHITE);
	g.drawString("Score: " + player.getScore(),(Game.WIDTH+240)/2,Game.HEIGHT-80);
    }
}
