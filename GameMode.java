package video_poker;

import java.util.Scanner;

public abstract class GameMode {
	//Macros
	int handSize = 5;
	int hasQuit = 3;
	int maxBet = 5;
	
	InteractiveMode mode1;
	DebugMode mode2;
	SimulationMode mode3;
	
	//Auxiliary variables
	int previousBet = maxBet;
	int[] toDiscard;
	int toHold;
	Deck deck = new Deck();
	Hand hand = new Hand(deck, handSize);
	
	int bet = maxBet;
	char input;
	String type_hand = null;
	int credit=0;
	int state = 0;
	
	String[] userInput;
	Scanner reader = new Scanner(System.in);
	
	public GameMode(String[] args) {
		credit = Integer.parseInt(args[1]);
		// TODO Auto-generated constructor stub
	}
	
	public void runner(String[] args,int opt){
		if(opt==1){
			mode1 = new InteractiveMode(args);
			mode1.interactive();
		}
		if(opt==2){
			mode2 = new DebugMode(args);
			mode2.debug();
		}
		if(opt==3){
			mode3 = new SimulationMode(args);
			mode3.simulation();
		}
	}
}