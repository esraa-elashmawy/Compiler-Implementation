package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class CfgFirstFollow {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	String terminals,variables;
	HashMap<String,  ArrayList<String>> rules;
	HashMap<String, HashSet<String>> all_first= new HashMap<>();
	HashMap<String, HashSet<String>> all_follow= new HashMap<>();

	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		String[] splitInput = cfg.split("#");
		variables = splitInput[0];
		terminals = splitInput[1];
		String[] rule = splitInput[2].split(";");
		rules = new LinkedHashMap<>();
		for (String a_rule: rule){
			String theKey=a_rule.split("/")[0];
			String[] ss=a_rule.split("/")[1].split(",");
			ArrayList<String> RHS=new ArrayList<>(List.of(ss));
			rules.put(theKey,RHS);
			all_first.put(theKey,null);
			all_follow.put(theKey,null);
		}
		//	System.out.println(rules);
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		HashMap<String, HashSet<String>> first_old=new HashMap<>();
		while (!first_old.equals(all_first)){
			first_old= (HashMap<String, HashSet<String>>) all_first.clone();

			for (Map.Entry<String, ArrayList<String>> set : rules.entrySet()) {
				for (String str : set.getValue()) {
				//	System.out.println("key : " + set.getKey() + " vs " + str);
					for (int i = 0; i < str.length(); i++) {
						    int count_e=0;
						HashSet<String> tmp = new HashSet<>();
						if (str.charAt(i) == 'e' || terminals.contains(str.charAt(i) + "")) {
							if (all_first.get(set.getKey()) != null)
									tmp.addAll(all_first.get(set.getKey()));
								tmp.add(str.charAt(i) + "");
								all_first.put(set.getKey(), tmp);
								break;
							} else {
							    if (all_first.get(str.charAt(i) + "") != null)
									tmp.addAll(all_first.get(str.charAt(i) + ""));
								if(!tmp.contains("e")){
									if (all_first.get(set.getKey()) != null)
										tmp.addAll(all_first.get(set.getKey()));
									all_first.put(set.getKey(), tmp);
									break;
								}else {
									tmp.remove("e");
									count_e++;
								}
								if (all_first.get(set.getKey()) != null)
									tmp.addAll(all_first.get(set.getKey()));
								all_first.put(set.getKey(), tmp);
							}
						if(count_e == str.length()){
							//HashSet<String> tmp = new HashSet<>();
							if (all_first.get(set.getKey()) != null)
								tmp.addAll(all_first.get(set.getKey()));
							tmp.add("e");
							all_first.put(set.getKey(), tmp);
						}
					}
				}
//				System.out.println(all_first);
//				System.out.println();
//				System.out.println();
			}
		}
		return StringHelper(all_first);
	}
	public String StringHelper(HashMap<String, HashSet<String>> rules2){
		StringBuilder res= new StringBuilder();
		for (int i=0;i<variables.split(";").length;i++){
			ArrayList<String> A_set= new ArrayList<>(rules2.get(variables.split(";")[i]));
			Collections.sort(A_set);
			res.append(variables.split(";")[i]).append("/");
			for (String r: A_set){
				res.append(r);
			}
			res.append(";");
		}
		res = new StringBuilder(res.substring(0, res.length() - 1));
		return res.toString();
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		first();
		String[] var=variables.split(";");
		HashMap<String, HashSet<String>> follow_old=new HashMap<>();
		while (!follow_old.equals(all_follow)) {
			follow_old = (HashMap<String, HashSet<String>>) all_follow.clone();
			for (int v = 0; v < var.length; v++) {
			//	System.out.println("variable: " + var[v]);
				HashSet<String> follow = new HashSet<>();
				for (Map.Entry<String, ArrayList<String>> set : rules.entrySet()) {
				//	System.out.println(set.getKey() + "  " + set.getValue());
					for (String str : set.getValue()) {
						if (str.contains(var[v])) {
							int idx = str.indexOf(var[v]);
							while (idx >= 0) {
								//int idx = str.indexOf(var[v]);
							//	System.out.println(str + " " + idx + " " + (str.length() - 1) + "  " + all_follow.get(set.getKey()));
								int j=idx;
								while (j<str.length()) {
								 if (j == (str.length() - 1)) {
									//last element-> follow key
									if (all_follow.get(set.getKey()) != null)
										follow.addAll(all_follow.get(set.getKey()));
									break;
								 } else {
									// get first of idx+1 -e
										if (terminals.contains(str.charAt(j + 1) + "")) {
											follow.add(str.charAt(j + 1) + "");
											break;
										}else { //is idx+1 is variable
											HashSet<String> tmp= all_first.get(str.charAt(j + 1) + "");
										//	System.out.println(tmp+"-------"+str.charAt(j+1)+"-------jere");
											follow.addAll(tmp);
											if(!tmp.contains("e"))
												break;
										}
										follow.remove("e");
										j++;
									}
								}
								idx = str.indexOf(var[v], idx + 1);
							}
						}
					}
				//	System.out.println(follow);
				//	System.out.println();
				}
				if (var[v].equals("S"))
					follow.add("$");
				all_follow.put(var[v], follow);
			//	System.out.println(all_follow);
			}
			//System.out.println(all_follow);
		}
		return StringHelper(all_follow);
	}

		public static void main(String [] args){

	}

}
