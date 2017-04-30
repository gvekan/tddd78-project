package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Road extends StillObject
{
    BufferedImage img = null;
    public Road(final int y, final Handler handler)
    {
	super(0, y, Game.WIDTH, Game.HEIGHT, Color.BLACK, Type.ROAD, handler);
	try {
	    img = ImageIO.read(new File("road.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public Road(final Handler handler, final String saveValues)
    {
	super(Color.BLACK, Type.ROAD, handler, saveValues);
	try {
	    img = ImageIO.read(new File("road.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override public void render(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (img != null) {
		    g2d.drawImage(img,x,y,null);
		}
    }
}
