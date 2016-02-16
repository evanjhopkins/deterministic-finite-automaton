public class ManWolf {

	//moves
	private static final int MAN = 0;     //n
	private static final int GOAT = 1;    //g
	private static final int WOLF = 2;    //w
	private static final int CABBAGE = 3; //c
	//states
	private static final int Q0 = 0;
	private static final int Q1 = 1;
	private static final int Q2 = 2;
	private static final int Q3 = 3; 
	private static final int Q4 = 4; 
	private static final int Q5 = 5;
	private static final int Q6 = 6;
	private static final int Q7 = 7;
	private static final int Q8 = 8;
	private static final int Q9 = 9;
	private static final int Q10 = 10;//error
	//transitions from a given origin
	private static final int[][] delta = {
      //n   g   w   c   Origin
      {Q10, Q1, Q10, Q10}, //0 : q0
      {Q2, Q0, Q10, Q10}, //1 : q1
      {Q1, Q10, Q3, Q4}, //2 : q2
      {Q10, Q5, Q2, Q10}, //3 : q3
      {Q10, Q6, Q10, Q2}, //4 : q4
      {Q10, Q3, Q10, Q7}, //5 : q5
      {Q10, Q4, Q7, Q10}, //6 : q6
      {Q8, Q10, Q6, Q5}, //7 : q7
      {Q7, Q9, Q10, Q10}, //8 : q8
      {Q10, Q8, Q10, Q10}, //9 : q9
      {Q10, Q10, Q10, Q10}  //10 : q10 (error)
    };

    //represents the current state of the solution
    private int state;

	public ManWolf(){
		//no construction needed atm 
	}

	public boolean analyze(String proposedSolution) {
		this.state = Q0; //resetting state to support many evals per instance
		evaluate(proposedSolution);//step through and evaluate the users proposed solution
		return isInFinalState();//return true or false depending if the final state is allowed
	}

	//evalutates the users proposed solution step by step
	private void evaluate(String proposedSolution){
		int move;
		//iterate over the users moves (chars) from their solution
		for(char ch: proposedSolution.toCharArray()){
			//get the entity that is represented by the current move (char)
			move = mapCharToMove(ch);

			try {
				//try to advance the next state
				this.state = delta[this.state][move];
			} catch (ArrayIndexOutOfBoundsException e) {
				//advancement failed, exceptions default the state to the error state
				this.state = Q10;
			}
		}
	}

	//checks whether the current state is a final state
	private boolean isInFinalState() {
		return (this.state == Q9) ? true : false;
	}

	//maps each of the possible inputs in char format to their respected entities
	private int mapCharToMove(char ch){
		switch (ch) {
			case 'n':return MAN; 
			case 'g':return GOAT; 
			case 'w':return WOLF;
			case 'c':return CABBAGE;
		}
		return -1;
	}

}
