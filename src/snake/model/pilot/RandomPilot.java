/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.pilot;

import snake.model.pilot.Pilot;
import java.awt.Point;
import java.util.Random;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
class RandomPilot extends Pilot {

    public RandomPilot(Area area) {
        super(area);
    }

    @Override
    public Point next(Point currentPos) {
        final int addend = posGenerator.nextBoolean() ? 1 : -1;
        final boolean isAddendToX = posGenerator.nextBoolean();
        final int x = currentPos.x + (isAddendToX ? addend : 0);
        final int y = currentPos.y + (isAddendToX ? 0 : addend);
        return new Point(bound(x), bound(y));
    }
    
    private final Random posGenerator = new Random();    
}
