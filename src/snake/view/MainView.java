/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import snake.AreaPanelActivity;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import snake.HavingHeart;
import snake.controller.Controller;
import snake.model.Model;

/**
 *
 * @author rasmadeus
 */
public class MainView extends JDialog {
    
    public MainView() {        
        setTitle("Snake");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        contentPanel.add(area, area.getCardKey());
        contentPanel.add(settings, settings.getCardKey());

        add(menu, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);
        
        setCloseEvent();
        
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        contentPanel.getActionMap().put(
            "up", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    controller.up();
                }
            }
        );
        
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        contentPanel.getActionMap().put(
            "down", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    controller.down();
                }
            }
        );
                
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        contentPanel.getActionMap().put(
            "left", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    controller.left();
                }
            }
        );
                
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        contentPanel.getActionMap().put(
            "right", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    controller.right();
                }
            }
        );
        
        
    }

    public void setAreaMode(AreaPanelActivity mode) {
        area.setActivity(mode);
    }
    
    public void showSettings() {
        areaPanelHeart.pause();
        contentLayout.show(contentPanel, settings.getCardKey());
    }
    
    public void showArea() {
        areaPanelHeart.start();
        contentLayout.show(contentPanel, area.getCardKey());
    }    
    
    public void moveToCenterOfScreen() {
        setLocation(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2
        );
    }

    public void updateSizeAndPosition() {
        controller.updateViewSizeAndPosition();
    }
    
    public void setAreaSize(int side) {
        area.setSize(side, side);
        menu.setSize(side, menu.getHeight());
        setSize(area.getWidth(), area.getHeight() + menu.getHeight() + menu.getHeight());
    }
    
    public void stop() {
        areaPanelHeart.stop();
        controller.stop();                
        areaPanelHeart.join();
    }

    public void stopp() {
        menu.stopp();
    }
    
    private void setCloseEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent ev) {
                stop();
                super.windowClosed(ev);
            }
        });
    }
    
    private final Model model = new Model();
    private final Controller controller = new Controller(model, this);
    private final MenuPanel menu = new MenuPanel(controller);
    private final AreaPanel area = new AreaPanel(model, controller);
    private final HavingHeart areaPanelHeart = new AreaPanelHeart(area);
    private final SettingsPanel settings = new SettingsPanel(controller);
    
    private final CardLayout contentLayout = new CardLayout();
    JPanel contentPanel = new JPanel(contentLayout);
}
