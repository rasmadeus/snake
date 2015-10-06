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
class JumpPilot extends Pilot {

    public JumpPilot(Area area) {
        super(area);
    }

    @Override
    public Point next(Point from) {
        final int x = posGenerator.nextInt(area.getSideWidth());
        final int y = posGenerator.nextInt(area.getSideHeight());
        return new Point(x, y);
    }
 
    private final Random posGenerator = new Random();
}
