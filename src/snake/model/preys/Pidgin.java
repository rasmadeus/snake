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
class Pidgin extends Prey {
    
    public Pidgin(Area area, Point pos) {
        super(1000, area, pos);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    protected Image getImage() {
        return sprite;
    }
    
    private final Image sprite = new ImageIcon(getClass().getResource("../../../resources/pidgin.png")).getImage();    
}
