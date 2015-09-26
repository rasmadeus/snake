/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import snake.model.pilot.StraightPilot;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import snake.model.preys.Prey;

/**
 *
 * @author rasmadeus
 */
class Snake extends Creature {
    
    public static int initialLength() {
        return 4;
    }

    public Snake(Area area, Model model) {
        super(200, area);
        
        this.model = model;
        
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
        return weight;
    }   
    
    public void setDirection(Model.Direction direction) {
        pilot.setDirection(direction);
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
        Point nextPos = pilot.next(body.get(body.size() - 1));
        synchronized (renderLock) {   
            boolean eat = area.eat(nextPos, this);
            body.add(nextPos);
            if (!eat) {
                area.release(body.get(0));
                body.remove(0);
            }
        }
    }
    
    @Override
    public void eat(Creature creature) {
        if (creature == this) {
            model.stopp();
            stop();
        }
        else {
            Prey prey = (Prey) creature;
            weight += prey.getWeight();
            prey.toJumpMode();
        }
    }
    
    private final ArrayList<Point> body = new ArrayList();
    
    private final Image head = new ImageIcon(getClass().getResource("../../resources/snakeHead.png")).getImage();

    private final Object renderLock = new Object();  
    
    private final StraightPilot pilot = new StraightPilot(area);
    
    private int weight = 0;
    
    private Model model;
}