package JumpingBox;

import java.awt.Rectangle;

import gameFrame.Obj_game;

public class Box extends Obj_game{
	private int speed;
	private boolean isJump;
	public static int g = 2;
	private int jumpCount;
	private Rectangle rect;
	public Box(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.speed = 0;
		this.isJump = false;
		rect = new Rectangle(x, y, w, h);
	}
	public int getSpeed() {
		return speed;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isJump() {
		return isJump;
	}
	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}
	public void jump(int height) {
		double initialVelocity = Math.sqrt(2 * g * height);
        this.speed = (int) initialVelocity;
        this.isJump = true;
		this.jumpCount++;
	}
	public void resetJump() {
		this.jumpCount = 0;
	}
	public int JumpCount() {
		return this.jumpCount;
	}
	public void update(int y) {
		this.setY(this.getY() - this.speed);
		this.speed -= Box.g;
		rect.setLocation((int)this.getX(), (int)this.getY());
		if(this.getY() >= y) {
			this.isJump = false;
			this.speed = 0;
			this.setY(y);
		}
	}
}
