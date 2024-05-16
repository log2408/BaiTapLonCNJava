package JumpingBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import Main.MainGame;
import SignInAndSignUp.updatePoint;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import gameFrame.AFrameOnImage;
import gameFrame.Animation;
import gameFrame.gameScreen;

public class game extends gameScreen {
    private long maxPoint;
    private boolean kiemtra = false;
    private boolean tieptuc = false;
    private int level = 2;
    private boolean checkDie = false;
    private boolean check_die_box = false;
        
    private Box box;
    private BufferedImage cha_run;
    private BufferedImage cha_double_jump;
    private Animation box_anim;
    private Animation double_jump;
    private BufferedImage jump;
    private BufferedImage fall;
    private Timer gameTimer;
    private StoneGroup st;
    private stoneStart stStart;

    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    private int CurrentScreen = BEGIN_SCREEN;
    private BufferedImage start;
    private BufferedImage over;
    private BufferedImage continueGame;

    private JButton btnDung;
    private JButton btnPlayAgain;
    private JButton btnBack;
    private JButton btnTT;

    private int point;
    private String username;
    private boolean allowSpace = true;

    public void closeWindow() {
        JFrame frame = (JFrame) this.getParent().getParent().getParent().getParent();
        frame.dispose();
    }
    
    public game(String user, long max) throws IOException {
        super(600, 425);
        this.maxPoint = Math.max(this.maxPoint, max);
        this.username = user;
        cha_run = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\Run (32x32).png"));

        box_anim = new Animation(20);
        box_anim.addFrame(new AFrameOnImage(6, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(38, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(70, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(103, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(134, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(166, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(198, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(230, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(262, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(293, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(326, 0, 25, 32));
        box_anim.addFrame(new AFrameOnImage(358, 0, 25, 32));

        box = new Box(100, 100, 25, 32);

        stStart = new stoneStart(100, 300, 500, 129);

        cha_double_jump = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\Double Jump (32x32).png"));
        double_jump = new Animation(60);
        double_jump.addFrame(new AFrameOnImage(6, 0, 27, 32));
        double_jump.addFrame(new AFrameOnImage(37, 0, 27, 32));
        double_jump.addFrame(new AFrameOnImage(69, 0, 27, 32));
        double_jump.addFrame(new AFrameOnImage(100, 0, 27, 32));
        double_jump.addFrame(new AFrameOnImage(131, 0, 27, 32));
        double_jump.addFrame(new AFrameOnImage(163, 0, 27, 32));

        ImageIcon icon1 = new ImageIcon(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\iconbutton1.png");
        btnPlayAgain = new JButton(icon1);
        ImageIcon icon2 = new ImageIcon(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\iconbutton2.png");
        btnBack = new JButton(icon2);
        btnPlayAgain.setBounds(200, 300, icon1.getIconWidth(), icon1.getIconHeight()); 
        btnBack.setBounds(350, 300, icon2.getIconWidth(), icon2.getIconHeight()); 
        ImageIcon icon3 = new ImageIcon(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\icondung.png");
        btnDung = new JButton(icon3);
        btnDung.setBounds(400, 300, icon3.getIconWidth(), icon3.getIconHeight()); 
        ImageIcon icon4 = new ImageIcon(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\icontieptuc.png");
        btnTT = new JButton(icon4);
        btnTT.setBounds(400, 300, icon4.getIconWidth(), icon4.getIconHeight()); 
        this.add(btnTT);
        this.add(btnDung);
        this.add(btnPlayAgain);
        this.add(btnBack);

        jump = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\Jump (32x32).png"));
        fall = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\Fall (32x32).png"));

        st = new StoneGroup();

        start = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\startGame.png"));
        over = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\gameOver.png"));
        continueGame = ImageIO.read(new File(
                "\\Users\\User\\Desktop\\Javapro\\Bai_Tap_Lon_CongNgheJava\\src\\images\\continue.png"));
        
        gameTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameUpdate();
                repaint();
            }
        });
        gameTimer.start();
        btnPlayAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayAgainMouseClicked(evt);
            }
        });

        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        
        btnDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDungMouseClicked(evt);
            }
        });
        
        btnTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTTMouseClicked(evt);
            }
        });
    }

    public void resetGame() {
        box.setX(100);
        box.setY(100);
        box.setJump(false);
        box.setSpeed(0);
        stStart.reset();
        st.reset();
        point = 0;
        tieptuc = false;
    }

    public void gameUpdate() {
        if (CurrentScreen == BEGIN_SCREEN) {
        	check_die_box = false;
            resetGame();
        } else if (CurrentScreen == GAMEPLAY_SCREEN) {
        	check_die_box = false;
            point++;
            box_anim.update();
            stStart.collide(box);
            st.collide(box);
            double_jump.update();
            st.update(level + point/500);
            stStart.update();
            if (box.getY() == 400) {
                this.maxPoint = Math.max(this.maxPoint, point);
                updatePoint u = new updatePoint();
                u.update(this.username, this.maxPoint);
                CurrentScreen = GAMEOVER_SCREEN;
                checkDie = true;
            }
        } else {

        }
    }

    @Override
    public void draw(Graphics2D g) {
    	ImageIcon icon = new ImageIcon("src\\images\\e.gif");
    	icon.paintIcon(null, g, -50, 0);
        stStart.draw(g);
        if (box.isJump() == true) {
            if (box.getSpeed() > 0) {
                if (box.JumpCount() == 2) {
                    double_jump.paintAnim(box.getX(), box.getY(), cha_double_jump, g);
                } else {
                    g.drawImage(jump, box.getX(), box.getY(), 32, 32, null);
                }
            } else {
                g.drawImage(fall, box.getX(), box.getY(), 32, 32, null);
            }
        } else {
            box_anim.paintAnim(box.getX(), box.getY(), cha_run, g);
            double_jump.reset();
            box.resetJump();
        }
        if (CurrentScreen == BEGIN_SCREEN) {
            g.drawImage(start, 200, 150, 200, 31, null);
            btnDung.setVisible(false);
        } else {
        	btnDung.setVisible(true);
            btnDung.setBounds(520, 10, btnDung.getWidth(),
                    btnDung.getHeight());
        }
        if (CurrentScreen == GAMEOVER_SCREEN || kiemtra == true) {
        	if(checkDie) {
        		check_die_box = true;
        		playDieSound();
        		checkDie = false;
        	}
        	if(!check_die_box) {
        		g.drawImage(continueGame, 189, 10, 223, 226, null);
        	} else {
        		g.drawImage(over, 189, 10, 223, 226, null);
        	}
            int buttonWidth = btnPlayAgain.getWidth() + btnBack.getWidth() + 10; 
            int startX = 189 + (over.getWidth() - buttonWidth) / 2;
            btnPlayAgain.setBounds(startX, over.getHeight() - 50, btnPlayAgain.getWidth(), btnPlayAgain.getHeight());
            btnBack.setBounds(startX + btnPlayAgain.getWidth() + 10, over.getHeight() - 50, btnBack.getWidth(),
                    btnBack.getHeight());
            btnPlayAgain.setVisible(true);
            btnBack.setVisible(true);
            if(CurrentScreen != GAMEOVER_SCREEN && kiemtra) {
            	 btnTT.setBounds(startX + btnBack.getWidth() - 15, over.getHeight() - 20, btnTT.getWidth(),
                         btnTT.getHeight());
            	btnTT.setVisible(true);
            	gameTimer.stop();
            }
        } else {
            btnPlayAgain.setVisible(false);
            btnBack.setVisible(false);
            btnTT.setVisible(false);
        }
        Font font = new Font("sansserif", Font.BOLD, 12);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Max point: " + this.maxPoint, 20, 20);
        g.drawString("Point: " + point, 20, 40);
        g.drawString("Username: " + this.username, 20, 60);
        st.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE && allowSpace) {
            if (CurrentScreen == BEGIN_SCREEN) {
                CurrentScreen = GAMEPLAY_SCREEN;
            } else if (CurrentScreen == GAMEPLAY_SCREEN) {
                if (box.JumpCount() < 2) {
                	playJumpSound();
                    box.setJump(true);
                    box.jump(80);
                }
            } else if (CurrentScreen == GAMEOVER_SCREEN) {
                CurrentScreen = BEGIN_SCREEN;
            }
        }
    }
    public void setAllowSpace(boolean allowSpace) {
        this.allowSpace = allowSpace;
    }
    private void playJumpSound() {
        try {
            File soundFile = new File("src/images/jump.wav").getAbsoluteFile();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void playDieSound() {
        try {
            File soundFile = new File("src/images/hurt.wav").getAbsoluteFile();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void btnDungMouseClicked(java.awt.event.MouseEvent evt) {
        if (CurrentScreen == GAMEPLAY_SCREEN) {
            kiemtra = true;
            if(!tieptuc) {
            	point = 0;
            }
        }
    }
    
    private void btnPlayAgainMouseClicked(java.awt.event.MouseEvent evt) {
        MainGame m = new MainGame(username, maxPoint);
        this.closeWindow();
        m.setVisible(true);
        btnPlayAgain.setVisible(false);
        btnBack.setVisible(false);
        kiemtra = false;
    }
    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {
        resetGame();
        CurrentScreen = BEGIN_SCREEN;
        kiemtra = false;
        gameTimer.start();
    }
    private void btnTTMouseClicked(java.awt.event.MouseEvent evt) {
    	gameTimer.start();
    	kiemtra = false;
    	CurrentScreen = GAMEPLAY_SCREEN;
    	tieptuc = true;
    }
    public static void main(String[] args) throws IOException {
    }
}
