package gameFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class gameScreen extends JPanel implements KeyListener{
	private int width, height;
	public gameScreen(int w, int h) {
		width = w;
		height = h;
		setFocusable(true);
		addKeyListener(this);
	}
	public void create() {
		JFrame jfame = new JFrame();
		jfame.setSize(width, height);
		jfame.setLocationRelativeTo(null);
		jfame.add(this);
		jfame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfame.setVisible(true);
		jfame.setResizable(false);
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d); 
    }
	@Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    public abstract void draw(Graphics2D g);
    public abstract void gameUpdate();
}

