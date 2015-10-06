/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import snake.model.Model;

/**
 *
 * @author rasmadeus
 */
class AreaPanel extends Canvas implements Cardable{

    public AreaPanel(Model model) {
        this.model = model;
        
        setFocusable(true);
        requestFocusInWindow();
    }
    
    @Override
    public String getCardKey() {
        return "AreaPanel";
    }   
    
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            final int numberOfBuffers = 2;
            createBufferStrategy(numberOfBuffers);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        model.render(g);
        g.dispose();
        bs.show();
    }
    
    private final Model model;
}
