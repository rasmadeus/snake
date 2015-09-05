/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import snake.HavingHeart;

/**
 *
 * @author rasmadeus
 */
class AreaPanelHeart extends HavingHeart{

    public AreaPanelHeart(AreaPanel areaPanel) {
        super(getUpdatingFrequency());
        this.areaPanel = areaPanel;
    }

    @Override
    protected void step() {
        areaPanel.render();
    }
    
    private static long getUpdatingFrequency() {
        return 100;
    }
    
    private final AreaPanel areaPanel;
}
