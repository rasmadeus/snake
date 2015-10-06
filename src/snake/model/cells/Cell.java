/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.cells;

import java.awt.Point;
import snake.model.Area;
import snake.model.creatures.Creature;
import snake.model.Renderable;

/**
 *
 * @author rasmadeus
 */
public abstract class Cell implements Renderable {
    
    public static Cell make(CellType type, Area area) {
        switch(type) {
            case ONE_CREATURE_CONTAINER : return new OneCreatureContainer(area);
            default: throw new IllegalArgumentException("You forget to process all items of CellType");
        }
    }
    
    public static int getCellSideInPixel() {
        return 20;
    }
    
    public Cell(Area area) {
        this.area = area;
    }
    
    public abstract boolean take(Creature catcher);
    
    public abstract void release();
    
    public abstract boolean isTaked();

    public abstract boolean move(Point dest, Creature catcher);
    
    public abstract boolean catchCreature(Creature predator);

    protected Area area;
}
