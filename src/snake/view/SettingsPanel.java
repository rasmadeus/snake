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
import snake.model.Model;


/**
 *
 * @author rasmadeus
 */
class SettingsPanel extends JPanel implements Cardable {
    
    public SettingsPanel(Model model) {
        this.model = model;
        areaWidthModel = new SpinnerNumberModel(model.getAreaWidth(), Model.getAreaMinSide(), Model.getAreaMaxSide(), 1);
        areaWidth = new JSpinner(areaWidthModel);
        areaHeightModel = new SpinnerNumberModel(model.getAreaHeight(), Model.getAreaMinSide(), Model.getAreaMaxSide(), 1);
        areaHeight = new JSpinner(areaHeightModel);
        preysModel = new SpinnerNumberModel(0, 0, 0, 1);
        snakeLengthModel = new SpinnerNumberModel(1, 1, 1, 1);
        
        putControlsToPanel();
        setSnakeLengthModelMaximumValue();
        setPreysModelMaximumValue();        
        setSpinnersModelsListeners();
    }
    
    @Override
    public String getCardKey() {
        return "SettingsPanel";
    }
    
    public int getNumberOfPreys() {
        return preysModel.getNumber().intValue();
    }
    
    public int getAreaSideWidth() {
        return areaWidthModel.getNumber().intValue();
    }
    
    public int getAreaSideHeight() {
        return areaHeightModel.getNumber().intValue();
    }
    
    public int getSnakeLength() {
        return snakeLengthModel.getNumber().intValue();
    }
    
    private void putControlsToPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
            .addComponent(areaWidthComment)
            .addComponent(areaHeightComment)
            .addComponent(preysComment)
            .addComponent(snakeLengthComment)
        );
        hGroup.addGroup(layout.createParallelGroup()
            .addComponent(areaWidth)
            .addComponent(areaHeight)
            .addComponent(preys)
            .addComponent(snakeLength)
        );
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(areaWidthComment)
            .addComponent(areaWidth)
        );
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(areaHeightComment)
            .addComponent(areaHeight)
        );
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(preysComment)
            .addComponent(preys)                
        );
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(snakeLengthComment)
            .addComponent(snakeLength)                
        );
        layout.setVerticalGroup(vGroup);  
    }
    
    private void setPreysModelMaximumValue() {
        final int currentValue = getNumberOfPreys();
        final int maxPreys = getAreaSideWidth() * getAreaSideHeight() - getSnakeLength();
        preysModel = new SpinnerNumberModel(Math.min(currentValue, maxPreys), 0, maxPreys, 1);
        preys.setModel(preysModel);
        setMouseWheelListener(preys, preysModel);    }    
    
    private void setSnakeLengthModelMaximumValue() {
        final int currentValue = getSnakeLength();
        final int maxLength = getAreaSideWidth();
        snakeLengthModel = new SpinnerNumberModel(Math.min(currentValue, maxLength), 1, maxLength, 1);
        snakeLength.setModel(snakeLengthModel);
        setMouseWheelListener(snakeLength, snakeLengthModel);
    }
    
    private void setSpinnersModelsListeners() {
        areaWidthModel.addChangeListener(
            (ChangeEvent ev) -> {
                setSnakeLengthModelMaximumValue(); 
                setPreysModelMaximumValue();          
                setMouseWheelListener(preys, preysModel);            
            }
        );
        
        areaHeightModel.addChangeListener(
            (ChangeEvent ev) -> {
                setPreysModelMaximumValue();          
                setMouseWheelListener(preys, preysModel);            
            }
        );
        
        snakeLengthModel.addChangeListener(
            (ChangeEvent ev) -> {                
                setPreysModelMaximumValue();          
                setMouseWheelListener(preys, preysModel);            
            }
        );
        
        setMouseWheelListener(preys, preysModel);
        setMouseWheelListener(areaWidth, areaWidthModel);
        setMouseWheelListener(areaHeight, areaHeightModel);
        setMouseWheelListener(snakeLength, snakeLengthModel);
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

    private final JLabel areaWidthComment = new JLabel("Area width:", JLabel.TRAILING);
    private final SpinnerNumberModel areaWidthModel;
    private final JSpinner areaWidth;
    
    private final JLabel areaHeightComment = new JLabel("Area height:", JLabel.TRAILING);
    private final SpinnerNumberModel areaHeightModel;
    private final JSpinner areaHeight;
    
    private final JLabel preysComment = new JLabel("Number of preys:", JLabel.TRAILING);
    private SpinnerNumberModel preysModel;
    private final JSpinner preys = new JSpinner();
    
    private final JLabel snakeLengthComment = new JLabel("Snake length:", JLabel.TRAILING);
    private SpinnerNumberModel snakeLengthModel;
    private final JSpinner snakeLength = new JSpinner();
    
    private final Model model;
}
