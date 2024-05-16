package JumpingBox;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;

public class StoneGroup {
	private Queue<Stone> stones;
	private BufferedImage stone_1;
	private BufferedImage stone_2;
	private BufferedImage stone_3;
	public static int SIZE = 6;
	public StoneGroup() throws IOException {
		stone_1 = ImageIO.read(new File("\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\st1.png"));
		stone_2 = ImageIO.read(new File("\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\st2.png"));
		stone_3 = ImageIO.read(new File("\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\st3.png"));
		stones = new LinkedList<Stone>();
		Stone st = null;
		Stone tmp = new Stone(stone_1, 500, 300, 20, 20);
		for(int i = 0; i < StoneGroup.SIZE; i++) {
			int deltaY = getRandomY();
			int a = getRandomImg();
			if(a == 1) {
				st = new Stone(stone_1, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 197, 61);
			} else if(a == 2) {
				st = new Stone(stone_2, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 105, 57);
			} else if(a == 3) {
				st = new Stone(stone_3, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 171, 64);
			}
			tmp = st;
			stones.add(st);
		}
	}
	public void reset() {
	    stones.clear();
	    Stone tmp = null;
	    Stone st = null;
        tmp = new Stone(stone_1, 600, 300, 20, 20); 
	    for(int i = 0; i < StoneGroup.SIZE; i++) {
	        int deltaY = getRandomY();
	        int a = getRandomImg();
	        if(a == 1) {
                st = new Stone(stone_1, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 197, 61);
            } else if(a == 2) {
                st = new Stone(stone_2, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 105, 57);
            } else if(a == 3) {
                st = new Stone(stone_3, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 171, 64);
            }
            tmp = st;
            stones.add(st); 
	    }
	}

	public void update(int level) {
		Stone tmp = null;
		for (Stone stone : stones) {
			stone.update(level);
			tmp = stone;
		}
		if(tmp.getX() < 456) {
			Stone st = null;
			int deltaY = getRandomY();
			int a = getRandomImg();
			if(a == 1) {
				st = new Stone(stone_1, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 197, 61);
			} else if(a == 2) {
				st = new Stone(stone_2, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 105, 57);
			} else if(a == 3) {
				st = new Stone(stone_3, tmp.getX() + (int)tmp.getW() + 50, 200 + deltaY, 171, 64);
			} 
			stones.remove();
			stones.add(st);
		}
	}
	public void draw(Graphics2D g) {
		for (Stone stone : stones) {
			g.drawImage(stone.getImg(), stone.getX(), stone.getY(), null);
		}
	}
	public int getRandomImg() {
		Random random = new Random();
        int a;
        a = random.nextInt(3) + 1;
        return a;
	}
	public int getRandomY() {
        Random random = new Random();
        int a;
        a = random.nextInt(50);
        return a;
    }
	public void collide(Box box) {
		Rectangle boxBounds = box.getRect();
        for (Stone stone : stones) {
            Rectangle stoneBounds = stone.getRect();
            if (boxBounds.intersects(stoneBounds)) {
                int boxBottom = boxBounds.y + boxBounds.height;
                int stoneTop = stoneBounds.y;
                if (boxBottom - stoneTop <= box.getH()) {
                    box.update(stoneTop - (int) box.getH());
                }
            }
        }
	}
}
