package jframe;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class ReadImageUserInterface extends JFrame implements ActionListener{//[1]
    ReadImageComponent component = null;//[2]
    public ReadImageUserInterface () {//[3]
    	super();
        setTitle("Java 画像サンプル");//[4]
        this.setPreferredSize(new Dimension(1024,512));
        setLayout(new BorderLayout());//[6]
        Container contentPane = getContentPane();//[7]
        component = new ReadImageComponent();//[8]
        contentPane.add(component, BorderLayout.CENTER);//[9]
        JButton button = new JButton("Save");//[10]
        button.addActionListener(this);//[11]
        contentPane.add(button, BorderLayout.SOUTH);//[12]
    }
    public void actionPerformed(ActionEvent e){//[20]
        component.writeImage();//[21]
    }
    public void run() {//[30]
       
        WindowAdapter windowAdapter = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };//[32]
        this.addWindowListener(windowAdapter);//[33]
        this.pack();//[34]
        this.setVisible(true);//[35]
    }
}
