/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import snake.AreaPanelActivity;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import snake.controller.Controller;
import snake.model.Model;

/**
 *
 * @author rasmadeus
 */
final class AreaPanel extends Canvas implements Cardable{

    public AreaPanel(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        
        setActivity(AreaPanelActivity.RESULTS);
        
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
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        renderContent(g);
        g.dispose();
        bs.show();
    }

    public void setActivity(AreaPanelActivity mode) {
        synchronized(renderEngineGuard) {
            renderEngine = make(mode);
        }
    }
    
    private void renderContent(Graphics g) {
        synchronized (renderEngineGuard) {
            renderEngine.render(g);
        }
    }
        
    private AreaPanelRender make(AreaPanelActivity activity) {
        switch(activity){
            case GAME: return new AreaPanelActiveGame(model, this);
            case RESULTS: return new AreaPanelResults(model, this);
            default: return null;
        }
    }

    private final Image background = new ImageIcon(getClass().getResource("../../resources/gameAreaBackground.png")).getImage();
    
    private final Model model;
    
    private AreaPanelRender renderEngine;
    
    private final Object renderEngineGuard = new Object();
    
    private final Controller controller;
}
