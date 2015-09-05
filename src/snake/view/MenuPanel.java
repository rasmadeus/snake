/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import snake.controller.Controller;

/**
 *
 * @author rasmadeus
 */
class MenuPanel extends JPanel {

    public MenuPanel(MainView mainView, Controller controller) {
        this.mainView = mainView;
        this.controller = controller;
        
        putControlsToPanel();
        setListeners();
    }
   
    private void putControlsToPanel() {
        setLayout(new GridLayout(1, 4));
        add(start);
        add(stop);
        add(panelSwitcher);
        add(exit);
    }
    
    private void setListeners() {
        start.addActionListener(
            (ActionEvent ev) -> {
                controller.start();
            }
        );
        
        stop.addActionListener(
            (ActionEvent ev) -> {
                controller.stop();
            }
        );
        
        panelSwitcher.addActionListener(
            (ActionEvent ev) -> {
                if (isAreaPanel) {
                    mainView.stop();                    
                    mainView.showSettings();
                    panelSwitcher.setIcon(new ImageIcon(getClass().getResource("../../resources/snake.png")));
                } else {
                    mainView.showArea();
                    panelSwitcher.setIcon(new ImageIcon(getClass().getResource("../../resources/settings.png")));
                }
                isAreaPanel = !isAreaPanel;
                start.setEnabled(isAreaPanel);
                stop.setEnabled(isAreaPanel);
            }
        );
        
        exit.addActionListener(
            (ActionEvent ev) -> {
                mainView.dispose();
            }
        );
    } 
    
    private final Controller controller;
    private final MainView mainView;
    
    private final JButton start = new JButton(new ImageIcon(getClass().getResource("../../resources/start.png")));
    private final JButton stop = new JButton(new ImageIcon(getClass().getResource("../../resources/stop.png")));
    private final JButton panelSwitcher = new JButton(new ImageIcon(getClass().getResource("../../resources/settings.png")));
    private final JButton exit = new JButton(new ImageIcon(getClass().getResource("../../resources/exit.png")));
    
    private boolean isAreaPanel = true;
}