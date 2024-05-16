package gameFrame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<AFrameOnImage> frames;
    private int currentFrameIndex = 0;
    private long frameStart;
    private int frameDelay;
    public Animation(int frameDelay) {
        this.frames = new ArrayList<AFrameOnImage>();
        this.frameDelay = frameDelay;
        this.frameStart = System.currentTimeMillis();
    }
    public void reset() {
    	currentFrameIndex = 0;
    }
    public void update() {
        if(System.currentTimeMillis() - frameStart > frameDelay) {
            currentFrameIndex++;
            frameStart = System.currentTimeMillis();
            if(currentFrameIndex == frames.size()) {
                currentFrameIndex = 0;
            }
        }
    }
    public void addFrame(AFrameOnImage frame) {
    	this.frames.add(frame);
    }
    public void paintAnim(int x, int y, BufferedImage image, Graphics2D g2) {
        frames.get(currentFrameIndex).Paint(x, y, image, g2);
    }
}
