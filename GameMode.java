package group18;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Abstract class. It represents the different game modes of the program
 * The abstract class has variables shared by its child classes since they
 * are used in all of them and also implements some methods that are
 * common to these child classes. It has one abstract method, being the 
 * main method that implements the subsequent classes
 */
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
	
	/**
	 * Constructor that takes the input argument and the credit.
	 * @param 
	 */
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
			if(credit == 0 || credit < 0){
				System.out.println("Error: Credit");
				System.exit(0);
			}
		}catch(NumberFormatException ex){
			System.out.println("Wrong input, exiting");
			System.exit(1);
		}
		
		createDeck();
	}
	
	/**
	 * creates a newly Deck. Used ever since a new deck is needed after a normal play.
	 */
	public void createDeck(){
		deck = new Deck();
		deck.shuffle();
		
		hand = new Hand(deck, handSize);
	}
	
	/**
	 * Prints the current player's credit
	 */
	public void Show_credit(){
		System.out.println("player's credit is " + credit);
	}
	
	/**
	 * Method that takes the integer bet from the user and subtracts to the current credit. 
	 * @param amount. Integer that represents the amount of a bet a player wants to make.
	 */
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
		System.out.println("player is betting " + bet);
	}
	
	/**
	 * Method that checks if the user can bet, based on the current state of the game,
	 * and, if betting is allowed, it makes the bet.
	 */
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
					return;
				}
				if(bet < 1 || bet > 5){
					System.out.println(userInput[0] + " Illegal amount.");
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
	
	/**
	 * Method that checks which cards does the player want to keep in his hand and
	 * discards the rest with new cards. Finally it calls a method that checks the 
	 * player's hand to see if the final hand has won anything.
	 * @param score Class that implements the score of the game, checks if the hand is a
	 * winning hand and increments the credit if it is the case.
	 * @param handi Current player's hand, to be compared with the Discard Array to see
	 * which cards should be switched and which ones should be kept in the hand.
	 */
	public void hold(Score score, Hand handi){
		Deck deck = new Deck();
		Hand sorted = new Hand(deck, handi.length());
		if(state == 2){
			//Creating the toDiscard array that will be used for the "hold" function
			toDiscard = new int[handi.length()];
			Arrays.fill(toDiscard, 1);
			
			//The string userInput has the h x x, x being the id of the cards that want to
			//be held, it switches the ones that are not tagged.
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
			System.out.println("h: illegal command");
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
		
		//Creates a local hand so it can be sorted and compared to the various possible
		//hands. A local variable is used to ensure that the original hand is not sorted
		//since in a normal game, the hand would not be sorted.
		sorted.rigHand(new int[]{handi.getCardAt(0).getValue() ,  handi.getCardAt(1).getValue(), handi.getCardAt(2).getValue(), handi.getCardAt(3).getValue(), handi.getCardAt(4).getValue()}, new int[]{handi.getCardAt(0).getSuit() ,  handi.getCardAt(1).getSuit(), handi.getCardAt(2).getSuit(), handi.getCardAt(3).getSuit(), handi.getCardAt(4).getSuit()});
		
		sorted.sort();
		//Checks if the player has won anything with his hand.
		credit = score.result(sorted, bet, credit);
		
		state = 0;
	}
	
	/**
	 * Method that sorts an integer string, from the lowest number to the highest number.
	 * @param s String that needs to be sorted.
	 * @return the same parameter string s but now sorted.
	 */
	public String sortString(String s){
		String[] array = new String[s.length()-1];
		
		for(int i=0;i<array.length;i++){
			array[i] = new String();
		}
		array = s.split("\\s+");
		Arrays.sort(array,1,array.length);
		s = String.join(" ",array);
		return s;
	}
	
	/**
	 * Abstract method that means to be implemented in the child classes.
	 * @param args
	 * @param score
	 */
	public void runner(String[] args, Score score){
	
	}
}
