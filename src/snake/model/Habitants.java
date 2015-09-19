/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import snake.Playable;

/**
 *
 * @author rasmadeus
 */
class Habitants implements Playable {
    
    public Habitants(int numberOfHabitants, Area area) {
        this.area = area;
        
        preys = new ArrayList(numberOfHabitants);
        Random posGenerator = new Random();
        for(int i = 0; i < numberOfHabitants; ++i) {
            while(true) {
                Point pos = new Point(posGenerator.nextInt(area.getWidth()), posGenerator.nextInt(area.getWidth()));
                if (!area.isTaked(pos)) {
                    preys.add(Prey.make(pos, area));
                    break;
                }
            }
        }
    }
    
    @Override
    public void start() {
        preys.forEach((prey) -> {prey.start();});
    }

    @Override
    public void stop() {
        preys.forEach((prey) -> {prey.stop();});
        preys.forEach((prey) -> {prey.join();});
    }

    @Override
    public void pause() {
        stop();
    }    
    
    public void render(Graphics g) {
        preys.forEach((prey) -> {prey.render(g);});  
    }
    
    public void kill(Creature creature) {
        Prey prey = (Prey) creature;
        prey.toJumpMode();
    }    
    
    private final Area area;
 
    private final Collection<Creature> preys;    
}