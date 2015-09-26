/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.preys;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import snake.model.Area;
import snake.model.Cell;
import snake.model.Creature;
import snake.model.pilot.Pilot;
import snake.model.pilot.PilotType;

/**
 *
 * @author rasmadeus
 */
public abstract class Prey extends Creature {
  
    public static Prey make(Point pos, Area area)
    {
        final Random preyGenerator = new Random();
        final int numberOfPreysType = 3;
        switch(preyGenerator.nextInt(numberOfPreysType)) {
            case 0: return new Fox(area, pos);
            case 1: return new Cat(area, pos);
            case 2: return new Pidgin(area, pos);
            default: return null;
        }
    }
    
    public Prey(long frequency, Area area, Point pos) {
        super(frequency, area);
        this.pos = pos;
        pilot = Pilot.make(area, PilotType.RANDOM);
        jumpPilot = Pilot.make(area, PilotType.JUMP);
    }
    
    public void toJumpMode() {
        isJumping = true;
    }
    
    public abstract int getWeight();
    
    @Override
    public void render(Graphics g) {
        if (isJumping) {
            return;
        }
        
        synchronized (renderLock) {
            final int x = pos.x * Cell.getSideLength();
            final int y = pos.y * Cell.getSideLength();
            g.drawImage(getImage(), x, y, Cell.getSideLength(), Cell.getSideLength(), null);
        }
    }
    
    @Override
    public void eat(Creature creature) {
        throw new UnsupportedOperationException("Prey is not able to eat another creature.");
    }    
    
    @Override
    protected void step() {
        if (isJumping) {
            jump();
        }
        else {
            randomStep();
        }
    }
    
    protected abstract Image getImage();    
    
    private void jump() {
        Point nextPos = jumpPilot.next(pos);
        if (area.take(nextPos, this)) {
            pos = nextPos;
            isJumping = false;
        }
    }
    
    private void randomStep() {
        Point nextPos = pilot.next(pos);
        if (area.take(nextPos, this)) {
            area.release(pos);
            synchronized(renderLock) {
                this.pos = nextPos;
            }
        }
    }
  
    private Point pos;
    
    private final Pilot pilot;
    
    private final Object renderLock = new Object();

    private volatile boolean isJumping = false;
    
    private final Pilot jumpPilot;
}
