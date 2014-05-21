package ScreenManager.Elements;

import ScreenManager.Screen;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created by penagwin on 4/26/14.
 */
public class Image extends org.newdawn.slick.Image {
    public float x = -90, y = -90;
    private String name ="";
    private Texture intexter;
    private Boolean inverted = false;

    void setText(String s){
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTexture(texture);

    }
    public void invText(){
        if(inverted){
            this.setText("Images/"+name);
            inverted = false;
        }else {
            this.setTexture(intexter);
            inverted = true;
        }
    }
    public Image(String s, Screen screen) throws SlickException {
        super(s);
        screen.collidables.add(this);
        this.name = s.substring(7, s.length());
        try {
            intexter = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Images/zinv_" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(float f1, float f2, float f3) {
        x = f1;
        y = f2;
        super.draw(f1, f2, f3);
    }

}
