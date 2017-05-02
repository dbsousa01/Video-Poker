package group18;

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
	
	//Variables
	int bet = maxBet;
	char input;
	String type_hand = null;
	int credit=0;
	int state = 0;
	String[] userInput;
	
	
	Scanner reader = new Scanner(System.in);
	
	public GameMode(String[] args) {
		if(args.length < 2){
			System.out.println("Not enough arguments");
			System.exit(0);
		}
		try{
			credit = Integer.parseInt(args[1]);
		}catch(NumberFormatException ex){
			System.out.println("Wrong input, exiting");
			System.exit(1);
		}
	}
	
	
	public void Show_credit(){
		System.out.println("Your credit is " + credit);
	}
	
	public void bet(int amount){
		if(bet <= credit){
			previousBet = bet;
		}else{
			System.out.println("Not enough credits. Going ALL IN!");
			bet = credit;
		}
		
		credit -= bet;
		System.out.println("You bet " + bet);
	}
	
	//method that checks if the user can bet and bets it if it is the case
	public void bet(){
		if(state == 0){
			//Change the value of the bet. if there is no value, the bet keeps its previous value.
			if(userInput.length > 2){
				System.out.println("Please, choose only one value to bet: [b i]\n Try Again");
				return;
			}else if(userInput.length == 2){
				bet = Integer.parseInt(userInput[1]);
				if(bet < 1 || bet > 5){
					System.out.println("Invalid bet, please choose a proper value [1 - 5].");
					bet = previousBet;
					return;
				}
			}

			bet(bet);
			
			state = 1;
		}else{
			System.out.println("b: illegal command.");
		}
	}

	
	public void runner(String[] args, Score score){
	
	}
}
