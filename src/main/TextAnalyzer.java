package main;

import rules.RuleBigger;
import rules.RuleEquals;
import rules.RuleIs;
import rules.RuleSmaller;

public class TextAnalyzer {

	public void analyze(String text) {
		String[] secs=text.split("&");
		for(String sec:secs) {
			String[] patt=text.split(" is ");
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
				break;
			}
			patt=text.split("=");
			if(patt.length>1) {
				patt=clean(patt);
				
				Main.ruleManager.addRules(new RuleEquals(patt[0],patt[1]));
				break;
			}
			patt=text.split(">");
			if(patt.length>1) {
				patt=clean(patt);
				
				Main.ruleManager.addRules(new RuleBigger(patt[0],patt[1]));
				break;
			}
			patt=text.split("<");
			if(patt.length>1) {
				patt=clean(patt);
				
				Main.ruleManager.addRules(new RuleSmaller(patt[0],patt[1]));
				break;
			}
		}
	}
	
	public String clean(String text) {
		String newtext;
		newtext=text.replace("\\s", "");
		newtext=newtext.replace("\\n", "");
		newtext=newtext.replace("\\t", "");
		return newtext;
	}
	
	public String[] clean(String[] text) {
		String[] newtext=new String[text.length];
		for(int i=0;i<text.length;i++) {
			newtext[i]=clean(text[i]);
		}
		return newtext;
	}
	
}
