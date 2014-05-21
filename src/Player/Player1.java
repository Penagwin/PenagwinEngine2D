package Player;

import Collisions.CollisionManager;
import ScreenManager.Elements.Image;
import ScreenManager.ScreenManager;
import Util.SocketHandle;
import org.json.JSONObject;
import org.lwjgl.opengl.Display;

import java.awt.*;

/**
 * Created by Penagwin on 5/8/2014.
 */
public class Player1 {
    public static Image image;
    public static float verticalSpeed = 0;
	public static float horizontalSpeed = 0;
    public static int Ticks = 0;
    public static void setx(int nx){
        image.x = nx;
    }
    public static Rectangle lava = new Rectangle(0, Display.getHeight()+20, Display.getWidth(), 0);
    public static void lavacheck(){
        if (CollisionManager.isCollided(image, lava)){
            JSONObject obj = new JSONObject();
            SocketHandle.socket.emit("lost", obj);
            ScreenManager.screenname = "lost";
            ScreenManager.update();
        }
    }
}
