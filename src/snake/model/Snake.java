/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author rasmadeus
 */
class Snake extends Creature {
    
    public static int initialLength() {
        return 4;
    }

    public Snake(Area area, Habitants habitants) {
        super(200, area);
        
        this.habitants = habitants;
        
        final int xHead = area.getWidth() / 2;
        final int yHead = area.getWidth() / 2;
        final int snakeInitialWidth = area.getWidth() / 4;
        for(int i = 0; i < snakeInitialWidth; ++i) {
            Point pos = new Point(xHead, yHead + i);
            area.take(pos, this);
            body.add(pos);
        }
    }
    
    public int getWeight() {
        return body.size();
    }

    public void setDirection(Model.Direction direction) {
        synchronized(directionLocker) {
            this.lastCameDirection = direction;
        }
    }
    
    private void changeDirection() {
        synchronized(directionLocker) {
            switch(lastCameDirection) {
                case UP: if (direction == Model.Direction.DOWN) return; break;
                case RIGHT: if (direction == Model.Direction.LEFT) return; break;
                case DOWN: if (direction == Model.Direction.UP) return; break;
                case LEFT: if (direction == Model.Direction.RIGHT) return; break;
            }
            direction = lastCameDirection;
        }
    }    
    
    @Override
    public void render(Graphics g) {
        synchronized(renderLock) {
            body.forEach((pos) -> {
                final int x = pos.x * Cell.getSideLength();
                final int y = pos.y * Cell.getSideLength();
                g.drawImage(head, x, y, Cell.getSideLength(), Cell.getSideLength(), null);
            });
        }
    }

    @Override
    protected void step() { 
        changeDirection();
        Point nextPos = this.nextPos();
        synchronized (renderLock) {   
            boolean eat = area.eat(nextPos, this);
            body.add(nextPos);
            if (!eat) {
                area.release(body.get(0));
                body.remove(0);
            }
        }
    }

    private Point nextPos() {
        Point headPos = body.get(body.size() - 1);
        int x = headPos.x;
        int y = headPos.y;
        
        switch(direction) {
            case UP: y -= 1; break;
            case RIGHT: x += 1; break;
            case DOWN: y += 1; break;
            case LEFT: x -= 1; break;
        }
        
        return new Point(bound(x), bound(y));
    }
    
    private int bound(int value) {
        if (value < 0) {
            return area.getWidth() - 1;
        }
        
        if (value > area.getWidth() - 1) {
            return 0;
        }
        
        return value;
    }
    
    private final ArrayList<Point> body = new ArrayList();
    
    private final Image head = new ImageIcon(getClass().getResource("../../resources/snakeHead.png")).getImage();

    private final Object renderLock = new Object();
    
    private final Object directionLocker = new Object();
    
    private Model.Direction lastCameDirection = Model.Direction.UP;
    
    private Model.Direction direction = Model.Direction.DOWN;

    private final Habitants habitants;
    
    @Override
    public void eat(Creature creature) {
        habitants.kill(creature);
    }
}