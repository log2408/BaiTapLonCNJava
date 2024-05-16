package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import SignInAndSignUp.DangNhap;
import SignInAndSignUp.ThemTaiKhoan;

public class MainLG extends JFrame {
    private static Audio audio;
    private static MainLG currentInstance;
    ImageIcon icon;
    public MainLG(String audioPath) {
        audio = new Audio(audioPath);
        audio.start();
        currentInstance = this;
        
        icon = new ImageIcon("src\\images\\e.gif"); 
        JLabel backgroundLabel = new JLabel(icon);
        backgroundLabel.setLayout(new BorderLayout());
        JButton b1 = new JButton("Login");
        JButton b2 = new JButton("Register");
        JPanel p1 = new JPanel();
        p1.add(b1);
        p1.add(b2);
        p1.setOpaque(false);
        
        JPanel  p2 = new JPanel();
        JLabel jlabel = new JLabel("JUMPING BOX");
        jlabel.setForeground(Color.WHITE);
        jlabel.setFont(new java.awt.Font("sansserif", 1, 30));
        p2.add(jlabel);
        p2.setOpaque(false);
        backgroundLabel.add(BorderLayout.NORTH, p2);
        backgroundLabel.add(BorderLayout.CENTER, p1);
        p2.setBorder(BorderFactory.createEmptyBorder(50, 10, 100, 10));
        p1.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
        this.add(backgroundLabel);

        b1.addActionListener((ActionEvent e) -> {
            dangnhap(e);
        });
        b2.addActionListener((ActionEvent e) -> {
            dangky(e);
        });
        init();
    }
    
    public void dangnhap(ActionEvent e) {
        DangNhap d = new DangNhap();
        this.dispose();
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }
    
    public void dangky(ActionEvent e) {
        ThemTaiKhoan t = new ThemTaiKhoan();
        this.dispose();
        t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
    
    public void init() {
        this.setSize(600, 425);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public static void close() {
        if (currentInstance != null) {
            currentInstance.dispose();
            audio.stopAudio();
        }
    }
    
    public static void main(String[] args) {
        MainLG main = new MainLG("src/images/gamemusic.wav"); 
        main.setVisible(true);
    }
}

