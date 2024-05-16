package JumpingBox;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameFrame.Obj_game;

public class stoneStart extends Obj_game{
	private BufferedImage img;
	private Rectangle rect;
	public stoneStart(int x, int y, int w, int h) throws IOException {
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
		img = ImageIO.read(new File("\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\startStone.png"));
	}
	public void update() {
        setX(getX() - 2);
        rect.setLocation((int)this.getX(), (int)this.getY());
    }
	public void reset() {
		setX(100);
        setY(300);
        setW(500); 
        setH(129);
        rect.setLocation((int)this.getX(), (int)this.getY());
	}
	public void draw(Graphics2D g) {
		g.drawImage(img, getX(), getY(),(int) getW(), (int)getH(), null);
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public void collide(Box box) {
		Rectangle boxBounds = box.getRect();
		if (boxBounds.intersects(getRect())) {
            int boxBottom = boxBounds.y + boxBounds.height;
            int stoneTop = getRect().y;
            if (boxBottom >= stoneTop) {
                box.update(stoneTop - (int) box.getH());
            }
        } else {
        	box.update(400);
        }
	}
}
