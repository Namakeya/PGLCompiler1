package main;

import java.util.logging.Logger;

import objects.PGDouble;
import objects.PGVariable;
import rules.RuleBigger;
import rules.RuleEquals;
import rules.RuleIs;
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
				switch(patt[1]) {
				case "double":
					patt[1]="Variable_Double";
					break;
				case "int":
					patt[1]="Variable_Integer";
					break;
				case "string":
					patt[1]="Variable_String";
					break;
				}
				Main.ruleManager.addRules(new RuleIs(patt[0],patt[1]));
				continue;
			}
			patt=sec.split("=");
			if(patt.length>1) {
				patt=clean(patt);
				if(patt[1].startsWith("\"") && patt[1].endsWith("\"")) {
					String str=patt[1].replace("\"", "");
					Main.ruleManager.addRules(new RuleEquals(patt[0],new PGVariable<String>("",str)));
				}else {
					try {

						Main.ruleManager.addRules(new RuleEquals(patt[0],new PGDouble("",Double.parseDouble(patt[1]))));
						//System.out.println("number");
					}catch(NumberFormatException e) {
						//System.out.println("not number");
						Main.ruleManager.addRules(new RuleEquals(patt[0],patt[1]));
					}
				}
				continue;
			}
			patt=sec.split(">");
			if(patt.length>1) {
				patt=clean(patt);
				
				try {
					
					Main.ruleManager.addRules(new RuleBigger(patt[0],new PGDouble("",Double.parseDouble(patt[1]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleBigger(patt[0],patt[1]));
				}
				continue;
			}
			patt=sec.split("<");
			if(patt.length==2) {
				patt=clean(patt);
				
				try {
					Main.ruleManager.addRules(new RuleSmaller(patt[0],new PGDouble("",Double.parseDouble(patt[1]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleSmaller(patt[0],patt[1]));
				}
				continue;
			}else if(patt.length==3) {
				patt=clean(patt);
				
				try {
					Main.ruleManager.addRules(new RuleBigger(patt[1],new PGDouble("",Double.parseDouble(patt[0]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleBigger(patt[1],patt[0]));
				}
				try {
					Main.ruleManager.addRules(new RuleSmaller(patt[1],new PGDouble("",Double.parseDouble(patt[2]))));
					//System.out.println("number");
				}catch(NumberFormatException e) {
					//System.out.println("not number");
					Main.ruleManager.addRules(new RuleSmaller(patt[1],patt[2]));
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
