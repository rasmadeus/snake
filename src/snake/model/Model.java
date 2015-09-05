/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

/**
 *
 * @author rasmadeus
 */
public class Model {
    
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
    
    public void setAreaLength(int length) {
        area.setLength(length);
    }
    
    public int getLength() {
        return area.getLength();
    }
    
    private Area area = new Area();
}
