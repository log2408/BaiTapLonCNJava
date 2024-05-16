package JumpingBox;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameFrame.Obj_game;

public class Stone extends Obj_game{
	private Rectangle rect;
	private BufferedImage img;
	public Stone(BufferedImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.img = img;
		rect = new Rectangle(x, y, w, h);
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public void update(int level) {
        setX(getX() - level);
        rect.setLocation((int)this.getX(), (int)this.getY());
    }
	public Rectangle getRect() {
        return rect;
    }
}
