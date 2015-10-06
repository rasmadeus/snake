/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

/**
 *
 * @author rasmadeus
 */
public class Heart extends Thread {
    
    public Heart(Stepable stepable) {
        this.stepable = stepable;
    } 

    public void stopStepping() {
        mustBeRunning = false;
    }    
       
    @Override
    public void run() {
        while(mustBeRunning) {
            stepable.step();
        }
    }
    
    private volatile boolean mustBeRunning = true;
    
    private final Stepable stepable;
    
}
