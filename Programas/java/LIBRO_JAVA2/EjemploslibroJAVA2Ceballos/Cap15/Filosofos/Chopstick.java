/*
 * This class requires no changes from the 1.0 version. 
 * It's kept here so the rest of the example can compile.
 */

import java.awt.*;

public class Chopstick {
    Philosopher owner = null;

    public synchronized void release(Philosopher phil) {
        if (phil == owner)
            owner = null;
        notify();
    }

    public synchronized void grab(Philosopher phil)
					throws InterruptedException {
        while (owner != null)
            wait();
        owner = phil;
    }
}
