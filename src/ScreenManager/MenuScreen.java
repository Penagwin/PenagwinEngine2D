package ScreenManager;

import ScreenManager.Elements.Image;
import Util.Log;
import Util.SocketHandle;
import Util.TextField;

import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;

import java.awt.Font;

import static ScreenManager.ScreenManager.*;

/**
 * Created by penagwin on 4/26/14.
 */
public class MenuScreen extends Screen {
    private Image exitButton;
    private Image restartButton;
    private Image enterButton;
    private Boolean first = true;
    TrueTypeFont font;
    TextField textField;
    GameContainer ggc;

    public MenuScreen() throws SlickException {
        super("Menu");
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (ggc == null) {
            ggc = gc;
            textField = new TextField(ggc, font, 400, 300, 300, 50);
        }
        exitButton.draw(Display.getWidth() / 2 - exitButton.getWidth() / 2, 400, 1f);
        restartButton.draw(Display.getWidth() / 2 - restartButton.getWidth() / 2, 250, 1f);
        enterButton.draw(Display.getWidth() / 2 - restartButton.getWidth() / 2, 200, 1f);

        if(first){
            textField.setText("localhost");
            first = false;
        }
        textField.setTextColor(new Color(255, 255, 255));
        textField.render(gc, g);
    }

    @Override
    public void init() throws SlickException {
        exitButton = new Image("Images/ExitButton.png", this);
        restartButton = new Image("Images/RestartButton.png", this);
        enterButton = new Image("Images/connect.png", this);


        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, Font.PLAIN, 26), false);
    }


    @Override
    public void MousePress(int i) throws SlickException {
        if (i == 0) {
            if (ImageCollide(exitButton)) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("message", "foo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SocketHandle.socket.emit("send", obj);
            }
            if (ImageCollide(restartButton)) {
                if(SocketHandle.server) {
                    removeScreen("Game");
                    screens.add(new GameScreen1());
                    screenname = "Game";
                    

                    JSONObject obj = new JSONObject();
                    SocketHandle.socket.emit("restart", obj);
                    Log.println("Only the server can restart!");
                }
            }
            if (ImageCollide(exitButton)) {
                System.exit(0);
            }
            if (ImageCollide(enterButton)) {
            	SocketHandle.disconnect();
                SocketHandle.init(textField.getText());
                textField.deactivate();

                removeScreen("Game");
                screens.add(new GameScreen1());
                screenname = "Game";
                ScreenManager.update();
            }
        }
    }

    @Override
    public void Keypress() {
        //if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) ScreenManager.screenname = "Game";
    }


}
