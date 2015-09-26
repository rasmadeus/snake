/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rasmadeus
 */
public class Area {
    Area(int width) {
        this.width = width;
        
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < width; ++y) {
                cells.put(new Point(x, y), new Cell());
            }
        }                
    }
    
    public boolean isTaked(Point pos) {
        return cells.get(pos).isTaked();
    }
    
    public boolean take(Point pos, Creature creature) {
        return cells.get(pos).take(creature);
    }
    
    public void release(Point pos) {
        cells.get(pos).release();
    }
    
    public boolean eat(Point pos, Creature creature) {
        return cells.get(pos).eat(creature);
    }
    
    public int getWidth() {
        return width;
    }
    
    private final int width;
    
    private final Map<Point, Cell> cells = new HashMap();
}
