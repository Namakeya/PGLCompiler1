package main;

import java.util.logging.Logger;

import objects.basic.PGLiteral;
import objects.basic.PGObject;
import objects.basic.PGRanged;
import objects.basic.PGString;
import objects.function.FunctionEquals;
import rules.RuleBigger;
import rules.RuleFEquals;
import rules.RuleIs;
import rules.RuleSEquals;
import rules.RuleSmaller;

public class TextAnalyzer {

	public static Logger logger=Main.logger;

	public void analyze(String text) {
		text=text.replaceAll("\r\n|[\n\r\u2028\u2029\u0085]", "");
		String[] secs=text.split("&");
		for(String sec:secs) {
			logger.fine("..."+sec);
			String[] patt=sec.split(" is ");
			if(patt.length>1) {
				patt=clean(patt);

				Main.ruleManager.addRules(new RuleIs(PGObject.getOrCreateFromFullpath(patt[0]),patt[1]));
				continue;
			}
			patt=sec.split("=");
			if(patt.length>1) {
				patt=clean(patt);
				if(patt[1].startsWith("\"") && patt[1].endsWith("\"")) {
					String str=patt[1].replace("\"", "");
					Main.ruleManager.addRules(new RuleSEquals(PGString.getOrCreateFromFullpath(patt[0]),str));
				}else {
					try {

						Main.ruleManager.addRules(new RuleFEquals
								(FunctionEquals.getOrCreateFromFullpath(patt[0])
										,new PGLiteral(Double.parseDouble(patt[1]))));
						//System.out.println("number");
					}catch(NumberFormatException e) {
						//System.out.println("not number");
						Main.ruleManager.addRules(new RuleFEquals
								(FunctionEquals.getOrCreateFromFullpath(patt[0])
										,(FunctionEquals.getOrCreateFromFullpath(patt[1]))));
					}
				}
				continue;
			}
			patt=sec.split(">");
			if(patt.length>1) {
				patt=clean(patt);

				try {

					Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
							(patt[0]),new PGLiteral(Double.parseDouble(patt[1]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
							(patt[0]),FunctionEquals.getOrCreateFromFullpath(patt[1])));
				}
				continue;
			}
			patt=sec.split("<");
			if(patt.length==2) {
				patt=clean(patt);

				try {
					Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
							(patt[0]),new PGLiteral(Double.parseDouble(patt[1]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
							(patt[0]),FunctionEquals.getOrCreateFromFullpath(patt[1])));
				}
				continue;
			}else if(patt.length==3) {
				patt=clean(patt);

				try {

					Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
							(patt[1]),new PGLiteral(Double.parseDouble(patt[0]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleBigger(PGRanged.getOrCreateFromFullpath
							(patt[1]),FunctionEquals.getOrCreateFromFullpath(patt[0])));
				}
				try {
					Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
							(patt[1]),new PGLiteral(Double.parseDouble(patt[2]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleSmaller(PGRanged.getOrCreateFromFullpath
							(patt[1]),FunctionEquals.getOrCreateFromFullpath(patt[2])));
				}
				continue;
			}
		}
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

}
