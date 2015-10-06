/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.cells;

import java.awt.Graphics;
import snake.model.Area;

/**
 *
 * @author rasmadeus
 */
abstract class EmptyRenderCell extends Cell {

    public EmptyRenderCell(Area area) {
        super(area);
    }
    
    @Override
    public void render(Graphics g) {        
    }
}
