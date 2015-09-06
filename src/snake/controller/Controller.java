/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.controller;

import snake.AreaPanelActivity;
import snake.Playable;
import snake.model.Model;
import snake.view.MainView;

/**
 *
 * @author rasmadeus
 */
public class Controller implements Playable {
    
    public Controller(Model model, MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        view.setAreaMode(AreaPanelActivity.GAME);
        model.start();
    }
    
    @Override
    public void stop() {
        view.setAreaMode(AreaPanelActivity.RESULTS);
        model.stop();
    }
    
    @Override
    public void pause() {
        view.setAreaMode(AreaPanelActivity.RESULTS);
        model.pause();
    }
    
    public void reinitModel(int areaLength, int numberOfPreys) {
        stop();
        model.reinit(areaLength, numberOfPreys);
    }
    
    public void updateViewSizeAndPosition() {
        view.setAreaSize(model.getLength() * Model.cellSide());
        view.moveToCenterOfScreen();
    }    
    
    public void showSettings() {
        model.pause();
        view.showSettings();        
    }
    
    public void showArea() {
        view.showArea();
    }
    
    public void exit() {
        view.dispose();
    }
    
    public void up() {        
    }
    
    public void left() {        
    }
    
    public void down() {        
    }
    
    public void right() {        
    }
    
    private final Model model;    
    
    private final MainView view;
}
