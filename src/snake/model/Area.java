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
class Area {

    public static int minimumSideLength() {
        return 15;
    }
    
    public static int maximumSideLength() {
        return 35;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getLength() {
        return length;
    }
    
    private int length = minimumSideLength();
}
