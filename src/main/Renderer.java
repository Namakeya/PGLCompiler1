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

import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.basic.PGObject;
import objects.basic.PGString;
import objects.basic.PGType;

public class Renderer {

	public Map<String,BufferedImage> textures=new HashMap<String,BufferedImage>();
	public List<PGObject> rects=new ArrayList<PGObject>();

	public void register(PGBase pgb) {
		if(pgb instanceof PGObject) {
			PGObject obj=(PGObject)pgb;
			if(obj.getType()==PGType.getType("Rect")) {
				rects.add(obj);
			}
			for(PGBase child:obj.getChildren().values()) {
				register(child);
			}
		}
	}

	public void beginRegister(PGBase root) {
		this.register(root);
		 Comparator<PGObject> comparator = new Comparator<PGObject>() {
	            @Override
	            public int compare(PGObject o1, PGObject o2) {
	            	PGBase zo1=o1.getChild("z");
	            	double z1=0;
	            	if(zo1!=null) {
	            		z1=((PGDouble)zo1).getValue();
	            	}
	            	PGBase zo2=o2.getChild("z");
	            	double z2=0;
	            	if(zo2!=null) {
	            		z2=((PGDouble)zo2).getValue();
	            	}
	            	return ((Double)z1).compareTo(z2);
	            }
	        };
		this.rects.sort(comparator);
	}

	public BufferedImage render(BufferedImage bi) {
		for(PGObject rect:this.rects) {

			BufferedImage texbi = null;

			String texname=((PGString)rect.getChild("texture")).getValue();
			texbi=textures.get(texname);
			if(texbi==null) {
				try {
					texbi = ImageIO.read(new File(texname));
				} catch (IOException e) {
					Main.logger.severe("File "+texname+" does not exists!");
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
			g2d.fillRect((int)((PGDouble)rect.getChild("x")).getValue(),(int)((PGDouble)rect.getChild("y")).getValue(),
					(int)((PGDouble)rect.getChild("width")).getValue(),(int)((PGDouble)rect.getChild("height")).getValue());//[206]
			//g2d.drawString("RED ROSE", 20, 25);//[207]
		}
        return bi;
	}
}
