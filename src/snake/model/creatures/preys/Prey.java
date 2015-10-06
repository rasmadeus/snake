/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures.preys;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import snake.model.Area;
import snake.model.cells.Cell;
import snake.model.creatures.Creature;
import snake.model.creatures.pilot.Pilot;
import snake.model.creatures.pilot.PilotType;

/**
 *
 * @author rasmadeus
 */
public abstract class Prey extends Creature {

    public static Prey make(PreyType type, Area area, Point pos) {
        switch(type) {
            case PIDGIN : return new Pidgin(area, pos);
            case FOX : return new Fox(area, pos);
            case CAT: return new Cat(area, pos);
            default: throw new IllegalArgumentException("You forget to process all items of PreyType");                
        }
    }
    
    public Prey(Area area, Point pos, String spriteFileName) {
        super(area);
        this.pos = pos;
        
        final String pathToSpriteFile = String.format("../../../../resources/%s", spriteFileName);
        sprite = new ImageIcon(getClass().getResource(pathToSpriteFile)).getImage();
    }
    
    @Override
    public void eat(Creature creature) {
        throw new UnsupportedOperationException("Preys isn't able to eat somebody.");
    }

    @Override
    public void step() {
        if (isSeekingNewPosition) {
            final Point newPos = jumpPilot.next(pos);
            if (area.take(pos, this)) {
                pos = newPos;
                isSeekingNewPosition = false;
            }
        }
        else {
            final Point newPos = randomPilot.next(pos);
            if (area.move(pos, newPos, this)) {
                synchronized(posGuard) {
                    pos = newPos;
                }
            }
        }
        rest();
    }

    @Override
    public void render(Graphics g) {
        if (isSeekingNewPosition) {
            return;
        }
        
        synchronized(posGuard) {
            final int x = pos.x * Cell.getCellSideInPixel();
            final int y = pos.y * Cell.getCellSideInPixel();
            g.drawImage(sprite, x, y, Cell.getCellSideInPixel(), Cell.getCellSideInPixel(), null);
        }
    }

    public void toSeekingNewPosition() {
        isSeekingNewPosition = true;
    }
    
    public abstract int weight();
    
    protected Point pos;
    
    private final Image sprite;
    
    private final Pilot randomPilot = Pilot.make(PilotType.ONE_STEP_RANDOM_PILOT, area);
    
    private final Pilot jumpPilot = Pilot.make(PilotType.JUMP_PILOT, area);
    
    private final Object posGuard = new Object();
    
    private volatile boolean isSeekingNewPosition = false;
}
