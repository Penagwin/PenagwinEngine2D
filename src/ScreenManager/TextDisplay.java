package ScreenManager;

import org.apache.commons.lang3.text.WordUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


/**
 * Created by penagwin on 4/28/14.
 */
class TextDisplay {
    public static void render(GameContainer gc, Graphics g, String s) {
        g.setColor(new Color(255, 255, 255));
        g.drawString(WordUtils.wrap(s, 30), 200, 100);
    }
    public static void rendercaptionthing(GameContainer gc, Graphics g, String s) {
        g.setColor(new Color(255, 255, 255));
        g.drawString(WordUtils.wrap(s, 60), 100, 100);
    }
}
