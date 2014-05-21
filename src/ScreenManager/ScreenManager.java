package ScreenManager;

import org.json.JSONException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by penagwin on 4/26/14.
 */
public class ScreenManager {
    public static ArrayList<Screen> screens = new ArrayList<Screen>();
    private static Screen screen;
    public static String screenname;
    public static Boolean outlines = false;
    public static GameContainer ggc;


    private static Screen getScreen() {
        for (int i = 0; i < screens.toArray().length; i++) {
            String name = screens.get(i).name;
            if (name.equals(screenname)) {
                return screens.get(i);
            }
        }
        return null;
    }

    public static void removeScreen(String s) {
        for (int i = 0; i < screens.toArray().length; i++) {
            String name = screens.get(i).name;
            if (name.equals(s)) {
                screens.remove(screens.get(i));
            }
        }
    }

    public static void setName(String s) {
        screenname = s;
    }

    private static void getScreenName() {
        if (screen == null || screen.name != screenname) {
            screen = getScreen();
        }
    }

    public static void render(GameContainer gc, Graphics g) throws FontFormatException {
        getScreenName();
        screen.render(gc, g);
    }
    public static void update(){
        getScreenName();
        screen.update();
    }

    public static void MousePress(int i) throws SlickException {
        getScreenName();
        screen.MousePress(i);
    }

    public static void KeyPress() throws JSONException {
        getScreenName();
        screen.Keypress();
    }
    public static void restartGame(){
        screenname = "Menu";
        ScreenManager.update();
        removeScreen("Game");
        try {
            screens.add(new GameScreen1());
        } catch (SlickException e) {
            e.printStackTrace();
        }
        screenname = "Game";
        ScreenManager.update();
    }
}
