/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures;

import snake.model.Area;
import snake.model.Renderable;
import snake.model.Stepable;

/**
 *
 * @author rasmadeus
 */
public abstract class Creature extends Stepable implements Renderable {
    
    public Creature(Area area) {
        this.area = area;
    }
    
    public abstract void eat(Creature creature);
    
    protected final Area area;
}
