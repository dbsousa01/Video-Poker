package group18;

import java.util.Arrays;
import java.util.Scanner;

public abstract class GameMode {
	//Macros
	int handSize = 5;
	int hasQuit = 3;
	int maxBet = 5;
	
	InteractiveMode mode1;
	DebugMode mode2;
	SimulationMode mode3;
	UiMode mode4;
	
	//Auxiliary variables
	int previousBet = maxBet;
	int[] toDiscard;
	int toHold;
	Deck deck;
	Hand hand;
	
	//Variables
	Integer bet = maxBet;
	char input;
	String type_hand = null;
	Integer credit=0;
	int state = 0;
	String[] userInput;
	
	
	Scanner reader = new Scanner(System.in);
	
	GameMode(String[] args){
		if(args[0].equals("-g") && args.length <=1){ //UIMode
			return;
		}
		else if(args.length < 2){
			System.out.println("Not enough arguments");
			System.exit(0);
		}
		try{
			credit = Integer.parseInt(args[1]);
			if(credit == 0){
				System.out.println("Error, credit is zero.");
				System.exit(0);
			}
		}catch(NumberFormatException ex){
			System.out.println("Wrong input, exiting");
			System.exit(1);
		}
		deck = new Deck();
		deck.shuffle();
		
		hand = new Hand(deck, handSize);
	}
	
	public void Show_credit(){
		System.out.println("Your credit is " + credit);
	}
	
	public void bet(int amount){
		if(bet <= credit){
			previousBet = bet;
		}else if(credit.equals(0)){
			System.out.println("player ran out of credit");
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
				try{
					bet = Integer.parseInt(userInput[1]);
				}catch(NumberFormatException ex){
					System.out.println("Not a valid number");
				}
				if(bet < 1 || bet > 5){
					System.out.println(userInput[0] + " Illegal amount.");
					bet = previousBet;
					return;
				}
			}
			bet(bet);
			
			state = 1;
		}else if(state == 4){
			if(userInput.length > 3){
				System.out.println("Please, choose only one value to bet: [b i]\n Try Again");
				return;
			}else if(userInput.length == 3){
				try{
					bet = Integer.parseInt(userInput[2]);
				}catch(NumberFormatException ex){
					System.out.println("Not a valid number");
				}
				if(bet < 1 || bet > 5){
					System.out.println(userInput[1] + ": Illegal amount.");
					bet = previousBet;
					return;
				}
			}

			bet(bet);
		}else{
			System.out.println("b: illegal command.");
		}
	}
	public void hold(Score score, Hand handi){
		if(state == 2){
			//Creating the toDiscard array that will be used for the "hold" function
			toDiscard = new int[handi.length()];
			Arrays.fill(toDiscard, 1);
			
			for(String aux: userInput){
				if(!aux.equals("h")){
					toHold = Integer.parseInt(aux);
					if(toHold >= 1 && toHold <= 5){
						toDiscard[toHold - 1] = 0;
					}else{
						System.out.println("Invalid value, please choose cards from those in hand [1 - 5].");
						state = 3;
						break;
					}
				}
			}
			
			if(state == 3){
				state = 2;
				return;
			}
		}else{
			System.out.println("h: ilegal command");
			return;
		}
		
		for(int i = 0; i < toDiscard.length; i++){
			if(toDiscard[i] == 1){
				handi.replace(i);
			}
		}
		//hand.sort();

		System.out.println("player's hand " + handi);
		//hand.rigHand(new int[]{10, 11, 12, 9, 0}, new int[]{0, 0, 0, 0, 0});
		handi.sort();
		
		credit = score.result(handi, bet, credit);
		
		state = 0;
	}
	
	public void runner(String[] args, Score score){
	
	}
}
