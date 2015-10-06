/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmadeus
 */
public abstract class Stepable {
    
    public void rest() {
        try {
            Thread.sleep(frequency());
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Stepable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void start() {
        if (heart == null) {
            heart = new Heart(this);
            heart.start();
        }
    }
    
    public void stop() {
        if (heart != null) {
            heart.stopStepping();
        }
    }
    
    public void join() {
        if (heart != null) {
            try {
                heart.join();
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Stepable.class.getName()).log(Level.SEVERE, null, ex);
            }
            heart = null;
        }
    }
    
    protected abstract void step();
    
    protected abstract long frequency();
    
    private Heart heart = null;
    
}
