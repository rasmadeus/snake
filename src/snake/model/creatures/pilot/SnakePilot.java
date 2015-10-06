/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures.pilot;

import java.awt.Point;
import snake.model.Area;
import snake.model.Direction;

/**
 *
 * @author rasmadeus
 */
public class SnakePilot extends Pilot {

    public SnakePilot(Area area) {
        super(area);
    }

    @Override
    public Point next(Point from) {
        validateDirection();
        final Point nextPosition = getNextPosition(from);
        return getBoundedPosition(nextPosition.x, nextPosition.y);
    }
    
    public synchronized void setDirection(Direction direction) {
        lastCameDirection = direction;
    }    
    
    private synchronized void validateDirection() {
        switch(lastCameDirection) {
            case UP: if (currentDirection != Direction.DOWN) currentDirection = lastCameDirection; break;
            case RIGHT: if (currentDirection != Direction.LEFT) currentDirection = lastCameDirection; break;
            case DOWN: if (currentDirection != Direction.UP) currentDirection = lastCameDirection; break;
            case LEFT: if (currentDirection != Direction.RIGHT) currentDirection = lastCameDirection; break;
        }
    }
    
    private Point getNextPosition(Point from) {
        switch(currentDirection) {
            case UP: return new Point(from.x, from.y - 1);
            case RIGHT: return new Point(from.x + 1, from.y);
            case DOWN: return new Point(from.x, from.y + 1);
            case LEFT: return new Point(from.x - 1, from.y);
            default: throw new IllegalArgumentException("You forget to process all items of Direction");
        }
    }
    
    private Direction currentDirection = Direction.RIGHT;
    
    private Direction lastCameDirection = currentDirection;
}
