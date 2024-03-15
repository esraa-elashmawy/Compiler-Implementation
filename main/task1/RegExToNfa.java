package csen1002.main.task1;

import java.util.Stack;


/**
 * Write your info here
 * 
 * @name Esraa Ahmed
 * @id 46-1666
 * @labNumber 21
 */

public class RegExToNfa {
	int startState;
	int endState;
	String transitions;
	String numbers="";
	String Final;

	public RegExToNfa(int startState,int endState,String transitions){
		this.startState=startState;
		this.endState=endState;
		this.transitions=transitions;
	}

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	public RegExToNfa(String input) {
		// TODO Auto-generated constructor stub
		String[] splitInput = input.split("#");
		Stack<RegExToNfa> nodes = new Stack<>();
		String R=splitInput[1];
		int stateCounter=-1;
		for (int i = 0; i < R.length(); i++) {
			if(splitInput[0].contains(""+R.charAt(i)) || R.charAt(i)=='e'){ // a character
				int start=++stateCounter;
				int end=++stateCounter;
				RegExToNfa state=new RegExToNfa(start,end, start+","+R.charAt(i)+","+end+";");
				//System.out.println(state.transitions);
				numbers+=start+";"+end+";";
				nodes.push(state);
			}
			if(R.charAt(i)=='*'){
				RegExToNfa curr=nodes.pop();
				int beforeStart=curr.startState;
				int beforeEnd=curr.endState;
				curr.startState=++stateCounter;
				curr.endState=++stateCounter;
				curr.transitions= curr.transitions+
						beforeEnd+",e,"+beforeStart+";"+
						beforeEnd+",e,"+curr.endState+";"+
						curr.startState+",e,"+beforeStart+";"+
						curr.startState+",e,"+curr.endState+";";
			//	System.out.println(curr.transitions);
				numbers+=curr.startState+";"+curr.endState+";";
				nodes.push(curr);
			}
			if(R.charAt(i)=='|'){
				RegExToNfa op2=nodes.pop();
				RegExToNfa op1=nodes.pop();
				int start=++stateCounter;
				int end=++stateCounter;
				op1.transitions=op1.transitions+op2.transitions+ start+",e,"+op1.startState+";"+
						start+",e,"+op2.startState+";"+op1.endState+",e,"+end+";"+op2.endState+",e,"+end+";";
				op1.startState=start;
				op1.endState=end;
				numbers+=start+";"+end+";";
			//	System.out.println(op1.transitions);
				nodes.push(op1);
			}
			if(R.charAt(i)=='.'){
				RegExToNfa op2=nodes.pop();
				RegExToNfa op1=nodes.pop();

				// remove from op2 transitions anything starting with op2.startState 8,e,4
				int index = op2.transitions.indexOf(op2.startState+"");
				//System.out.println("op2: "+op2.transitions+index+"  "+op2.transitions.charAt(index)+" start state:"+op2.startState);
				String[] splitTrans = op2.transitions.split(";");
				String newTransition="";
				for(String node: splitTrans){
					String[] tmp = node.split(",");
					if(tmp[0].equals(op2.startState+"")){
						newTransition+=op1.endState+","+tmp[1]+","+tmp[2]+";";
					}else{
						newTransition+=node+";";
					}
				}

				numbers= numbers.replaceAll(op2.startState+";","");
//				String regex= op2.startState+",\\w,\\d;";
//				op2.transitions=op2.transitions.replaceAll( regex, "");
//				String regex2= op2.startState+",\\w,\\d;";
//				newTransition=newTransition.replaceAll( regex2, "");

				RegExToNfa res= new RegExToNfa(op1.startState,op2.endState, op1.transitions+newTransition);
				nodes.push(res);
			}
		}
		// finished looping over expression...pop the remaining state in stack
		RegExToNfa res= nodes.pop();
		numbers=numbers.substring(0, numbers.length() - 1);
		// sort transitions
		String[] splitNo = res.transitions.split(";");
		String sortedTransition="";
		for(int i=0;i<res.endState;i++){
			for(int j=0; j<splitNo.length;j++){
				String[] tmp = splitNo[j].split(",");
				if(tmp[0].equals(i+"")){
					sortedTransition+=splitNo[j]+";";
				}
			}
		}
		sortedTransition=sortedTransition.substring(0, sortedTransition.length() - 1);
		Final=numbers+"#"+splitInput[0]+"#"+sortedTransition+"#"+res.startState+"#"+res.endState;

//		System.out.println("res: "+res.transitions);
//		System.out.println("res2: "+sortedTransition);
//		System.out.println(numbers);
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method tub
		return Final;
	}

}