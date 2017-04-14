package se.liu.ida.gusan092.tddd78.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Insets;

public abstract class MenuButton extends JButton
{
    protected JFrame window;
    private Font font;
    private boolean first = true;


    public MenuButton(final JFrame window, final String text) {
        super(text);
        this.window = window;
	this.setBackground(new Color(59, 89, 182));
 	this.setForeground(Color.WHITE);
 	this.setBorderPainted(false);
 	this.setMargin(new Insets(0,0,0,0));
 	this.setFocusPainted(false);

    }
}
