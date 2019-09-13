package jframe;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import main.Main;
class ReadImageComponent extends Component {//[100]
    BufferedImage bufferedImage = null;//[101]
    ReadImageComponent() {//[102]
    	/*
        String imageFilename = "resource/wood.jpg";//[103]
        try {//[104]
            bufferedImage = ImageIO.read(new File(imageFilename));//[105]
        } catch (IOException e) {//[106]
            System.out.println("image file not found. [" + imageFilename + "]");//[107]
        }
        */
    }
    public void paint(Graphics graphics) {//[110]
    	
    	bufferedImage=new BufferedImage(1024,512, BufferedImage.TYPE_INT_ARGB);
    	Main.renderer.render(bufferedImage);
        graphics.drawImage(bufferedImage, 0, 0, null);//[208]
    }
    public Dimension getPreferredSize() {//[120]
        int width = 1024;//[123]
        int height = 512;//[134]
        if (bufferedImage != null) {//[125]
            width = bufferedImage.getWidth(null);//[126]
            height = bufferedImage.getHeight(null);//[127]
        }
        return new Dimension(width, height);//[128]
    }
    void writeImage() {//[130]
    	JFileChooser filechooser = new JFileChooser();
    	filechooser.setCurrentDirectory(new File("").getAbsoluteFile());
        int selected = filechooser.showSaveDialog(this);
        File imageFilename=null;
        if (selected == JFileChooser.APPROVE_OPTION){
          imageFilename=filechooser.getSelectedFile();
        }else {
        	return;
        }
        try {//[303]
            ImageIO.write(bufferedImage, "png", imageFilename);//[304]
        } catch (Exception e) {//[305]
            System.out.println("image file write error. [" + imageFilename + "]");//[306]
        }
    }
}