package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window
{
    private JFrame frame = new JFrame("Game");

    public Window() {
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	frame.setResizable(false);
 	frame.pack();
 	frame.setVisible(true);
 	frame.setFocusable(true);

    }

    public Window(final Game game) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        game.start();
    }
}
