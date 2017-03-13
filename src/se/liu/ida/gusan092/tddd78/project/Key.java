package se.liu.ida.gusan092.tddd78.project;

import java.awt.event.KeyEvent;

public enum Key
{
       UP("Up", KeyEvent.VK_UP), DOWN("Down", KeyEvent.VK_DOWN),
	LEFT("W", KeyEvent.VK_LEFT), RIGHT("S", KeyEvent.VK_RIGHT), ;

       private final String text;
       private final int keyCode;

       private Key(String text, int keyCode) {
	  this.text = text;
	  this.keyCode = keyCode;
       }

       public String getText() {
	  return text;
       }

       public int getKeyCode() {
	  return keyCode;
       }

}
