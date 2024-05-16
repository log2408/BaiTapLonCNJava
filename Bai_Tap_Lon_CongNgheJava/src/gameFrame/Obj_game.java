package gameFrame;

public class Obj_game {
	private int x, y;
	private double h, w;
	public Obj_game(int x, int y, double w, double h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
}
