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
abstract class PreyBrain {
    
    public enum Type {
        STUPID_BRAIN,
        JUMP,
    }
    
    static PreyBrain make(Area area, Type type) {
        switch(type) {
            case STUPID_BRAIN: return new StupidPreyBrain(area);
            case JUMP: return new JumpPositionGenerator(area);
            default: return null;
        }
    }

    public PreyBrain(Area area) {
        this.area = area;
    }
    
    public abstract Point next(Point currentPos);
    
    protected Area area;    
}
