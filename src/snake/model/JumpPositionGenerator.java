/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author rasmadeus
 */
class JumpPositionGenerator extends PositionGenerator {

    public JumpPositionGenerator(Area area) {
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
