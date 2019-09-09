package main;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import objects.PGInteger;
import objects.PGObject;
import objects.PGVariable;

public class Renderer {
	
	public Map<String,BufferedImage> textures=new HashMap<String,BufferedImage>();

	public BufferedImage render(BufferedImage bi) {
		PGObject rect=Main.rootObject;
		
		
		BufferedImage texbi = null;
		
		String texname=((PGVariable<String>)rect.getVariable("texture")).get();
		texbi=textures.get(texname);
		if(texbi==null) {
			try {
				texbi = ImageIO.read(new File(texname));
			} catch (IOException e) {
				e.printStackTrace();
			}
			textures.put(texname, texbi);
		}
		TexturePaint texp=new TexturePaint(texbi,new Rectangle2D.Double(0,0,texbi.getWidth(),texbi.getHeight()));
		
        Graphics2D g2d = bi.createGraphics();//[202]
        //g2d.setColor(Color.YELLOW);//[203]
        //BasicStroke wideStroke = new BasicStroke(4.0f);//[204]
        //g2d.setStroke(wideStroke);//[205]
        g2d.setPaint(texp);
        g2d.fillRect(((PGInteger)rect.getVariable("x")).get(),((PGInteger)rect.getVariable("y")).get(),
        		((PGInteger)rect.getVariable("width")).get(),((PGInteger)rect.getVariable("height")).get());//[206]
        //g2d.drawString("RED ROSE", 20, 25);//[207]
        
        return bi;
	}
}
