/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import snake.model.Area;
import snake.model.Direction;
import snake.model.Model;
import snake.model.cells.Cell;
import snake.model.creatures.pilot.SnakePilot;
import snake.model.creatures.preys.Prey;

/**
 *
 * @author rasmadeus
 */
public class Snake extends Creature {

    public Snake(Area area, Model model, int length) {
        super(area);
        
        this.model = model;
        
        if (area.getSideWidth() < length) {
            throw new IllegalArgumentException("The snake body's length must be less or equal area width.");
        }
        
        init(length);        
    }

    @Override
    public void eat(Creature creature) {
        if (creature == this) {
            model.finishGame();
            return;
        }
        
        Prey prey = (Prey) creature;
        prey.toSeekingNewPosition();
        score += prey.weight();
    }

    @Override
    public void step() {
        final Point headPos = body.get(body.size() - 1);
        Point nextPos = pilot.next(headPos);
        final boolean catchPrey = area.catchCreature(nextPos, this);
        if (!catchPrey) {
            area.release(body.get(0));
        }
        synchronized(renderGuard) {
            body.add(nextPos);
            if (!catchPrey) {
                body.remove(0);
            }            
        }
        rest();
    }

    @Override
    public long frequency() {
        return 200;
    }

    @Override
    public void render(Graphics g) {
        synchronized(renderGuard) {
            body.forEach((partPos) -> {
                final int x = partPos.x * Cell.getCellSideInPixel();
                final int y = partPos.y * Cell.getCellSideInPixel();
                g.drawImage(bodyPart, x, y, Cell.getCellSideInPixel(), Cell.getCellSideInPixel(), null);
            });
        }
    }
    
    public int length() {
        return body.size();
    }    
    
    public void setDirection(Direction direction) {
        pilot.setDirection(direction);
    }
    
    public int getScore() {
        return score;
    }
    
    private void init(int length) {
        body = new ArrayList(area.square());
        final int y = area.getSideHeight() / 2;
        for(int x = 0; x < length; ++x) {
            Point snakePart = new Point(x, y);
            body.add(snakePart);
            area.take(snakePart, this);
        }
    }
    
    private Model model;
    
    private ArrayList<Point> body;
    
    private final Image bodyPart = new ImageIcon(getClass().getResource("../../../resources/snakeHead.png")).getImage();
    
    private final SnakePilot pilot = new SnakePilot(area);
    
    private final Object renderGuard = new Object();
    
    private int score = 0;
}
