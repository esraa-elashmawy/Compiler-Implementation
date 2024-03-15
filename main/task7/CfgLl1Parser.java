package csen1002.main.task7;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param //cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	String terminals,variables;
	HashMap<String,  ArrayList<String>> rules;
	HashMap<String, ArrayList<String>> all_first= new HashMap<>();
	HashMap<String, ArrayList<String>> all_follow= new HashMap<>();
	HashMap<String, String> table= new HashMap<>();
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		String[] splitInput = input.split("#");
		variables = splitInput[0];
		terminals = splitInput[1];
		String[] rule = splitInput[2].split(";");
		String[] first = splitInput[3].split(";");
		String[] follow = splitInput[4].split(";");
		rules = new LinkedHashMap<>();
		for (int i=0; i< rule.length;i++){
			String theKey=rule[i].split("/")[0];
			String[] ss=rule[i].split("/")[1].split(",");
			ArrayList<String> RHS=new ArrayList<>(List.of(ss));
			rules.put(theKey,RHS);

			String theKey3=follow[i].split("/")[0];
			String[] ss3=follow[i].split("/")[1].split(",");
		//	System.out.println(ss3[0]);
			ArrayList<String> RHS3=new ArrayList<>(List.of(ss3));
			//System.out.println(RHS3);
			all_follow.put(theKey3,RHS3);

			String theKey2=first[i].split("/")[0];
			String[] ss2=first[i].split("/")[1].split(",");
			ArrayList<String> RHS2=new ArrayList<>(List.of(ss2));
			//System.out.println(RHS2);
			all_first.put(theKey2,RHS2);
			for(int j=0; j< ss2.length; j++){
				for(int k=0; k< ss2[j].length(); k++) {
					if("e".equals(ss2[j].charAt(k)+"")){
						for(int m=0; m< ss3[0].length(); m++){
							String rc = theKey2 + ss3[0].charAt(m);
							table.put(rc, ss[j]);
						}
					}else {
						String rc = theKey2 + ss2[j].charAt(k);
						table.put(rc, ss[j]);
					}
				}
			}


		}
//		System.out.println("rules: "+rules);
//		System.out.println("first: "+all_first);
//		System.out.println("follow: "+all_follow);
//		System.out.println("table: "+table);
//		System.out.println("var: "+variables);




	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		// TODO Auto-generated method stub
		input= input+"$";
		String res="";
		Stack<String> stack = new Stack<>();
		stack.push("$");
		stack.push(variables.charAt(0)+""); //push start symbol
		res+=variables.charAt(0)+";";
		String before = variables.charAt(0)+"";
//		System.out.println(res);
//		System.out.println(stack);
		for(int i=0;i<input.length();){
			if("$".equals(input.charAt(i)+"") && !stack.peek().equals("$")){
				res+="ERROR;";
				break;
			}
//			if(!stack.peek().equals(input.charAt(i)+"") && terminals.contains(stack.peek())){
//				res+="ERROR;";
//				break;
//			}
			if(stack.peek().equals(input.charAt(i)+"")){
				stack.pop();
				i++;
			}else {
				if (table.get(stack.peek() + input.charAt(i)) != null) {
					if(table.get(stack.peek() + input.charAt(i)).equals("e")){
						res += before.replaceFirst(stack.peek(), "") + ";";
						before = before.replaceFirst(stack.peek(), "");
						stack.pop();
					}else {
						String str = table.get(stack.peek() + input.charAt(i));
						res += before.replaceFirst(stack.peek(), str) + ";";
						before = before.replaceFirst(stack.peek(), str);
						stack.pop();
						for (int j = str.length() - 1; j > -1; j--) {
							stack.push(str.charAt(j) + "");
						}
					}
				} else {
					res+="ERROR;";
					break;
				}
			}
		//	System.out.println(stack);
		}
		//System.out.println(res);
		res=res.substring(0, res.length() - 1);
		return res;
	}

	public static void main(String [] args){
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;T#a;c;i#S/iST,e;T/cS,a#S/i,e;T/c,a#S/$ca;T/$ca");
		cfgLl1Parser.parse("iiac");
		String str ="iiac";
		Stack<String> stack = new Stack<>();
		for (int j = str.length()-1; j > -1; j--) {
			stack.push(str.charAt(j) + "");
		}
		System.out.println(stack);
	}

}
