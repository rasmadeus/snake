/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import snake.model.preys.Prey;
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
class Preys implements Playable {
    
    public Preys(int numberOfHabitants, Area area) {
        this.area = area;
        
        preys = new ArrayList(numberOfHabitants);
        final Random posGenerator = new Random();
        for(int i = 0; i < numberOfHabitants; ++i) {
            while(true) {
                final int x = posGenerator.nextInt(area.getWidth());
                final int y = posGenerator.nextInt(area.getWidth());
                final Point pos = new Point(x, y);
                if (!area.isTaked(pos)) {
                    preys.add(Prey.make(pos, area));
                    break;
                }
            }
        }
    }
    
    public int getNumberOfPreys() {
        return preys.size();
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
   
    private final Area area;
 
    private final Collection<Prey> preys;    
}