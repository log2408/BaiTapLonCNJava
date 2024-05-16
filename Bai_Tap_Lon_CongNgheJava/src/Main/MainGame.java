package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import JumpingBox.game;
import SignInAndSignUp.DangNhap;
import SignInAndSignUp.ThemTaiKhoan;

public class MainGame extends JFrame{
	private String user;
	private long max;
	private static Audio audio;
	private ImageIcon icon;
	public MainGame(String user, long max) {
		this.user = user;
		this.max = max;
		audio = new Audio("src/images/gamemusic.wav");
		audio.start();
		icon = new ImageIcon("src\\images\\e.gif"); 
		JPanel  p2 = new JPanel();
		JPanel p3 = new JPanel();
        JLabel jlabel = new JLabel("JUMPING BOX");
        jlabel.setForeground(Color.WHITE);
        jlabel.setFont(new java.awt.Font("sansserif", 1, 30));
        p2.add(jlabel);
        p2.setOpaque(false);
        JLabel backgroundLabel = new JLabel(icon);
        backgroundLabel.setLayout(new BorderLayout());
        JButton b1 = new JButton("Play game");
        JButton b2 = new JButton("Log out");
        JPanel p1 = new JPanel();
        JLabel userLabel = new JLabel(user);
        userLabel.setForeground(Color.WHITE);
        p3.add(userLabel);
        p1.add(b1);
        p1.setOpaque(false);
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.add(b2, BorderLayout.EAST);
        p3.setOpaque(false);
        backgroundLabel.add(BorderLayout.SOUTH, p3);
        backgroundLabel.add(BorderLayout.CENTER, p1);
        backgroundLabel.add(BorderLayout.NORTH, p2);
        p3.setBorder(BorderFactory.createEmptyBorder(0, 480, 10, 0));
        p2.setBorder(BorderFactory.createEmptyBorder(50, 10, 100, 10));
        p1.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
        this.add(backgroundLabel);
        
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					VaoGame(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        
        b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DangXuat(e);
			}
		});
        init();
	}
	public void VaoGame(ActionEvent e) throws IOException {
		MainLG.close();
		game g = new game(user, this.max);
		g.create();
		audio.stopAudio();
		this.dispose();
	}
	public void DangXuat(ActionEvent e) {
        int dx = JOptionPane.showConfirmDialog(this, "Do you want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(dx == JOptionPane.YES_OPTION) {
        	MainLG m = new MainLG("src/images/gamemusic.wav");
    		m.setVisible(true);
    		audio.stopAudio();
    		this.dispose();
        } else {
        	return;
        }
	}
	public void init() {
		this.setSize(600, 425);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	public void close() {
		this.dispose();
	}
}
