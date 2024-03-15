package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class CfgEpsUnitElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */

	String variables,terminals;
	HashMap<String, HashSet<String>> rules;
	String res1="";
	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		String[] splitInput = cfg.split("#");
		variables = splitInput[0];
		terminals = splitInput[1];
		String[] rule = splitInput[2].split(";");
		rules = new HashMap<>();
		for (String a_rule: rule){
			String theKey=a_rule.split("/")[0];
			String[] ss=a_rule.split("/")[1].split(",");
			HashSet<String> RHS=new HashSet<>(List.of(ss));
		//	RHS.addAll(List.of(ss));
//			for(String s: ss){
//				RHS.add(s);
//			}
			rules.put(theKey,RHS);
		}
		//System.out.println(rules);
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return res1;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
		HashMap<String, HashSet<String>> new_rules=rules;
		HashMap<String, HashSet<String>> old_rules=new HashMap<>();
		while (!old_rules.equals(new_rules)) {
			old_rules=(HashMap<String, HashSet<String>>) new_rules.clone();
			for (Map.Entry<String, HashSet<String>> set : rules.entrySet()) {
				if (set.getValue().contains("e")) {
					for (Map.Entry<String, HashSet<String>> set2 : rules.entrySet()) {
						HashSet<String> curr_rule = helper(set2.getValue(), set.getKey());
						new_rules.put(set2.getKey(), curr_rule);
					}
					//new_rules.get(set.getKey()).remove("e");
				}
			}
		//	System.out.println(new_rules);
		}

		//System.out.println(new_rules);
		for (Map.Entry<String, HashSet<String>> set : new_rules.entrySet()) {
			HashSet<String> RHS=new HashSet<>();
			if (set.getValue().contains("e")) {
				RHS.addAll(set.getValue());
				RHS.remove("e");
			}else {
				RHS.addAll(set.getValue());
			}
			new_rules.put(set.getKey(),RHS);
		}
		//System.out.println(new_rules);

		res1=StringHelper(new_rules);
		//System.out.println(res2);
	}

	public static HashSet<String> helper(HashSet<String> new_rule,String var) {
		//System.out.println(new_rule);
		HashSet<String> old_rule = new HashSet<>();
		while (!old_rule.equals(new_rule)) {
			old_rule = (HashSet<String>) new_rule.clone();
			for (String str : old_rule) {
				new_rule.add(str);
				if (str.contains(var)) {
					for (int i = 0; i < str.length(); i++) {
						if (var.equals(str.charAt(i) + "")) {
							String val;
							if (i == 0) {
								val=str.substring(i + 1 );
							} else if (i == str.length() - 1) {
								val=str.substring(0, i);
							} else {
								val = str.substring(0, i) + str.substring(i + 1);
							}
							if(val.equals(""))
								new_rule.add("e");
							else
								new_rule.add(val);
						}
					}
				}
			}
		}
//		System.out.println(old_rule);
		return old_rule;
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
		HashMap<String, HashSet<String>> rules2=new HashMap<>();
		HashMap<String, HashSet<String>> old_rules=rules;
		while (!rules2.equals(old_rules)) {
			rules2=(HashMap<String, HashSet<String>>) old_rules.clone();
			for (Map.Entry<String, HashSet<String>> set : old_rules.entrySet()) {
				//loop over hashset/RHS of rule if it contains a single variable if equal to key remove else sub
				HashSet<String> tmp = new HashSet<>();
				for (String x : set.getValue()) {
					tmp.add(x);
					if (variables.contains(x)) {
						if (x.equals(set.getKey())) {
							//remove it
							tmp.remove(x);
						} else {
							//substitute with hashset of that key
							tmp.remove(x);
							tmp.addAll(rules.get(x));
						}
					}
				}
				old_rules.put(set.getKey(), tmp);
			}
		}


		//String res=StringHelper(rules2);
		//System.out.println(res);
		res1=StringHelper(rules2);


	}


	public String StringHelper(HashMap<String, HashSet<String>> rules2){
		String res="";
		for (int i=0;i<variables.split(";").length;i++){
			ArrayList<String> A_set= new ArrayList<>(rules2.get(variables.split(";")[i]));
			Collections.sort(A_set);
			res+=variables.split(";")[i]+"/";
			for (String r: A_set){
				res+=r+",";
			}
			res=res.substring(0, res.length() - 1);
			res+=";";

		}
		res=res.substring(0, res.length() - 1);
		res=variables+"#"+terminals+"#"+res;
		return res;
	}



}
