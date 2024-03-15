package csen1002.main.task3;

import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class FallbackDfa {

	/**
	 * Constructs a Fallback DFA
	 * 
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	String[] inputStates,alphas,TransitionStates,goalState,startState;
	public FallbackDfa(String fdfa) {
		// TODO Auto-generated constructor stub
		String[] splitInput = fdfa.split("#");
		inputStates = splitInput[0].split(";");
		alphas = splitInput[1].split(";");
		TransitionStates = splitInput[2].split(";");
		startState = splitInput[3].split(";");
		goalState=splitInput[4].split(";");
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 * 
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {
		// TODO Auto-generated method stub
		String res="";
		String inputTemp="";
		int R=0;
		int L=0;
		while(inputTemp.length()!=input.length()) {
			String initialState=String.valueOf(startState[0]);
			Stack<String> stack = new Stack<>();
			for (int i = R; i < input.length(); i++) {
				String str = input.charAt(i) + "";
				for (String ts : TransitionStates) {
					String[] val = ts.split(",");
					if (val[0].equals(initialState) && str.equals(val[1])) {
						initialState = val[2];
						stack.push(val[2]);
						L++;
						break;
					}
				}
			}
			String top = stack.peek();
			int tmpL=L;
			boolean foundGoal = false;
			while ( foundGoal==false ) {
				String curr = stack.pop();
				L--;
				for (int i = 0; i < goalState.length; i++) {
					if (curr.equals(goalState[i])) {
						foundGoal = true;
						res += input.substring(R, ++L ) + "," + curr + ";";
						inputTemp+=input.substring(R, L );
						R = L;
						break;
					}
				}
				if(stack.isEmpty() && foundGoal ==false ){
					res += input.substring(R, tmpL ) + "," + top + ";";
					inputTemp+=input.substring(R, tmpL );
					break;
				}

			}
			stack.clear();
		}
		res=res.substring(0, res.length() - 1);
	//	System.out.println("final res : "+res);
		return res;
	}

//	public static void main(String[]args){
//		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3#a;b#0,a,0;0,b,1;1,a,2;1,b,1;2,a,0;2,b,3;3,a,3;3,b,3#0#1;2");
//		fallbackDfa.run("aa");
//	}
	
}
