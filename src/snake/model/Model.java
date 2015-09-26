/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import snake.Playable;
import snake.controller.Controller;

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
    
    private enum State {
        READY_RUNNING,
        NOT_READY_RUNNING,
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
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void start() {
        if (state == State.NOT_READY_RUNNING) {
            final int numberOfPreys = preys.getNumberOfPreys();
            final int areaSideLength = area.getWidth();
            reinit(areaSideLength, numberOfPreys);
        }

        preys.start();
        snake.start();
    }
    
    @Override
    public void stop() {
        preys.stop();
        snake.stop();
        state = State.NOT_READY_RUNNING;
        snake.join();
    }
    
    public void stopp() {
        controller.stopp();
    }
    
    @Override
    public void pause() {
        preys.stop();
        snake.stop();
        state = State.READY_RUNNING;
        snake.join();
    }
    
    public void reinit(int areaSideLength, int numberOfPreys) {
        stop();
        area = new Area(areaSideLength);
        preys = new Preys(numberOfPreys, area);
        snake = new Snake(area, this);
        state = State.READY_RUNNING;
    }
    
    public int getLength() {
        return area.getWidth();
    }
    
    public int getWeight() {
        return snake.getWeight();
    }
    
    public void render(Graphics g) {
        preys.render(g);
        snake.render(g);
    }
        
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }
    
    private Area area = new Area(0);
  
    private Preys preys = new Preys(0, area);
    
    private Controller controller = null;
    
    private Snake snake = new Snake(area, this);
    
    private State state = State.READY_RUNNING;
}
