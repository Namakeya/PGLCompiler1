package main;

import jframe.ReadImageUserInterface;
import objects.PGInteger;
import objects.PGObject;
import objects.PGVariable;

public class Main {
	public static PGObject rootObject;

	public static void main(String[] args) {
		System.out.println("Hello,world!");

		rootObject=new PGObject("rect");
		rootObject.setVariable("width",new PGInteger(80));
		rootObject.setVariable("height",new PGInteger(500));
		rootObject.setVariable("x", new PGInteger(100));
		rootObject.setVariable("y", new PGInteger(100));
		rootObject.setVariable("texture", new PGVariable<String>("resource/concrete_white.png"));
		//File file=new File("resource/image.png");
		//System.out.println(file.getAbsolutePath());
		//System.out.println(new PGInteger(1).add(2));
		
		new ReadImageUserInterface().run();
		
	}

}
