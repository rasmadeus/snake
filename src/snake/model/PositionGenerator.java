/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Point;

/**
 *
 * @author rasmadeus
 */
abstract class PositionGenerator {
    
    public enum Type {
        STUPID,
        JUMP,
        STRAIGHT
    }
    
    static PositionGenerator make(Area area, Type type) {
        switch(type) {
            case STUPID: return new StupidPreyBrain(area);
            case JUMP: return new JumpPositionGenerator(area);
            case STRAIGHT: return new StraightPositionGenerator(area);
            default: return null;
        }
    }

    public PositionGenerator(Area area) {
        this.area = area;
    }
    
    public abstract Point next(Point currentPos);
    
    protected int bound(int coord) {
        if (coord < 0) {
            return area.getWidth() - 1;
        }            
        
        if (coord > area.getWidth() - 1) {
            return 0;
        }
        
        return coord;
    }    
    
    protected Area area;    
}
