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
class StraightPositionGenerator extends PositionGenerator {

    public StraightPositionGenerator(Area area) {
        super(area);
    }

    @Override
    public Point next(Point currentPos) {
        changeDirection();
        
        int x = currentPos.x;
        int y = currentPos.y;
        
        switch(direction) {
            case UP: y -= 1; break;
            case RIGHT: x += 1; break;
            case DOWN: y += 1; break;
            case LEFT: x -= 1; break;
        }
        
        return new Point(bound(x), bound(y));
    }
    
    public void setDirection(Model.Direction direction) {
        synchronized(directionLock) {
            this.lastCameDirection = direction;
        }
    }
    
    private void changeDirection() {
        synchronized(directionLock) {
            switch(lastCameDirection) {
                case UP: if (direction == Model.Direction.DOWN) return; break;
                case RIGHT: if (direction == Model.Direction.LEFT) return; break;
                case DOWN: if (direction == Model.Direction.UP) return; break;
                case LEFT: if (direction == Model.Direction.RIGHT) return; break;
            }
            direction = lastCameDirection;
        }
    }    
    
    private final Object directionLock = new Object();
    
    private Model.Direction lastCameDirection = Model.Direction.UP;
    
    private Model.Direction direction = Model.Direction.DOWN;
}
