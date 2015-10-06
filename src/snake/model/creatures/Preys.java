/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import snake.model.Area;
import snake.model.Renderable;
import snake.model.creatures.preys.Prey;
import snake.model.creatures.preys.PreyType;

/**
 *
 * @author rasmadeus
 */
public class Preys implements Renderable {
    
    public Preys(Area area, int numberOfPreys, int snakeLength) {
        if (area.square() - snakeLength < numberOfPreys) {
            throw new IllegalArgumentException("There isn't place in the area for preys.");    
        }
        
        final Random preyParamsGen = new Random();
        preys = new ArrayList(numberOfPreys);
        for(int i = 0; i < numberOfPreys; ++i) {
            while(true) {
                final Point pos = getPoint(area, preyParamsGen);
                if (!area.isTaked(pos)) {
                    final Creature prey = getPrey(area, pos, preyParamsGen);
                    area.take(pos, prey);
                    preys.add(prey);
                    break;
                }
            }
        }
    }
    
    public void start() {
        preys.forEach((prey) -> {prey.start();});
    }
    
    public void stop() {
        preys.forEach((prey) -> { prey.stop(); });
    }
    
    public void join() {
        preys.forEach((prey) -> { prey.join();});
    }    
    
    @Override
    public void render(Graphics g) {
        preys.forEach((prey) -> { prey.render(g);});
    }
    
    public int size() {
        return preys.size();
    }
    
    private Point getPoint(Area area, Random posGen) {
        final int x = posGen.nextInt(area.getSideWidth());
        final int y = posGen.nextInt(area.getSideHeight());
        return new Point(x, y);
    }
    
    private Prey getPrey(Area area, Point pos, Random preyTypeGen) {
        final PreyType type = PreyType.values()[preyTypeGen.nextInt(PreyType.values().length)];
        return Prey.make(type, area, pos);
    }
    
    private final Collection<Creature> preys;
}
