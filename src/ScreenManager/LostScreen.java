package ScreenManager;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


/**
 * Created by Penagwin on 5/13/2014.
 */
public class LostScreen extends Screen {
    public LostScreen() throws SlickException {
        super("lost");
    }
    public void render(GameContainer gc , Graphics g) {
        g.setColor(new Color(255, 0, 0));
        g.drawString("YOU LOST", 300, 300);
    }


}
