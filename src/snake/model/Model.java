/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import snake.model.cells.Cell;
import snake.model.creatures.Preys;
import snake.model.creatures.Snake;
import snake.view.MainView;

/**
 *
 * @author rasmadeus
 */
public class Model implements Renderable {

    public Model(MainView view) {
        this.view = view;
    }
    
    public static int getAreaMinSide() {
        return Area.getMinSide();
    }
    
    public static int getAreaMaxSide() {
        return Area.getMaxSide();
    }
    
    public static int getCellSideInPixel() {
        return Cell.getCellSideInPixel();
    }
    
    public int getSnakeLength() {
        return snake.length();
    }
    
    @Override
    public synchronized void render(Graphics g) {
        area.render(g);
        preys.render(g);
        snake.render(g);
        
        if(isStopped) {
            g.setColor(new Color(255, 255, 255, 200));            
            final int width = area.getSideWidth() * Cell.getCellSideInPixel();
            final int height = area.getSideHeight() * Cell.getCellSideInPixel();
            g.drawRect(0, 0, width, height);
                  
            final String textScore = String.format("Your score: %d", snake.getScore());
            final FontMetrics fontMetricsScore = g.getFontMetrics(fontScore);
            final int xScore = fontMetricsScore.stringWidth(textScore);
            final int yScore = fontMetricsScore.getHeight();
            g.setColor(Color.red);
            g.setFont(fontScore);
            g.drawString(textScore, (width - xScore) / 2, (height - yScore) / 2);
        }
    }
    
    public synchronized void stop() {
        preys.stop();
        snake.stop();
        isStopped = true;
        snake.join();
        preys.join();   
    }
    
    public synchronized void finishGame() {
        preys.stop();
        snake.stop();
        isStopped = true;
        view.toPauseState();
        preys.join();   
    }
    
    public synchronized void start() {
        isStopped = false;
        preys.start();
        snake.start();        
    }
    
    public synchronized void reinit(int areaWidth, int areaHeight, int numberOfPreys, int snakeLength) {
        stop();
        area = new Area(areaWidth, areaHeight);
        snake = new Snake(area, this, snakeLength);
        preys = new Preys(area, numberOfPreys, snakeLength);
    }
    
    public int preysCount() {
        return preys.size();
    }    
    
    public int getAreaWidth() {
        return area.getSideWidth();
    }
    
    public int getAreaHeight() {
        return area.getSideHeight();
    }
    
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }
     
    private Area area = new Area(20, 20);
    
    private Snake snake = new Snake(area, this, 0);    
    
    private Preys preys = new Preys(area, 0, 0);
    
    private boolean isStopped = true;
    
    private final Font fontScore = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 20);
    
    private final MainView view;
}
