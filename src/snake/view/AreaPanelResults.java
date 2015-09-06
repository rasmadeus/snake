/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import snake.model.Model;

/**
 *
 * @author rasmadeus
 */
public class AreaPanelResults extends AreaPanelRender {

    public AreaPanelResults(Model model, Canvas parent) {
        super(model, parent);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(fontScore);
        g.setColor(Color.RED);
        final String textScore = String.format("Your score: %d", model.getWeight());
        FontMetrics fontMetricsScore = g.getFontMetrics(fontScore);
        final int xScore = fontMetricsScore.stringWidth(textScore);
        final int yScore = fontMetricsScore.getHeight();
        g.drawString(textScore, (parent.getWidth() - xScore) / 2, yScore);
        
        g.setFont(fontAbout);
        g.setColor(Color.RED.darker());
        FontMetrics aboutFontMetrics = g.getFontMetrics(fontAbout);
        final int xAbout = aboutFontMetrics.stringWidth(textAbout);
        final int yAbout = aboutFontMetrics.getHeight() + yScore;
        g.drawString(textAbout, (parent.getWidth() - xAbout) / 2, yAbout);
        
    }   

    private final Font fontScore = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 20);
    
    private final Font fontAbout = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 10);
    private final String textAbout = "Developer: rasmadeus@gmail.com";
}
