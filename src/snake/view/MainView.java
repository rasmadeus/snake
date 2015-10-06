/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

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
import snake.model.Direction;
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
                    model.setSnakeDirection(Direction.UP);
                }
            }
        );
        
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        contentPanel.getActionMap().put(
            "down", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    model.setSnakeDirection(Direction.DOWN);
                }
            }
        );
                
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        contentPanel.getActionMap().put(
            "left", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    model.setSnakeDirection(Direction.LEFT);
                }
            }
        );
                
        contentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        contentPanel.getActionMap().put(
            "right", 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    model.setSnakeDirection(Direction.RIGHT);
                }
            }
        );
    }
    
    public void showSettings() {
        contentLayout.show(contentPanel, settings.getCardKey());
    }
    
    public void showArea() {
        contentLayout.show(contentPanel, area.getCardKey());
    }    
    
    public void moveToCenterOfScreen() {
        setLocation(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2
        );
    }
    
    public void reinitModel() {
        final int areaWidth = settings.getAreaSideWidth();
        final int areaHeight = settings.getAreaSideHeight();
        final int numberOfPreys = settings.getNumberOfPreys();
        final int snakeLength = settings.getSnakeLength();
        model.reinit(areaWidth, areaHeight, numberOfPreys, snakeLength);
    }    
    
    public void updateSize() {
        final int width = settings.getAreaSideWidth() * Model.getCellSideInPixel();
        final int height = settings.getAreaSideHeight() * Model.getCellSideInPixel();
        setSize(width, height + menu.getHeight() + menu.getHeight());
    }
    
    public void startRender() {
        areaHeart.start();
    }
    
    public void toPauseState() {
        menu.toPauseState();
    }
    
    private void setCloseEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent ev) {
                model.stop();
                areaHeart.stop();
                areaHeart.join();
                super.windowClosed(ev);
            }
        });
    }
    
    private final Model model = new Model(this);
    private final AreaPanel area = new AreaPanel(model);
    private final AreaPanelHeart areaHeart = new AreaPanelHeart(area);
    private final MenuPanel menu = new MenuPanel(this, model, areaHeart);
    private final SettingsPanel settings = new SettingsPanel(model);
    
    private final CardLayout contentLayout = new CardLayout();
    JPanel contentPanel = new JPanel(contentLayout);
}
