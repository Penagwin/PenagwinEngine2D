package Collisions;

import Player.Player1;
import ScreenManager.Elements.Image;
import ScreenManager.Screen;

import org.lwjgl.opengl.Display;

import java.awt.*;

/**
 * Created by penagwin on 4/26/14.
 */
public class CollisionManager {

    public static Boolean isCollided(Image im0, Image im1) {
        Rectangle rect0 = new Rectangle((int) im0.x, (int) im0.y, (int)( im0.getWidth()*1.5), (int)( im0.getHeight()*1.5));
        Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) im1.getHeight());

        return rect0.intersects(rect1);
    }
    public static Boolean isCollided(Image im0, Rectangle im1) {
        Rectangle rect0 = new Rectangle((int) im0.x, (int) im0.y, (int)( im0.getWidth()*1.5), (int)( im0.getHeight()*1.5));
        Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) Display.getHeight());

        return rect0.intersects(rect1);
    }
    public static int wouldCollideAnythingX(Image im0, int moveamount, Screen screen) {
        for(Rectangle rect : screen.collidablesr){
            int tmp = wouldCollided(Player1.image, moveamount, rect);
            if( tmp < moveamount);
            moveamount = tmp;
        }
        return moveamount;
    }
    public static int wouldCollideAnythingY(Image im0, int moveamount, Screen screen) {
        Rectangle rect0 = new Rectangle((int) im0.x, (int) im0.y + moveamount, (int) (im0.getWidth() * 1.5), (int) (im0.getHeight() * 1.5));
        for (Image im1 : screen.collidables) {
            Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) im1.getHeight());
            if (rect0.intersects(rect1)) {
                if (moveamount > 0)
                    return moveamount - (int) rect0.createIntersection(rect1).getHeight();
                else
                    return moveamount + (int) rect0.createIntersection(rect1).getHeight();
            }

        }
        for (Rectangle im1 : screen.collidablesr) {
            Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) im1.getHeight());
            if (rect0.intersects(rect1)) {
                if (moveamount > 0)
                    return moveamount - (int) rect0.createIntersection(rect1).getHeight();
                else
                    return moveamount + (int) rect0.createIntersection(rect1).getHeight();
            }

        }

        return moveamount;
    }
    

    public static int wouldCollided(Image im0, int moveamount, Image im1) {
        Rectangle rect0 = new Rectangle((int) im0.x + moveamount, (int) im0.y, (int) (im0.getWidth() * 1.5), (int) (im0.getHeight() * 1.5));
        Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) im1.getHeight());
        if (rect0.intersects(rect1))
            if (moveamount > 0)
                return moveamount - (int) rect0.createIntersection(rect1).getWidth();
            else
                return moveamount + (int) rect0.createIntersection(rect1).getWidth();
        return moveamount;
    }
    public static int wouldCollided(Image im0, int moveamount, Rectangle im1) {
        Rectangle rect0 = new Rectangle((int) im0.x + moveamount, (int) im0.y, (int) (im0.getWidth() * 1.5), (int) (im0.getHeight() * 1.5));
        Rectangle rect1 = new Rectangle((int) im1.x, (int) im1.y, (int) im1.getWidth(), (int) im1.getHeight());
        if (rect0.intersects(rect1))
            if (moveamount > 0)
                return moveamount - (int) rect0.createIntersection(rect1).getWidth();
            else
                return moveamount + (int) rect0.createIntersection(rect1).getWidth();
        return moveamount;
    }

    public static int mapCollided(Image im0, int moveamount) {
        Rectangle rect0 = new Rectangle((int) im0.x + moveamount, (int) im0.y, (int) (im0.getWidth() * 1.5), (int) (im0.getHeight() * 1.5));
        Rectangle leftwall = new Rectangle(-20, 50, 18, Display.getHeight());
        Rectangle rightwall = new Rectangle(Display.getWidth()-1 , 0, 50, Display.getHeight());

        if (rect0.intersects(leftwall)) {
            if (moveamount > 0)
                return moveamount - (int) rect0.createIntersection(leftwall).getWidth();
            else
                return moveamount + (int) rect0.createIntersection(leftwall).getWidth();
        }
        else if (rect0.intersects(rightwall)) {
            if (moveamount > 0)
                return moveamount - (int) rect0.createIntersection(rightwall).getWidth();
            else
                return moveamount + (int) rect0.createIntersection(rightwall).getWidth();
        }
        else
            return moveamount;
    }
}
