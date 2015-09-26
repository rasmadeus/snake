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

    public MenuPanel(Controller controller) {
        this.controller = controller;
        
        putControlsToPanel();
        setListeners();
    }
    
    public void stopp() {
        isPause = true;
        start.setIcon(startIcon);
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
                if (isPause) {
                    controller.start();
                    start.setIcon(pauseIcon);
                }
                else {
                    controller.pause();
                    start.setIcon(startIcon);
                }
                isPause = !isPause;
            }
        );
        
        stop.addActionListener(
            (ActionEvent ev) -> {
                controller.stop();
                isPause = true;
                start.setIcon(startIcon);
            }
        );
        
        panelSwitcher.addActionListener(
            (ActionEvent ev) -> {
                if (isAreaPanel) {
                    controller.showSettings();
                    panelSwitcher.setIcon(areaIcon);
                    start.setIcon(startIcon);
                    isPause = true;
                } else {
                    controller.showArea();
                    panelSwitcher.setIcon(settingsIcon);
                }
                isAreaPanel = !isAreaPanel;
                start.setEnabled(isAreaPanel);
                stop.setEnabled(isAreaPanel);
            }
        );
        
        exit.addActionListener(
            (ActionEvent ev) -> {
                controller.exit();
            }
        );
    } 
    
    private final Controller controller;
    
    private final ImageIcon startIcon = new ImageIcon(getClass().getResource("../../resources/start.png"));
    private final ImageIcon pauseIcon = new ImageIcon(getClass().getResource("../../resources/pause.png"));
    private final ImageIcon stopIcon = new ImageIcon(getClass().getResource("../../resources/stop.png"));
    private final ImageIcon settingsIcon = new ImageIcon(getClass().getResource("../../resources/settings.png"));
    private final ImageIcon areaIcon = new ImageIcon(getClass().getResource("../../resources/snake.png"));
    private final ImageIcon exitIcon = new ImageIcon(getClass().getResource("../../resources/exit.png"));
    
    private final JButton start = new JButton(startIcon);
    private final JButton stop = new JButton(stopIcon);
    private final JButton panelSwitcher = new JButton(settingsIcon);
    private final JButton exit = new JButton(exitIcon);
    
    private boolean isAreaPanel = true;
    private boolean isPause = true;
}