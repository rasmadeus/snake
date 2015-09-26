/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.preys;

import snake.model.preys.Prey;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
class Cat extends Prey {
    
    public Cat(Area area, Point pos) {
        super(500, area, pos);
    }

    @Override
    public int getWeight() {
        return 2;
    }

    @Override
    protected Image getImage() {
        return sprite;
    }
    
    private final Image sprite = new ImageIcon(getClass().getResource("../../../resources/cat.png")).getImage();
}
