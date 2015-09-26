/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import snake.HavingHeart;

/**
 *
 * @author rasmadeus
 */
public abstract class Creature extends HavingHeart {

    public Creature(long frequency, Area area) {
        super(frequency);
        this.area = area;
    }
    
    public abstract void render(Graphics g);

    public abstract void eat(Creature creature);
    
    protected Area area;
}
