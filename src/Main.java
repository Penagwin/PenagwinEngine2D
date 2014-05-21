import java.awt.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ScreenManager.*;
import Util.SocketHandle;

import org.json.JSONException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by penagwin on 4/26/14.
 */
class Main extends BasicGame {
	private int ctick = 0;
	private Boolean menu = false;
	private int menutick = 0;
	private org.newdawn.slick.Font mainfont;

	private Main(String gamename) {
		super(gamename);
	}

	/*
	 * The only real important thing here is to init the ScreenManager All
	 * game/menu related things should be done in the Game/Menu screens!
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		ScreenManager.screens.add(new MenuScreen());
		ScreenManager.screens.add(new GameScreen1());
		ScreenManager.screens.add(new WinnerScreen());
		ScreenManager.screens.add(new LostScreen());

		ScreenManager.screenname = "Menu";
	}

	/*
	 * If a key or mouse button is pressed tell the current Screen.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 * int)
	 */
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !menu) {
			if (ctick - menutick > 30) {
				ScreenManager.screenname = "Menu";
				menu = true;
				menutick = ctick;
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && menu) {
			if (ctick - menutick > 30) {
				ScreenManager.screenname = "Game";
				menutick = ctick;
				menu = false;
			}
		}

		if (Mouse.isButtonDown(0)) {
			ScreenManager.MousePress(0);
		}
		try {

			ScreenManager.KeyPress();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ScreenManager.update();
		ctick++;

	}

	/*
	 * Tell the current screen to render along with printing any information on
	 * all screens.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (mainfont == null) {
			mainfont = g.getFont();
		}
		try {
			ScreenManager.render(gc, g);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		g.setColor(new Color(255, 255, 255));
		g.setFont(mainfont);
		if (SocketHandle.server)
			g.drawString("Server " + SocketHandle.clientId, 9, 30);
		else
			g.drawString("Client " + SocketHandle.clientId, 9, 30);

	}

	/* Create the Game screen
	 * 
	 */
	public static void main(String[] args) throws MalformedURLException,
			JSONException {
		
		//Set the path to the natives
		//System.setProperty("org.lwjgl.librarypath", new File("linux").getAbsolutePath());
		//System.setProperty("java.library.path", new File("linux").getAbsolutePath());


		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Penagwin Game"));
			appgc.setDisplayMode(620, 480, false);
			appgc.setTargetFrameRate(119);
			appgc.setAlwaysRender(true);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}