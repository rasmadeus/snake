/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.pilot;

import java.awt.Point;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
public abstract class Pilot {
    
    public static Pilot make(Area area, PilotType type) {
        switch(type) {
            case RANDOM: return new RandomPilot(area);
            case JUMP: return new JumpPilot(area);
            case STRAIGHT: return new StraightPilot(area);
            default: return null;
        }
    }

    public Pilot(Area area) {
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
