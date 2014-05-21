package ScreenManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created by Penagwin on 5/13/2014.
 */
public class WinnerScreen extends Screen {
    public WinnerScreen() throws SlickException {
        super("winner");
    }
    public void render(GameContainer gc, Graphics g) {
        g.setColor(new Color(0, 255, 0));
        g.drawString("YOU WON", 300, 300);
    }


}
