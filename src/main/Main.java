package main;

import jframe.ReadImageUserInterface;
import objects.PGBase;
import objects.PGInteger;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;
import rules.RuleBase;
import rules.RuleEquals;
import rules.RuleIs;

public class Main {
	public static PGObject rootObject;
	public static Renderer renderer;

	public static void main(String[] args) {
		System.out.println("Hello,world!");
		
		renderer=new Renderer();
		rootObject=new PGObject("root",PGType.getType("Root"));
		PGBase obj1=new PGBase("r1",PGType.NO_TYPE);
		PGBase obj2=new PGBase("r2",PGType.NO_TYPE);
		
		RuleBase ri1=new RuleIs(obj1,PGType.getType("Rect"));
		obj1=ri1.apply();
		RuleBase ri2=new RuleIs(obj2,PGType.getType("Rect"));
		obj2=ri2.apply();
		
		
		rootObject.addChild(obj1);
		rootObject.addChild(obj2);
		
		PGBase width=new PGBase("width",PGType.NO_TYPE);
		width=new RuleIs(width,PGType.INTEGER_TYPE).apply();
		width=new RuleEquals(width,new PGInteger("",80)).apply();
		
		
		obj1.addChild(width);
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
