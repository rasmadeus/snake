/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import snake.view.MainView;

/**
 *
 * @author rasmadeus
 */
public class Main {
    
    public static void main(String [] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);
        mainView.reinitModel();
        mainView.updateSize();
        mainView.moveToCenterOfScreen();
        mainView.startRender();
    }
}
