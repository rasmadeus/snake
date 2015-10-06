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
class Pidgin extends Prey {

    public Pidgin(Area area, Point pos) {
        super(area, pos, "pidgin.png");
    }

    @Override
    public int weight() {
        return 2;
    }

    @Override
    public long frequency() {
        return 1000;
    }    
}
