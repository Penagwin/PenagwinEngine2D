package Player;

import ScreenManager.Elements.Image;

public class Player {
	public int clientId = -1;
	public Image image;
	public Player(int id, int x, int y, Image nimage){
		clientId = id;
		setx(x);
		sety(y);
		image = nimage;
	}

    public void setx(int nx){
        image.x = nx;
    }
    public void sety(int nx){
        image.y = nx;
    }
}
