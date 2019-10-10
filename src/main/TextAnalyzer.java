package main;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import objects.basic.PGDouble;
import objects.basic.PGLiteral;
import objects.basic.PGObject;
import objects.basic.PGRanged;
import objects.basic.PGString;
import objects.basic.PGSymbol;
import objects.basic.PGType;
import objects.function.FunctionDivide;
import objects.function.FunctionEquals;
import objects.function.FunctionMinus;
import objects.function.FunctionMultiply;
import objects.function.FunctionPlus;
import objects.function.PGFunction;
import rules.RuleBigger;
import rules.RuleExtends;
import rules.RuleFEquals;
import rules.RuleIs;
import rules.RuleSEquals;
import rules.RuleSmaller;

public class TextAnalyzer {

	public static Logger logger=Main.logger;
	public static final Map<String,Class<? extends PGFunction>> operators=
			new HashMap<String,Class<? extends PGFunction>>();
	static {
		//least priority

		operators.put("+", FunctionPlus.class);
		operators.put("-", FunctionMinus.class);
		operators.put("*", FunctionMultiply.class);
		operators.put("/", FunctionDivide.class);

		//most priority
	}

	public void analyze(String text) {
		logger.fine("start analyzing text");
		text=text.replaceAll("\r\n|[\n\r\u2028\u2029\u0085]", "");
		String[] secs=text.split("&");
		for(String sec:secs) {
			logger.finer("..."+sec);
			String[] patt=sec.split(" is ");
			if(patt.length>1) {
				patt=clean(patt);

				Main.ruleManager.addRules(new RuleIs(PGObject.getOrCreateFromFullpath(patt[0]),patt[1]));
				continue;
			}

			patt=sec.split(" extends ");
			if(patt.length>1) {
				patt=clean(patt);

				Main.ruleManager.addRules(new RuleExtends(PGType.getType(patt[0]),patt[1]));
				continue;
			}

			patt=sec.split("=");
			if(patt.length>1) {
				patt=clean(patt);
				if(patt[1].startsWith("\"") && patt[1].endsWith("\"")) {
					String str=patt[1].replace("\"", "");
					Main.ruleManager.addRules(new RuleSEquals(PGString.getOrCreateFromFullpath(patt[0]),str));
				}else {
					Main.ruleManager.addRules(new RuleFEquals
							(FunctionEquals.getOrCreateFromFullpath(patt[0])
									,this.interpretFormula(patt[1])));
				}
				continue;
			}
			patt=sec.split(">");
			if(patt.length>1) {
				patt=clean(patt);

				Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
						(patt[0]),this.interpretFormula(patt[1])));

				continue;
			}
			patt=sec.split("<");
			if(patt.length==2) {
				patt=clean(patt);
				Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
						(patt[0]),this.interpretFormula(patt[1])));

				continue;
			}else if(patt.length==3) {
				patt=clean(patt);

				Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
						(patt[1]),this.interpretFormula(patt[0])));

				Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
						(patt[1]),this.interpretFormula(patt[2])));
				continue;
			}
		}
		logger.fine("end analyzing text");
	}

	public String clean(String text) {
		String newtext;
		newtext=text.replaceAll("\\s", "");
		newtext=newtext.replaceAll("\r\n|[\n\r\u2028\u2029\u0085]", "");
		newtext=newtext.replaceAll("\\t", "");
		return newtext;
	}

	public String[] clean(String[] text) {
		String[] newtext=new String[text.length];
		for(int i=0;i<text.length;i++) {
			newtext[i]=clean(text[i]);
			//System.out.println(newtext[i]);
		}
		return newtext;
	}

	public PGDouble execTextAsLiteralOrSymbol(String text) {
		Double d=null;
		try {
			d=Double.parseDouble(text);
		}catch(NumberFormatException e) {
			return new PGSymbol(text);
		}
		return new PGLiteral(d);
	}

	public PGDouble interpretFormula(String text) {
		if(text.startsWith("+")) {
			text="0"+text;
		}else if(text.startsWith("-")) {
			text="0"+text;
		}
		return interpretFormula(text,0);
	}

	public PGDouble interpretFormula(String text,int operator) {
		if(operator>=operators.size()) {
			return this.execTextAsLiteralOrSymbol(text);
		}
		String[] keys=operators.keySet().toArray(new String[0]);
		if(!text.contains(keys[operator])) {
			return interpretFormula(text,operator+1);
		}

		int j=text.indexOf(keys[operator]);
		String patt1=text.substring(0, j);
		String patt2=text.substring(j+1);

		Class[] values=operators.values().toArray(new Class[0]);
		PGFunction pgf = null;

		try {
			pgf=(PGFunction) values[operator].newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		pgf.setParams(new PGDouble[]{interpretFormula(patt1,operator+1),interpretFormula(patt2,operator)});
		return pgf;
	}

	public boolean containsOperator(String text,int operator) {
		String[] keys=operators.keySet().toArray(new String[0]);
		for(int i=operator;i<operators.size();i++) {

			if(text.contains(keys[i])) {
				return true;
			}
		}
		return false;
	}
}
