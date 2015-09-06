/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.event.MouseWheelEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import snake.controller.Controller;
import snake.model.Model;


/**
 *
 * @author rasmadeus
 */
class SettingsPanel extends JPanel implements Cardable {
    
    public SettingsPanel(Controller controller) {
        this.controller = controller;
        
        putControlsToPanel();
        setPreysModelMaximumValue();  
        setSpinnersModelsListeners();
        reinitModel();
        
        controller.updateViewSizeAndPosition();
    }
    
    @Override
    public String getCardKey() {
        return "SettingsPanel";
    }
    
    private void putControlsToPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(areaSideLengthComment).addComponent(preysComment));
        hGroup.addGroup(layout.createParallelGroup().addComponent(areaSideLength).addComponent(preys));
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(areaSideLengthComment).addComponent(areaSideLength));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(preysComment).addComponent(preys));
        layout.setVerticalGroup(vGroup);  
    }
    
    private void setPreysModelMaximumValue() {
        final int currentValue = preysModel.getNumber().intValue();
        final int maximumValue = (int) (Math.pow(areaSideLengthModel.getNumber().intValue(), 2) - Model.initialSnakeLength());
        preysModel = new SpinnerNumberModel(Math.min(currentValue, maximumValue), 0, maximumValue, 1);
        preys.setModel(preysModel);
    }    
    
    private void setSpinnersModelsListeners() {
        areaSideLengthModel.addChangeListener(
            (ChangeEvent ev) -> {
                setPreysModelMaximumValue();
                setMouseWheelListener(preys, preysModel);
                reinitModel();                
                controller.updateViewSizeAndPosition();
            }
        );
        
        preysModel.addChangeListener(
            (ChangeEvent ev) -> {
                reinitModel();
            }
        );
        
        setMouseWheelListener(preys, preysModel);
        setMouseWheelListener(areaSideLength, areaSideLengthModel);
    }    
    
    private void reinitModel() {
        controller.stop();
        controller.reinitModel(areaSideLengthModel.getNumber().intValue(), preysModel.getNumber().intValue());
    }
    
    private void setMouseWheelListener(JSpinner spinner, SpinnerNumberModel model) {
        spinner.addMouseWheelListener(
            (MouseWheelEvent ev) -> {
                if (ev.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    final int scrollFactor = 3;
                    final int newValue = model.getNumber().intValue() - ev.getUnitsToScroll() / scrollFactor;
                    final int topBoundedValue = Math.max((int) model.getMinimum(), newValue);
                    final int bottomTopBoundedValue = Math.min((int) model.getMaximum(), topBoundedValue); 
                    model.setValue(bottomTopBoundedValue);
                }
            }
        );
    }
    
    private final Controller controller;
    
    private final JLabel areaSideLengthComment = new JLabel("Length of side of area:", JLabel.TRAILING);
    private final SpinnerNumberModel areaSideLengthModel = new SpinnerNumberModel(Model.minimumAreaSideLength(), Model.minimumAreaSideLength(), Model.maximumAreaSideLength(), 1);
    private final JSpinner areaSideLength = new JSpinner(areaSideLengthModel);
    
    private final JLabel preysComment = new JLabel("Number of preys:", JLabel.TRAILING);
    private SpinnerNumberModel preysModel = new SpinnerNumberModel(0, 0, 0, 1);
    private final JSpinner preys = new JSpinner();
}
