package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class CfgLeftRecElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	String terminals;
	LinkedHashMap<String, ArrayList<String>> rules;

	public CfgLeftRecElim(String cfg) {
		// TODO Auto-generated constructor stub
		String[] splitInput = cfg.split("#");
		terminals = splitInput[1];
		String[] rule = splitInput[2].split(";");
		rules = new LinkedHashMap<>();
		for (String a_rule: rule){
			String theKey=a_rule.split("/")[0];
			String[] ss=a_rule.split("/")[1].split(",");
			ArrayList<String> RHS=new ArrayList<>(List.of(ss));
			rules.put(theKey,RHS);
		}
	//	System.out.println(rules);
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String res="";
		for(Object key:rules.keySet().toArray()){
			res+=key+";";
		}
		res=res.substring(0, res.length() - 1);
		res+="#"+terminals+"#";
		for (Map.Entry<String, ArrayList<String>> entry : rules.entrySet()) {
			res+= entry.getKey()+"/";
			for(String str: entry.getValue()){
				res+=str+",";
			}
			res=res.substring(0, res.length() - 1);
			res+=";";
		}
		res=res.substring(0, res.length() - 1);
		//System.out.println(res);

		return res;
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		// TODO Auto-generated method stub
		int counter=rules.size();
		int c2=rules.size();
		Boolean has_non_imm=false;
		LinkedHashMap<String, ArrayList<String>> new_rules = (LinkedHashMap<String, ArrayList<String>>) rules.clone();

		for (Map.Entry<String, ArrayList<String>> entry : rules.entrySet()) {
			ArrayList<String> list = entry.getValue();
			//System.out.println(" ------------------------------------------------");
			//System.out.println(entry.getKey()+" = "+list);
			Iterator<Map.Entry<String, ArrayList<String>> > new_Iterator = rules.entrySet().iterator();

			ArrayList<String> newS= (ArrayList<String>) list.clone();
			ArrayList<String> final1= new ArrayList<>();
			while ( counter<rules.size()  ){
				final1.clear();
				counter++;
				Map.Entry<String, ArrayList<String>> new_Map =  new_Iterator.next();
				//System.out.println("---"+new_Map.getKey() + " = " + new_Map.getValue());
				//System.out.println("non-imm error "+newS);
				for (String str : newS) {
					if(!final1.contains(str))
						final1.add(str);

					if(new_Map.getKey().equals(str.charAt(0)+"")){
						has_non_imm=true;
						final1.remove(str);
						for(String new_str: new_rules.get(new_Map.getKey())){
							final1.add(new_str+ str.substring(1));
						}
					}
				}
				newS= (ArrayList<String>) final1.clone();
//				System.out.println("non-imm "+newS);
//				System.out.println("final  "+final1);
			}
		//	newS=final1;
			if(!has_non_imm ){
				newS.clear();
				newS.addAll(entry.getValue());
			}
			c2--;
			counter=c2;
			new_rules.put(entry.getKey(),newS);
//			System.out.println("after non-imm  "+newS);
//			System.out.println("--> all rules "+ new_rules);

			//remove immediate left recursion//
			boolean has_immediate = false;
			ArrayList<String> S_dash=new ArrayList<>();
			ArrayList<String> newSS=new ArrayList<>();
			for (String str : new_rules.get(entry.getKey())) {
			//	System.out.println("curr str "+str );
				if(entry.getKey().equals(str.charAt(0)+"")){
//					System.out.println("curr str "+str +" the key "+entry.getKey());
					has_immediate=true;
					S_dash.add(str.substring(1)+entry.getKey()+"'");
				}else {
					newSS.add(str + entry.getKey() + "'");
				}
			}
		//	System.out.println(newSS);
			if(has_immediate ){
				S_dash.add("e");
				new_rules.put(entry.getKey(),newSS);
			//	System.out.println(new_rules);
				new_rules.put(entry.getKey()+"'",S_dash);
			}
		//	System.out.println("--> fin rules "+new_rules);
		}
		rules=new_rules;
	}

//	public static void main(String [] args){
//		//CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;T;L#a;b;c;d;i#S/ScT,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
////		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Z;J;O;X#b;k;q;t;z#S/SXbX,z;Z/XJXO,XOz,XzJbO,bOzZ,bZq,q;J/JSkZ,Jb,Oz,XkZb,bZSO,zXZJO;O/OZkJk,SJJ,SJtO,qJJJO,tOzXt;X/OkS,SJXkZ,StZO,XJ,XzJq,kXtXZ");
////		cfgLeftRecElim.eliminateLeftRecursion();
////		cfgLeftRecElim.toString();
//	}

}
