package se.liu.ida.gusan092.tddd78.project.gui;

import se.liu.ida.gusan092.tddd78.project.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window
{
    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        game.start();
    }
}
