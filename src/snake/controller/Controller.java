/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.controller;

import snake.model.Model;
import snake.view.MainView;

/**
 *
 * @author rasmadeus
 */
public class Controller {
    
    public Controller(Model model, MainView view) {
        this.model = model;
        this.view = view;
    }
    
    public void setAreaSideLength(int value) {
        view.setAreaSize(value * Model.cellSide());
    }
    
    public void setNumberOfPreys(int value) {
        
    }
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    private Model model;    
    
    private MainView view;
}
