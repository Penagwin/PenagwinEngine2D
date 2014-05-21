package ScreenManager;

import Collisions.CollisionManager;
import Player.Player1;
import Player.Player2;
import ScreenManager.Elements.Image;
import Util.SocketHandle;
import Util.LevelLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.newdawn.slick.Image.FILTER_NEAREST;

/**
 * Created by penagwin on 4/26/14.
 */
public class GameScreen1 extends Screen {
	private Boolean end = false;
	private Boolean falling = false;
	private Boolean moving = false;

	

	private float generalopacity = 100f;
	private Image fish;

	private Image bg;

	Music glitch1 = new Music("glitch1.wav");
	public ArrayList<Rectangle> storytext;

	public GameScreen1() throws SlickException {
		super("Game");
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (end) {
		}
		Player1.lavacheck();

		if (LevelLoader.getStage() == 4) {
			generalopacity -= 2.5;
			bg.setAlpha(generalopacity / 100);
			Player1.image.setAlpha(generalopacity / 100);
			fish.setAlpha(generalopacity / 100);
		}
		bg.draw(0, 0, .75f);
		g.setColor(new Color(0, 0, 0));
		for (Rectangle rect : this.collidablesr) {
			g.fillRect(rect.x, rect.y, (int) rect.getWidth(),
					(int) rect.getHeight());
		}

		Player1.image.draw(Player1.image.x, Player1.image.y, 1.5f);
		Player2.image.draw(Player2.image.x, Player2.image.y, 1.5f);

		fish.draw(400, Display.getHeight() - 160 - fish.getHeight() * 1.5f, 1f);
		// g.setColor(new Color(0, 0, 0));
		// g.drawRect(Player1.image.x, Player1.image.y, (float)
		// (Player1.image.getWidth() * 1.5), (float) (Player1.image.getHeight()
		// * 1.5));
		/*
		 * for (Image im1 : this.collidables) { g.drawRect(im1.x, im1.y,
		 * im1.getWidth(), im1.getHeight()); }
		 */
		g.setColor(new Color(255, 0, 0));
		g.fillRect(Player1.lava.x, Player1.lava.y,
				(int) Player1.lava.getWidth(), (int) Display.getHeight());

		if (Player1.Ticks % 15 == 0) {
			Player1.lava.y -= 1;

		}
		Player1.Ticks++;

	}

	@Override
	public void init() throws SlickException {

		// Interactables
		Player1.image = new Image("Images/Penguin.png", this);
		Player1.image.setFilter(FILTER_NEAREST);
		Player2.image = new Image("Images/Penguin.png", this);
		Player2.image.setFilter(FILTER_NEAREST);
		fish = new Image("Images/Fish.png", this);

		// Backgrounds or props
		bg = new Image("Images/bg.png", this);
		try {
			storytext = LevelLoader.ProgresStory();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		for (Rectangle rect : storytext) {
			this.collidablesr.add(rect);
		}

		this.collidables.remove(bg);
		this.collidables.remove(Player1.image);
		this.collidables.remove(Player2.image);

		Player1.image.x = 10;
		Player1.image.y = Display.getHeight() - 103 - Player1.image.getHeight()
				* 1.5f;
		Player2.image.x = 10;
		Player1.lava.y = Display.getHeight() + 20;

	}

	@Override
	public void MousePress(int i) {
		if (i == 0) {
			// s
		}
	}

	Boolean spaceyet1 = true;
	private float lastpos1 = -90;
	private float lastpos2 = -90;
	private int key = 0;

	@Override
	public void Keypress() throws JSONException {
		float verticalSpeed = Player1.verticalSpeed;
		float horizontalSpeed = Player1.horizontalSpeed;
		if (falling) {
			verticalSpeed += .2f * 5;
		}
		if (moving) {
			float tmp = Math.abs(horizontalSpeed) - .4f * 5;
			int tmp1 = -1;
			if(horizontalSpeed > 0){
				tmp1 = 1;
			}
			if(tmp <= 0){
				horizontalSpeed = 0;
			}else{
				horizontalSpeed = tmp*tmp1;
			}
		}
		int amountt = CollisionManager.wouldCollideAnythingY(Player1.image,
				(int) (verticalSpeed), this);
		int amountx = CollisionManager.wouldCollideAnythingX(Player1.image, (int)horizontalSpeed, this);
		if (amountx != horizontalSpeed && horizontalSpeed >= 0) {
			Player1.image.x += amountx;
			moving = false;
			horizontalSpeed = 0;
		} else if (amountx != horizontalSpeed) {
			Player1.image.x += amountx;
			horizontalSpeed = 1;
		} else {
			moving = true;
			Player1.image.x += amountx;
		}
		if (amountt != verticalSpeed && verticalSpeed >= 0) {
			Player1.image.y += amountt;
			falling = false;
			verticalSpeed = 0;
		} else if (amountt != verticalSpeed) {
			Player1.image.y += amountt;
			verticalSpeed = 1;
		} else {
			falling = true;
			Player1.image.y += amountt;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			horizontalSpeed = 6;
			moving = true;

		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			horizontalSpeed = -6;
			moving = true;
		}

		if (Keyboard.isKeyDown(Input.KEY_SPACE) && !falling) {
			verticalSpeed = -1.0f * 15;// negative value indicates an upward
										// movement
			falling = true;
		}
		Boolean send = false;
		if (SocketHandle.server && Keyboard.isKeyDown(Keyboard.KEY_P)) {
			Player1.lava.y -= 5;
			send = true;
		} else if (SocketHandle.server
				&& Keyboard.isKeyDown(Keyboard.KEY_SEMICOLON)) {
			Player1.lava.y += 5;
			send = true;
		}
		if (lastpos2 != Player1.image.y || lastpos1 != Player1.image.x) {
			key++;
			if (key % 2 == 0) {
				send = true;
			}
		} else {
			if (key != 0) {
				send = true;
			}
			key = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			JSONObject obj = new JSONObject();
			obj.put("range", "-50");
			SocketHandle.socket.emit("tackle", obj);	
		} else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			JSONObject obj = new JSONObject();
			obj.put("range", "50");
			SocketHandle.socket.emit("tackle", obj);
		}
		if (send) {
			JSONObject obj = new JSONObject();
			obj.put("x", Player1.image.x);
			obj.put("y", Player1.image.y);
			if (SocketHandle.server)
				obj.put("lava", Player1.lava.y);
			SocketHandle.socket.emit("newpos", obj);
		}
		lastpos2 = Player1.image.y;
		lastpos1 = Player1.image.x;
		Player1.lavacheck();
		Player1.verticalSpeed = verticalSpeed;
		Player1.horizontalSpeed = horizontalSpeed;
	}

	public void Text(GameContainer gc, Graphics g) {
		g.drawString("Game!", 100, 100);
	}

}
