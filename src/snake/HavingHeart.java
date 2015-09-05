/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmadeus
 */
public abstract class HavingHeart implements Runnable, Playable{

    public HavingHeart(long frequency) {
        this.frequency = frequency;
    }    
    
    @Override
    public void run() {
        while(isRunning) {
            step();
            try {             
                Thread.sleep(frequency);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(HavingHeart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    @Override
    public void start() {
        if (!isRunning) {
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        }
    }
   
    @Override
    public void stop() {
        if (isRunning) {
            isRunning = false;
            try {             
                thread.join();
            }
            catch (InterruptedException ex) {
                Logger.getLogger(HavingHeart.class.getName()).log(Level.SEVERE, null, ex);
            }
            thread = null;
        }
   }
   
   protected abstract void step();
    
   private final long frequency;
   
   private Thread thread = null;
   
   private volatile boolean isRunning = false;
   
}
