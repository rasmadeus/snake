/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.controller;

import java.awt.Graphics;
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
        this.model.setController(this);
    }

    @Override
    public void start() {
        view.setAreaMode(AreaPanelActivity.GAME);
        model.start();
    }
    
    @Override
    public void stop() {
        model.stop();
        view.setAreaMode(AreaPanelActivity.RESULTS); 
    }
    
    @Override
    public void pause() {
        model.pause();
    }
    
    public void stopp() {
       view.setAreaMode(AreaPanelActivity.RESULTS);
       view.stopp();
    }
    
    public void reinitModel(int areaLength, int numberOfPreys) {
        stop();
        model.reinit(areaLength, numberOfPreys);
    }
    
    public void updateViewSizeAndPosition() {
        view.setAreaSize(model.getLength() * Model.getCellSideLength());
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
        view.stop();
        view.dispose();
    }
    
    public void up() {
        model.setSnakeDirection(Model.Direction.UP);
    }
    
    public void left() {
        model.setSnakeDirection(Model.Direction.LEFT);
    }
    
    public void down() {
        model.setSnakeDirection(Model.Direction.DOWN);
    }
    
    public void right() {        
        model.setSnakeDirection(Model.Direction.RIGHT);
    }
    
    public void render(Graphics g) {
        model.render(g);
    }
    
    private final Model model;    
    
    private final MainView view;
}
