/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import snake.Playable;

/**
 *
 * @author rasmadeus
 */
public class Model implements Playable{
    
    public static int minimumAreaSideLength() {
        return Area.minimumSideLength();
    }
    
    public static int maximumAreaSideLength() {
        return Area.maximumSideLength();
    }
    
    public static int initialSnakeLength() {
        return Snake.initialLength();
    }
    
    public static int cellSide() {
        return 20;
    }    
    
    @Override
    public void start() {
        
    }
    
    @Override
    public void stop() {
        
    }
    
    @Override
    public void pause() {
        
    }
    
    public void reinit(int areaLength, int numberOfPreys) {
        stop();
        area.setLength(areaLength);
    }
    
    public int getLength() {
        return area.getLength();
    }
    
    public int getWeight() {
        return snake.getWeight();
    }
    
    private Area area = new Area();
    
    private Snake snake = new Snake();
}
