package ScreenManager;

import ScreenManager.Elements.Image;
import org.json.JSONException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.ArrayList;

import static Util.Log.*;

/**
 * Created by penagwin on 4/26/14.
 */
public class Screen {
    public String name;
    public ArrayList<Image> collidables = new ArrayList<>();
    public ArrayList<Rectangle> collidablesr = new ArrayList<>();


    Screen(String nname) throws SlickException {
        name = nname;
        init();
    }
    public void Keypress() throws JSONException {

    }
    public void render(GameContainer gc, Graphics g) {
        println("Some screen doesn't have a render class!");
    }

    void init() throws SlickException {
        println("Some screen doesn't have an init!");
    }
    public void update(){}

    public void MousePress(int i) throws SlickException {
    }

    Boolean ImageCollide(Image im) {
        float mmousey = Display.getHeight() - Mouse.getY();
        return (Mouse.getX() > im.x && Mouse.getX() < im.x + im.getWidth() && (mmousey > im.y && mmousey < im.y + im.getHeight()));
    }
}
