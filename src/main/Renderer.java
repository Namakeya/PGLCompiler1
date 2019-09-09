package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Renderer {
	
	public Map<String,BufferedImage>

	public BufferedImage render(BufferedImage bi) {
        Graphics2D g2d = bi.createGraphics();//[202]
        g2d.setColor(Color.YELLOW);//[203]
        BasicStroke wideStroke = new BasicStroke(4.0f);//[204]
        g2d.setStroke(wideStroke);//[205]
        g2d.drawi
        g2d.drawRect(10, 10, 100, 20);//[206]
        g2d.drawString("RED ROSE", 20, 25);//[207]
        
        return bi;
	}
}
