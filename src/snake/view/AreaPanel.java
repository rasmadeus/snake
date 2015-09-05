/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;

/**
 *
 * @author rasmadeus
 */
class AreaPanel extends Canvas implements Cardable{

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
        renderSelf(g);
        renderModel(g);
        g.dispose();
        bs.show();
    }
     
    private void renderSelf(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
  
    private void renderModel(Graphics g) {        
    }
    
    private final Image background = new ImageIcon(getClass().getResource("../../resources/gameAreaBackground.png")).getImage();
}
