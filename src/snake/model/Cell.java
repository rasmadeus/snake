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
public class Cell {
    
    static public int getSideLength() {
        return 20;
    }
    
    public synchronized boolean isTaked() {
        return creature != null;
    }
    
    public synchronized boolean take(Creature creature) {
        if (this.creature != null)
            return false;
        this.creature = creature;
        return true;
    }
    
    public synchronized void release() {
        creature = null;
    } 
    
    public synchronized boolean eat(Creature creature) {
        if (this.creature != null) {
            creature.eat(this.creature);
            this.creature = creature;
            return true;
        }
        this.creature = creature;
        return false;
    }
    
    private Creature creature = null;
}
