/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import snake.model.Stepable;

/**
 *
 * @author rasmadeus
 */
class AreaPanelHeart extends Stepable {

    public AreaPanelHeart(AreaPanel areaPanel) {
        this.areaPanel = areaPanel;
    }
    
    @Override
    protected void step() {
        areaPanel.render();
        rest();
    }

    @Override
    protected long frequency() {
        return 100;
    }    
    
    private final AreaPanel areaPanel;
}
