/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
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

    public void setAreaSize(int side) {
        setSize(side, side + menu.getHeight());
    }
    
    private final Model model = new Model();
    private final Controller controller = new Controller(model, this);
    private final MenuPanel menu = new MenuPanel(this, controller);
    private final AreaPanel area = new AreaPanel();
    private final SettingsPanel settings = new SettingsPanel(controller);
    
    private final CardLayout contentLayout = new CardLayout();
    JPanel contentPanel = new JPanel(contentLayout);
}