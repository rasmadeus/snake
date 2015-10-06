/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import snake.model.cells.Cell;
import snake.model.cells.CellType;
import snake.model.creatures.Creature;

/**
 *
 * @author rasmadeus
 */
public class Area implements Renderable {
    
    public static int getMinSide() {
        return 2;
    }
    
    public static int getMaxSide() {
        return 40;
    }
    
    public Area(int width, int height) {
        checkSide(width);
        checkSide(height);
        
        this.width = width;
        this.height = height;
        
        fillArea();
    }

    public int getSideWidth() {
        return this.width;
    }
    
    public int getSideHeight() {
        return this.height;
    }
    
    public int square() {
        return width * height;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, width * Cell.getCellSideInPixel(), height * Cell.getCellSideInPixel(), null);
        cells.forEach((pos, cell)->{cell.render(g);});
    }
    
    public boolean take(Point pos, Creature catcher) {
        return cells.get(pos).take(catcher);
    }
    
    public void release(Point pos) {
        cells.get(pos).release();
    }
    
    public boolean isTaked(Point pos) {
        return cells.get(pos).isTaked();
    }
    
    public boolean move(Point src, Point dest, Creature catcher) {
        return cells.get(src).move(dest, catcher);
    }
    
    public boolean catchCreature(Point pos, Creature predator) {
        return cells.get(pos).catchCreature(predator);
    }
   
    private void checkSide(int side) {
        if (side < getMinSide() || side > getMaxSide())
            throw new IllegalArgumentException(String.format("Side must be lying [%d; %d]", getMinSide(), getMaxSide()));
    }

    private void fillArea() {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                cells.put(new Point(x, y), Cell.make(CellType.ONE_CREATURE_CONTAINER, this));
            }
        }
    }
    
    private final int width;
    
    private final int height;
    
    private final Map<Point, Cell> cells = new HashMap();
    
    private final Image background = new ImageIcon(getClass().getResource("../../resources/gameAreaBackground.png")).getImage();
}
