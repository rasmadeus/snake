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
import snake.model.Model;


/**
 *
 * @author rasmadeus
 */
class MenuPanel extends JPanel {

    public MenuPanel(MainView mainView, Model model, AreaPanelHeart areaPanelHeart) {
        this.mainView = mainView;
        this.model = model;
        this.areaPanelHeart = areaPanelHeart;
        
        putControlsToPanel();
        setListeners();
    }
    
    public void toPauseState() {
        isPause = true;
        mustReinit = true;
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
                    if (mustReinit) {
                        mainView.reinitModel();
                        mustReinit = false;
                    }
                    start.setIcon(pauseIcon);
                    model.start();
                }
                else {
                    start.setIcon(startIcon);
                    model.stop();
                }
                isPause = !isPause;
            }
        );
        
        stop.addActionListener(
            (ActionEvent ev) -> {
                isPause = true;
                mustReinit = false;
                model.stop();
                mainView.reinitModel();
                start.setIcon(startIcon);                
            }
        );
        
        panelSwitcher.addActionListener(
            (ActionEvent ev) -> {
                if (isAreaPanel) {
                    panelSwitcher.setIcon(areaIcon);
                    start.setIcon(startIcon);
                    isPause = true;
                    model.stop();
                    mainView.showSettings();
                } else {
                    panelSwitcher.setIcon(settingsIcon);
                    mustReinit = false;
                    mainView.reinitModel();
                    mainView.showArea();
                    mainView.updateSize();
                }
                isAreaPanel = !isAreaPanel;
                start.setEnabled(isAreaPanel);
                stop.setEnabled(isAreaPanel);
            }
        );
        
        exit.addActionListener(
            (ActionEvent ev) -> {
                model.stop();
                areaPanelHeart.stop();
                areaPanelHeart.join();
                mainView.dispose();
            }
        );
    } 
    
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
    private volatile boolean isPause = true;
    private volatile boolean mustReinit = false;
    
    private final MainView mainView;
    private final Model model;
    private final AreaPanelHeart areaPanelHeart;
}