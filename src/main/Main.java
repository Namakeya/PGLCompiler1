package main;

import jframe.ReadImageUserInterface;
import objects.PGInteger;
import objects.PGObject;
import objects.PGVariable;

public class Main {
	public static PGObject rootObject;
	public static Renderer renderer;

	public static void main(String[] args) {
		System.out.println("Hello,world!");
		
		renderer=new Renderer();
		rootObject=new PGObject("root");
		PGObject obj1=new PGObject("rect");
		PGObject obj2=new PGObject("rect");
		rootObject.addChild(obj1);
		rootObject.addChild(obj2);
		obj1.setVariable("width",new PGInteger(80));
		obj1.setVariable("height",new PGInteger(500));
		obj1.setVariable("x", new PGInteger(100));
		obj1.setVariable("y", new PGInteger(100));
		obj1.setVariable("z", new PGInteger(100));
		obj1.setVariable("texture", new PGVariable<String>("resource/concrete_white.png"));
		
		obj2.setVariable("width",new PGInteger(300));
		obj2.setVariable("height",new PGInteger(50));
		obj2.setVariable("x", new PGInteger(60));
		obj2.setVariable("y", new PGInteger(150));
		obj2.setVariable("z", new PGInteger(-100));
		obj2.setVariable("texture", new PGVariable<String>("resource/concrete_red.png"));
		//File file=new File("resource/image.png");
		//System.out.println(file.getAbsolutePath());
		//System.out.println(new PGInteger(1).add(2));
		renderer.beginRegister(rootObject);
		
		new ReadImageUserInterface().run();
		
	}

}
