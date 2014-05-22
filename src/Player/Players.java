package Player;

import static org.newdawn.slick.Image.FILTER_NEAREST;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import ScreenManager.Elements.Image;

public class Players {
	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<Integer> playerids = new ArrayList<>();
	public static Image image;
	public static void init(ScreenManager.Screen screen, ArrayList<Image> collidables) throws SlickException{
		image = new Image("Images/Penguin.png", screen);
		image.setFilter(FILTER_NEAREST);
		image.x = 10;
		
	}
	public static void addPlayer(int id, int x, int y) {
		players.add(new Player(id, x, y, image));
	}
	public static Image getPlayer(int id){
		for(Player player: players)
			if(player.clientId == id)
				return player.image;
		return null;
	}
	public static void setPlayer(int id, int x, int y){
		if(!playerids.contains(id)){
			addPlayer(id, x, y);
		}
		for(Player player: players){
			if(player.clientId == id){
				player.setx(x);
				player.sety(y);
			}
		}
	}
}
