/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.creatures.preys;

import java.awt.Point;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
class Fox extends Prey {

    public Fox(Area area, Point pos) {
        super(area, pos, "fox.png");
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public long frequency() {
        return 250;
    }    
}