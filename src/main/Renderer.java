package main;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Renderer {
	
	//public Map<String,BufferedImage>

	public BufferedImage render(BufferedImage bi) {
		BufferedImage texbi = null;
		try {
			texbi = ImageIO.read(new File("resource/wood.jpg"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		TexturePaint texp=new TexturePaint(texbi,new Rectangle2D.Double(0,0,texbi.getWidth(),texbi.getHeight()));
		
        Graphics2D g2d = bi.createGraphics();//[202]
        //g2d.setColor(Color.YELLOW);//[203]
        //BasicStroke wideStroke = new BasicStroke(4.0f);//[204]
        //g2d.setStroke(wideStroke);//[205]
        g2d.setPaint(texp);
        g2d.fillRect(10, 10, 100, 20);//[206]
        g2d.drawString("RED ROSE", 20, 25);//[207]
        
        return bi;
	}
}
