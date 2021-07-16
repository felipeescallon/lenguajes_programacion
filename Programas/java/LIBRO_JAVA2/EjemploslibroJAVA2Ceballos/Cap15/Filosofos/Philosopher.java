/* 
 * This class requires no changes from the 1.0 version. 
 * It's kept here so the rest of the example can compile.
 */
import java.awt.*;

public class Philosopher extends Thread {
    private Chopstick leftStick, rightStick;
    private double x, y;
    private boolean sated;
    private PhilosopherArea parent;
    private int position;
    private boolean stopRequested = false;

    public Philosopher(PhilosopherArea parent,
		       double x, double y, int position) {
        super(parent.names[position]);

        this.parent = parent;
        this.position = position;
        this.x = x;
        this.y = y;

        // identify the chopsticks to my right and left
        this.rightStick = this.parent.chopsticks[position];
        if (position == 0) {
            this.leftStick = this.parent.chopsticks[this.parent.NUMPHILS-1];
        } else {
            this.leftStick = this.parent.chopsticks[position-1];
        }

        // I'm hungry
        this.sated = false;
    }

    public void run() {
        int grabDelay;
        while (stopRequested == false) {
             try {
                grabDelay = parent.controller.grabDelaySlider.getValue() * 100;
                sleep((int)(Math.random() * grabDelay));
                rightStick.grab(this);
                parent.repaintPhil(position);

                grabDelay = parent.controller.grabDelaySlider.getValue() * 100;
                sleep((int)(Math.random() * grabDelay));
                leftStick.grab(this);
                parent.repaintPhil(position);

                grabDelay = parent.controller.grabDelaySlider.getValue() * 100;
                sleep((int)(Math.random() * grabDelay));
                eat();

                grabDelay = parent.controller.grabDelaySlider.getValue() * 100;
                sleep((int)(Math.random() * grabDelay * 4));
                sated = false;
                parent.repaintPhil(position);
            } catch (java.lang.InterruptedException e) {
            }
        }
    }

    public void eat() {
        rightStick.release(this);
        leftStick.release(this);
        sated = true;
        parent.repaintPhil(position);
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect((int)x, (int)y, parent.imgs[0].getWidth(parent),
		   parent.imgs[0].getHeight(parent)+25);
        if (sated == false) {
            if (rightStick.owner == this && leftStick.owner != this) {
		// got left only
                g.drawImage(parent.imgs[1], (int)x, (int)y, parent);
            } else if (rightStick.owner == this && leftStick.owner == this) {
		// got both
                g.drawImage(parent.imgs[2], (int)x, (int)y, parent);
            } else {
		// got nothing
                g.drawImage(parent.imgs[0], (int)x, (int)y, parent);
            }
        } else {
            g.drawImage(parent.imgs[0], (int)x, (int)y, parent);
            g.setColor(Color.black);
            g.drawString("Mmm!", ((int)(x)+8),
			 ((int)(y)+parent.imgs[0].getHeight(parent)+13));
        }
    }
    public void stopRequested() {
        stopRequested = true;
    }
}
