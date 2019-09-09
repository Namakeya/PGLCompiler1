package jframe;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Renderer;
class ReadImageComponent extends Component {//[100]
    BufferedImage bufferedImage = null;//[101]
    ReadImageComponent() {//[102]
        String imageFilename = "resource/image.png";//[103]
        try {//[104]
            bufferedImage = ImageIO.read(new File(imageFilename));//[105]
        } catch (IOException e) {//[106]
            System.out.println("image file not found. [" + imageFilename + "]");//[107]
        }
    }
    public void paint(Graphics graphics) {//[110]
    	
    	BufferedImage bi=new BufferedImage(1024,1024, BufferedImage.TYPE_INT_ARGB);
    	new Renderer().render(bi);
        graphics.drawImage(bi, 0, 0, null);//[208]
    }
    public Dimension getPreferredSize() {//[120]
        int width = 1024;//[123]
        int height = 1024;//[134]
        if (bufferedImage != null) {//[125]
            width = bufferedImage.getWidth(null);//[126]
            height = bufferedImage.getHeight(null);//[127]
        }
        return new Dimension(width, height);//[128]
    }
    void writeImage() {//[130]
    }
}