package csen1002.main.task2;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class NfaToDfa {
	String Final;
	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		// TODO Auto-generated constructor stub
		String[] splitInput = input.split("#");
		String[] inputStates = splitInput[0].split(";");
		String[] TransitionStates = splitInput[2].split(";");
		HashMap<String, HashSet<Integer>> e_closure = new HashMap<>();
		HashMap<String, HashSet<Integer>> old_e_closure = new HashMap<>();
		HashSet<String> alphaTransitions = new HashSet<>();

		for (int i = 0; i < inputStates.length; i++) {
			HashSet<Integer> tmp = new HashSet<>();
			tmp.add(Integer.parseInt(inputStates[i]));
			for (int j = 0; j < TransitionStates.length; j++) {
				if (TransitionStates[j].split(",")[0].equals(inputStates[i])) {
					if (TransitionStates[j].split(",")[1].equals("e")) {
						tmp.add(Integer.parseInt(TransitionStates[j].split(",")[2]));
					} else {
						alphaTransitions.add(TransitionStates[j]);
					}
				}
			}
			e_closure.put(inputStates[i], tmp);
		}


		while (!old_e_closure.equals(e_closure)) {
			old_e_closure = (HashMap) e_closure.clone();
			for (Map.Entry<String, HashSet<Integer>> set : e_closure.entrySet()) {
				HashSet<Integer> tmp = new HashSet<>();
				for (int x : set.getValue()) {
					//must check for duplicates first => hashset doesn't allow for duplicates
					tmp.addAll(e_closure.get(x + ""));
				}
				e_closure.put(set.getKey(), tmp);
			}
		}
		//System.out.println(e_closure);

		String res_Transitions = "";
		String first_state="";
		String allStates="";
		String goalStates="";
		String[] alphas = splitInput[1].split(";");
		Boolean deadStatePresent = false;

		ArrayList<HashSet<Integer>> tmp = new ArrayList<>();
		ArrayList<HashSet<Integer>> storeRemoved = new ArrayList<>();
		tmp.add(e_closure.get(splitInput[3]));

		HashMap<String,HashSet<Integer>> helperTransit=new HashMap<>();
		//ArrayList<HashSet<Integer>> All_goal_states=new ArrayList<>();
		//ArrayList<HashSet<Integer>> All_states=new ArrayList<HashSet<Integer>>();

		while (!tmp.isEmpty() ) {
			HashSet<Integer> state = tmp.remove(0);
			storeRemoved.add(state);
			// can have multiple goal states ===
			String goal_split[]=splitInput[4].split(";") ;
			for(String val: goal_split) {
				if (state.contains(Integer.parseInt(val)) ) {
					goalStates += helper(state) + ";";
					//All_goal_states.add(state);
					break;
				}
			}
			allStates+=helper(state)+";";

			// for loop over state and find if there is a transition
			for (Integer ele : state) {
				for (String j : alphaTransitions) {
					for (String alpha : alphas) {
						if (ele == Integer.parseInt(j.split(",")[0])) {
							if (alpha.equals(j.split(",")[1])) {
								if (helperTransit.get(alpha) == null) {
									helperTransit.put(alpha, (e_closure.get(j.split(",")[2])));
//									System.out.println("help "+helperTransit+"  "+ele);
								}else {
									HashSet<Integer> tmp2= new HashSet<>();
											tmp2.addAll(e_closure.get(j.split(",")[2]));
											tmp2.addAll(helperTransit.get(alpha));
									helperTransit.put(alpha, tmp2 );
									//helperTransit.put(alpha, helperTransit.get(alpha)+ (e_closure.get(j.split(",")[2])) );
								}
							//	System.out.println("help "+helperTransit+"  "+ele+"  "+j+"  "+e_closure.get(j.split(",")[2]));
								//System.out.println(tmp+"  "+e_closure.get(j.split(",")[2]));
							//	System.out.println("help "+helperTransit);
								break;
							}
						}
					}
				}
				first_state += ele + "/";
			}

		//	System.out.println("help "+helperTransit);
			first_state=first_state.substring(0, first_state.length() - 1);

			for (String alpha : alphas) {
				if (!(helperTransit.get(alpha) == null)) {
					res_Transitions += first_state + "," + alpha + "," + helper(helperTransit.get(alpha)) + ";";

					if(!tmp.contains(helperTransit.get(alpha)) && !storeRemoved.contains(helperTransit.get(alpha)))
						tmp.add(helperTransit.get(alpha));
				} else {
					res_Transitions += first_state + "," + alpha + ",-1" + ";";
					deadStatePresent=true;
				}
			}
			first_state="";
			helperTransit.clear();
		}

		if(deadStatePresent==true){
			allStates="-1;"+allStates;

			HashSet<Integer> dead=new HashSet<>();
			dead.add(-1);
			String dead_tmp="";
			for (String alpha : alphas){
				dead_tmp+="-1,"+alpha+",-1;";
			}
			res_Transitions = dead_tmp+res_Transitions;
		}
		//System.out.println("all states sorted: "+sortHelper(allStates));
		allStates=sortHelper(allStates);
		goalStates=sortHelper(goalStates);

//		SORT goal states
//		String [] all_goals_split=goalStates.split(";");
//		Arrays.sort(all_goals_split);
//		goalStates="";
//		for(String ss: all_goals_split){
//			goalStates+=ss+";";
//		}
		//SORT states
		String [] allStates_split=allStates.split(";");
//		Arrays.sort(allStates_split);
//		allStates="";
//		for(String ss: allStates_split){
//			allStates+=ss+";";
//		}


		//SORT transitions
		String sortedTransition="";
		String[] res_Transitions_split= res_Transitions.split(";");

		for(int i=0;i<allStates_split.length;i++){
		//	System.out.println(allStates_split[i]);
			for(int j=0; j<res_Transitions_split.length;j++){
			//	System.out.println(res_Transitions_split[j]);
				String[] arr = res_Transitions_split[j].split(",");
				if(arr[0].equals(allStates_split[i])){
					sortedTransition+=res_Transitions_split[j]+";";
				}
			}
		}

		allStates=allStates.substring(0, allStates.length() - 1);
		sortedTransition=sortedTransition.substring(0, sortedTransition.length() - 1);
		goalStates=goalStates.substring(0, goalStates.length() - 1);

		//res_Transitions=res_Transitions.substring(0, res_Transitions.length() - 1);
		//System.out.println(sortedTransition);
		//System.out.println(allStates);
		//System.out.println(goalStates);
		Final=allStates+"#"+splitInput[1]+"#"+sortedTransition+"#"+helper(e_closure.get(splitInput[3]+""))+"#"+goalStates;
	//	System.out.println(Final);
	}

	public static String sortHelper(String state){

		String [] arr=state.split(";");

		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (sortHelper3(arr[j], arr[j + 1])) {
					// swap arr[j+1] and arr[j]
//					System.out.println(arr[j]+" swap with "+arr[j+1]);
					String temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		String allstr="";
		for(String s:arr){
			allstr+=s+";";
		}

		return allstr;
	}
// is a>b
	public static boolean sortHelper3(String curr1, String curr2){

		String s1[]=curr1.split("/");
		String s2[]=curr2.split("/");
		if(s1[0] ==null || s1[0].equals("")) {
			return false;
		}else if(s2[0]==null || s2[0].equals("")) {
			return true;
		}else {
			if (Integer.parseInt(s1[0]) < Integer.parseInt(s2[0]) ) {
				//System.out.println(s2[0]+" greater than "+s1[0]);
				return false;
			} else if (Integer.parseInt(s1[0]) >Integer.parseInt(s2[0])) {
				//System.out.println(s2[0]+" smaller than "+s1[0]);
				return true;
			} else {
				//System.out.println(s2[0]+" equals "+s1[0]);
				String newArr[] = Arrays.copyOfRange(s1, 1, s1.length);
				String newArr2[] = Arrays.copyOfRange(s2, 1, s2.length);
				String str1="";
				String str2="";
				for(String s:newArr){
					str1+=s+"/";
				}
				for(String s:newArr2){
					str2+=s+"/";
				}
				return  sortHelper3(str1, str2);
			}
		}
	}


	public String helper(HashSet<Integer> state){
		String s="";
		for(Integer val: state){
			s+=val+"/";
		}
		s=s.substring(0, s.length() - 1);
			return s;
	}



	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Final;
	}


}
