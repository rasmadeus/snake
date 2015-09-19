/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import snake.Playable;

/**
 *
 * @author rasmadeus
 */
public class Model implements Playable{
    
    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
    
    public static int minimumAreaSideLength() {
        return 15;
    }
    
    public static int maximumAreaSideLength() {
        return 30;
    }
    
    public static int initialSnakeLength() {
        return Snake.initialLength();
    }
    
    public static int getCellSideLength() {
        return Cell.getSideLength();
    }    
    
    @Override
    public void start() {
        habitants.start();
        snake.start();
    }
    
    @Override
    public void stop() {
        habitants.stop();
        snake.stop();
        snake.join();
    }
    
    @Override
    public void pause() {
        stop();
    }
    
    public void reinit(int areaSideLength, int numberOfPreys) {
        stop();
        area = new Area(areaSideLength);
        habitants = new Habitants(numberOfPreys, area);
        snake = new Snake(area, habitants);
    }
    
    public int getLength() {
        return area.getWidth();
    }
    
    public int getWeight() {
        return snake.getWeight();
    }
    
    public void render(Graphics g) {
        habitants.render(g);
        snake.render(g);
    }
        
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }
    
    private Area area = new Area(0);
  
    private Habitants habitants = new Habitants(0, area);
    
    private Snake snake = new Snake(area, habitants);
}
