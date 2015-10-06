/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures.pilot;

import java.awt.Point;
import java.util.Random;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
class OneStepRandomPilot extends Pilot {

    public OneStepRandomPilot(Area area) {
        super(area);
    }

    @Override
    public Point next(Point from) {
        final boolean isX = posGenerator.nextBoolean();
        final int addend = posGenerator.nextBoolean() ? 1 : -1;
        final int x = from.x + (isX ? addend : 0);
        final int y = from.y + (isX ? 0 : addend);
        return getBoundedPosition(x, y);
    }
    
    private final Random posGenerator = new Random();
    
}
