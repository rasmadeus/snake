/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author rasmadeus
 */
class Prey extends Creature {

    public Prey(long frequency, Area area, String pathToSprite, Point pos, PreyBrain brain) {
        super(frequency, area);
        sprite = new ImageIcon(getClass().getResource(pathToSprite)).getImage();
        this.pos = pos;
        this.brain = brain;
        jumpBrain = PreyBrain.make(area, PreyBrain.Type.JUMP);
    }

    public static Prey make(Point pos, Area area) {
        final int numberOfPreysType = 3;
        final String [] sprites = {"cat.png", "pidgin.png", "fox.png"};
        final int [] frequencies = {1000, 500, 250};
        
        final Random random = new Random();        
        final int i = random.nextInt(numberOfPreysType);
        
        final PreyBrain preyBrain = PreyBrain.make(area, PreyBrain.Type.STUPID_BRAIN);
        return new Prey(frequencies[i], area, String.format("../../resources/%s", sprites[i]), pos, preyBrain);
    }    
    
    public void toJumpMode() {
        isJumping = true;
    }
    
    @Override
    public void render(Graphics g) {
        if (isJumping) {
            return;
        }
        
        synchronized (renderLock) {
            final int x = pos.x * Cell.getSideLength();
            final int y = pos.y * Cell.getSideLength();
            g.drawImage(sprite, x, y, Cell.getSideLength(), Cell.getSideLength(), null);
        }
    }
    
    @Override
    protected void step() {
        if (isJumping) {
            Point nextPos = jumpBrain.next(pos);
            if (area.take(nextPos, this)) {
                pos = nextPos;
                isJumping = false;
            }
        }
        else {
            Point nextPos = brain.next(pos);
            if (area.take(nextPos, this)) {
                area.release(pos);
                synchronized(renderLock) {
                    this.pos = nextPos;
                }
            }
        }
    }
    
    private final Image sprite;
    
    private Point pos = new Point(0, 0);
    
    private final PreyBrain brain;
    
    private final Object renderLock = new Object();

    private volatile boolean isJumping = false;
    
    private final PreyBrain jumpBrain;
    
    @Override
    public void eat(Creature creature) {
    }
}
