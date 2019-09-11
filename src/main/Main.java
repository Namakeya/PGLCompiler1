package main;

import jframe.ReadImageUserInterface;
import objects.PGDouble;
import objects.PGObject;
import objects.PGType;
import rules.DependenciesSolver;
import rules.RuleEquals;
import rules.RuleIs;
import rules.RuleManager;

public class Main {
	public static PGObject rootObject;
	public static Renderer renderer;
	public static RuleManager ruleManager;
	public static DependenciesSolver dependenciesSolver;

	public static void main(String[] args) {
		System.out.println("Hello,world!");
		
		renderer=new Renderer();
		rootObject=new PGObject("root",PGType.getType("Root"));
		ruleManager=new RuleManager();
		dependenciesSolver=new DependenciesSolver();
		
		//PGBase obj1=new PGBase("r1",PGType.NO_TYPE);
		//PGBase obj2=new PGBase("r2",PGType.NO_TYPE);
		
		
		ruleManager.addRules(new RuleIs("root.rect1","Rect"));
		ruleManager.addRules(new RuleIs("root.rect2","Rect"));
		
		ruleManager.addRules(new RuleIs("root.rect1.width","Variable_Integer"));
		ruleManager.addRules(new RuleEquals("root.rect1.width",new PGDouble("",80)));
		ruleManager.sortRules();
		ruleManager.applyRules();
		System.out.println(rootObject.getTreeString());
		System.out.println(rootObject.getChild("rect1").getChild("width"));
		
		/*
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
		
		*/
		//File file=new File("resource/image.png");
		//System.out.println(file.getAbsolutePath());
		//System.out.println(new PGInteger(1).add(2));
		renderer.beginRegister(rootObject);
		
		new ReadImageUserInterface().run();
		
	}

}
