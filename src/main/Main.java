package main;

import jframe.ReadImageUserInterface;
import objects.PGInteger;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;

public class Main {
	public static PGObject rootObject;
	public static Renderer renderer;

	public static void main(String[] args) {
		System.out.println("Hello,world!");
		
		renderer=new Renderer();
		rootObject=new PGObject("root",PGType.getType("Root"));
		PGObject obj1=new PGObject("r1",PGType.getType("Rect"));
		PGObject obj2=new PGObject("r2",PGType.getType("Rect"));
		rootObject.addChild(obj1);
		rootObject.addChild(obj2);
		obj1.addChild(new PGInteger("width",80));
		obj1.addChild(new PGInteger("height",300));
		obj1.addChild(new PGInteger("x",80));
		obj1.addChild(new PGInteger("y",80));
		obj1.addChild(new PGInteger("z",80));
		obj1.addChild( new PGVariable<String>("texture","resource/concrete_white.png"));
		
		obj2.addChild(new PGInteger("width",300));
		obj2.addChild(new PGInteger("height",60));
		obj2.addChild(new PGInteger("x",100));
		obj2.addChild(new PGInteger("y",80));
		obj2.addChild(new PGInteger("z",0));
		obj2.addChild( new PGVariable<String>("texture","resource/concrete_red.png"));
		//File file=new File("resource/image.png");
		//System.out.println(file.getAbsolutePath());
		//System.out.println(new PGInteger(1).add(2));
		renderer.beginRegister(rootObject);
		
		new ReadImageUserInterface().run();
		
	}

}
