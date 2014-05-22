package Util;

import Collisions.CollisionManager;
import Player.Player1;
import Player.Players;
import ScreenManager.ScreenManager;
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import ScreenManager.Elements.Image;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.Rectangle;
import java.net.MalformedURLException;

/**
 * Created by Penagwin on 5/8/2014.
 */
public class SocketHandle {
	public static SocketIO socket;
	public static Boolean server = false;
	public static String ipString = "";

	public static void init(String ip) {
		ipString = ip;
		try {
			// Address to connect to
			socket = new SocketIO("http://" + ip + ":3001");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		socket.connect(new IOCallback() {
			@Override
			public void onMessage(JSONObject json, IOAcknowledge ack) {
				System.out.println("onMessage function fired");

			}

			@Override
			public void onMessage(String data, IOAcknowledge ack) {
				System.out.println("Server said: " + data);
				try {
					JSONObject json = new JSONObject(data); // json

					System.out.println(json.get("name"));

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(SocketIOException socketIOException) {
				System.out.println("an Error occured");
				socketIOException.printStackTrace();
			}

			@Override
			public void onDisconnect() {
				System.out.println("Connection terminated.");
			}

			@Override
			public void onConnect() {
				System.out.println("Connection established");
			}

			@Override
			public void on(String event, IOAcknowledge ack, Object... args) {
				Object[] arguments = args;

				JSONObject json = null; // json
				try {
					json = new JSONObject(arguments[0].toString());

					assert json != null;

					// If the recieved event is newpos then reset the other
					// player's info.
					if (event.equals("newpos")) {
						int x = (Integer.valueOf(json.get("x").toString()));
						int y = (Integer.valueOf(json.get("y").toString()));
						int id = (Integer.valueOf(json.get("id").toString()));
						Players.setPlayer(id, x, y);

						// If this is a client then also reset the Lava level
						// and timer
						if (!server) {
							Player1.lava.y = Integer.valueOf(json.get("lava").toString());
							Player1.Ticks = 0;
							Player1.lavacheck();
						}
						Log.println(Player1.lava.height);

					} else if (event.equals("lost")) {
						// The other person lost. So this won.
						ScreenManager.screenname = "winner";
						ScreenManager.update();

					} else if (event.equals("status")) {
						String status = json.get("status").toString();
						if (status == "restart")
							ScreenManager.restartGame();
						if (status.equals("newId")) {
							clientId = Integer.valueOf(json.get("id").toString());
							if (clientId == 0) {
								server = true;
								Log.println("server");
							} else {
								server = false;
								Log.println("client");

							}
						}

					} else if (event.equals("tackle")) {
						int range = Integer.valueOf(json.get("range").toString());
						int id = (Integer.valueOf(json.get("id").toString()));
						Image image = Players.getPlayer(id);
						Rectangle area = new Rectangle((int) image.x + range, (int) image.y, (int) (image.getWidth() * 1.5),
								(int) (image.getHeight() * 1.5));
						if (CollisionManager.isCollided(Player1.image, area)) {
							if (range > 0)
								Player1.horizontalSpeed += 10;
							else
								Player1.horizontalSpeed -= 10;

						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public static void disconnect() {
		if (socket != null)
			socket.disconnect();
		socket = null;
	}

	public static Boolean lava = true;
	public static int clientId = -1;

}