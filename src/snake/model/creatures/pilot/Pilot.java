/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures.pilot;

import java.awt.Point;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
public abstract class Pilot {
    
    public static Pilot make(PilotType type, Area area) {
        switch(type) {
            case ONE_STEP_RANDOM_PILOT: return new OneStepRandomPilot(area);
            case SNAKE_PILOT: return new SnakePilot(area);
            case JUMP_PILOT: return new JumpPilot(area);
            default: throw new IllegalArgumentException("You forget to process all items of PilotType");
        }
    }
    
    public Pilot(Area area) {
        this.area = area;
    }    
   
    public abstract Point next(Point from);

    protected Point getBoundedPosition(int x, int y) {
        final int xBounded = getBoundedSide(x, area.getSideWidth());
        final int yBounded = getBoundedSide(y, area.getSideHeight());
        return new Point(xBounded, yBounded);
    }
    
    private int getBoundedSide(int width, int side) {
        if (width < 0) {
            return side - 1;
        }
        else if (width > side - 1) {
            return 0;
        }
        else {
            return width;
        }
    }
    
    final Area area;    
}