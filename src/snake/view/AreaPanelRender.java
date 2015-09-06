/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.Canvas;
import java.awt.Graphics;
import snake.model.Model;

/**
 *
 * @author rasmadeus
 */
abstract class AreaPanelRender {
    
    public AreaPanelRender(Model model, Canvas parent) {
        this.model = model;
        this.parent = parent;
    }
    
    public abstract void render(Graphics g);
    
    protected Model model;
    
    protected Canvas parent;
}
