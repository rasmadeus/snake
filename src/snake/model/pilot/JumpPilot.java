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
class JumpPilot extends Pilot {

    public JumpPilot(Area area) {
        super(area);
    }

    @Override
    public Point next(Point currentPos) {
        final int x = random.nextInt(area.getWidth());
        final int y = random.nextInt(area.getWidth());
        return new Point(x, y);
    }
    
    private final Random random = new Random();
}
