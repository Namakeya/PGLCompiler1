package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import jframe.ReadImageUserInterface;
import objects.basic.PGObject;
import objects.basic.PGType;
import rules.DependenciesSolver;
import rules.RangeSolver;
import rules.RuleManager;

public class Main {
	//protected static final String ROOT_DIR = System.getProperty("user.dir");
    protected static final String LOGGING_PROPERTIES = "logging.properties";
    public static Logger logger=Logger.getLogger("Main");

	public static PGObject rootObject;
	public static Renderer renderer;
	public static RuleManager ruleManager;
	public static DependenciesSolver dependenciesSolver;
	public static RangeSolver rangeSolver;
	public static TextAnalyzer textAnalyzer;

	public static void main(String[] args) {
		String filePath =  LOGGING_PROPERTIES;
		InputStream inStream;


		try {
			inStream = new FileInputStream(filePath);
			LogManager.getLogManager().readConfiguration(inStream);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		logger.finest("Hello,world!");

		ReadImageUserInterface riui=new ReadImageUserInterface();
		File input=riui.loadFile();
		if(input==null)System.exit(0);
		System.out.println(input.getAbsolutePath());
		renderer=new Renderer();
		rootObject=new PGObject("root",PGType.getType("Root"));
		ruleManager=new RuleManager();
		dependenciesSolver=new DependenciesSolver();
		rangeSolver=new RangeSolver();
		textAnalyzer=new TextAnalyzer();

		//PGBase obj1=new PGBase("r1",PGType.NO_TYPE);
		//PGBase obj2=new PGBase("r2",PGType.NO_TYPE);

		/*
		ruleManager.addRules(new RuleIs("root.rect1","Rect"));
		//ruleManager.addRules(new RuleIs("root.rect2","Rect"));

		ruleManager.addRules(new RuleIs("root.rect1.width","Variable_Double"));
		ruleManager.addRules(new RuleIs("root.rect1.height","Variable_Double"));
		ruleManager.addRules(new RuleIs("root.rect1.x","Variable_Double"));
		ruleManager.addRules(new RuleIs("root.rect1.y","Variable_Double"));
		ruleManager.addRules(new RuleIs("root.rect1.z","Variable_Double"));
		ruleManager.addRules(new RuleIs("root.rect1.texture","Variable_String"));
		ruleManager.addRules(new RuleEquals("root.rect1.width",new PGDouble("",200)));
		ruleManager.addRules(new RuleBigger("root.rect1.height",new PGDouble("",10)));
		ruleManager.addRules(new RuleSmaller("root.rect1.height",new PGDouble("",100)));
		ruleManager.addRules(new RuleBigger("root.rect1.x",new PGDouble("",100)));
		ruleManager.addRules(new RuleSmaller("root.rect1.x",new PGDouble("",300)));
		ruleManager.addRules(new RuleBigger("root.rect1.y",new PGDouble("",100)));
		ruleManager.addRules(new RuleSmaller("root.rect1.y",new PGDouble("",300)));
		ruleManager.addRules(new RuleEquals("root.rect1.texture",new PGVariable<String>("","resource/concrete_white.png")));
		*/
		List<String> lines = null;
		try {
			lines = Files.readAllLines(input.toPath(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer text=new StringBuffer();
		for(String l:lines) {
			text.append(l);
		}
		textAnalyzer.analyze(text.toString());

		ruleManager.sortRules();
		ruleManager.applyRules();
		dependenciesSolver.solveAll();
		for(DependenciesSolver.Node node:dependenciesSolver.getNodes()) {
			rangeSolver.addNode(node.subject);
		}
		rangeSolver.solve();

		logger.info(rootObject.getTreeString());

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

		riui.run();

	}

}
