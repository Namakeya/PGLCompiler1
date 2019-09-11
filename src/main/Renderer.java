package main;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import objects.PGBase;
import objects.PGDouble;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;

public class Renderer {
	
	public Map<String,BufferedImage> textures=new HashMap<String,BufferedImage>();
	public List<PGObject> rects=new ArrayList<PGObject>();
	
	public void register(PGObject obj) {
		if(obj.getType()==PGType.getType("Rect")) {
			rects.add(obj);
		}
		for(PGBase child:obj.getChildren().values()) {
			register((PGObject)child);
		}
	}
	
	public void beginRegister(PGObject root) {
		this.register(root);
		 Comparator<PGObject> comparator = new Comparator<PGObject>() {
	            @Override
	            public int compare(PGObject o1, PGObject o2) {
	            	PGBase zo1=o1.getChild("z");
	            	double z1=0;
	            	if(zo1!=null) {
	            		z1=((PGDouble)zo1).getDouble();
	            	}
	            	PGBase zo2=o2.getChild("z");
	            	double z2=0;
	            	if(zo2!=null) {
	            		z2=((PGDouble)zo2).getDouble();
	            	}
	            	return ((Double)z1).compareTo(z2);
	            }
	        };
		this.rects.sort(comparator);
	}

	public BufferedImage render(BufferedImage bi) {
		for(PGObject rect:this.rects) {

			BufferedImage texbi = null;

			String texname=((PGVariable<String>)rect.getChild("texture")).get();
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
			g2d.fillRect(((PGDouble)rect.getChild("x")).getInt(),((PGDouble)rect.getChild("y")).getInt(),
					((PGDouble)rect.getChild("width")).getInt(),((PGDouble)rect.getChild("height")).getInt());//[206]
			//g2d.drawString("RED ROSE", 20, 25);//[207]
		}
        return bi;
	}
}
