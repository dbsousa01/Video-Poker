package video_poker;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Macros
		int hasQuit = 3;
		int maxBet = 5;
		int handSize = 5;
		
		//Auxiliary variables
		int previousBet = maxBet;
		int[] toDiscard;
		int toHold;
		
		//Class variables
		Deck deck = new Deck();
		Hand hand = new Hand(deck, handSize);
		int bet = maxBet;
		Scanner reader = new Scanner(System.in);
		String[] userInput;
		char input;
		String cmd_file, card_file;
		int credit=0;
		int nbdeals;
		int state = 0; //to track the state of the game

		
		//Reading the arguments to see if the program was initialized correctly
		//Initializing the credit variable with the amount requested by the player
		if(args[0].equals("-i")){
			credit = Integer.parseInt(args[1]);
			//Tirar estes prints todos na versão final
			System.out.println("You chose the interactive mode with "+ credit +" credit");
		}else if(args[0].equals("-d")){
			credit = Integer.parseInt(args[1]);
			cmd_file = args[2];
			card_file = args[3];
			System.out.println("You chose the debug mode. Loading game...");
		}else if(args[0].equals("-s")){
			credit = Integer.parseInt(args[1]);
			bet = Integer.parseInt(args[2]);
			nbdeals = Integer.parseInt(args[3]);
			System.out.println("You chose the simulation mode");
		}else{
			System.out.println("Not enough arguments\nUsage: -i or -d (file) or -s"); //Falta uma cena antes do -i... não sei correr programas T-T
			System.exit(-1);
		}
		/************************************
		 * ??(TO BE DISCUSSED WITH GROUP.	*
		 * Particularly statistics)			*
		 * 									*
		 * Possible states of the game:		*
		 * 0 - You haven't bet yet.			*
		 * 	   possible plays:				*
		 * 		-bet						*
		 * 		-credit						*
		 * 		-quit						*
		 * 		-statistics					*
		 * 									*
		 * 1 - You have bet.				*
		 * 	   possible plays:				*
		 * 		-deal						*
		 * 		-credit						*
		 * 									*
		 * 2 - The cards have been dealt.	*
		 * 	   possible plays:				*
		 * 		-hold						*
		 * 		-credit						*
		 * 		-advice						*
		 * 		-statistics					*
		 ************************************/
		
		
		/********************************************************************************************************
		*Starting the interface with the player.																*
		*b - Place a bet. Cannot be used after the deal or before the end of the dealer’s turn. 				*
		*Use:	b: bet the same as the last bet or 5 tokens, if there is no previews bet;						*
		*		b i: bet i tokens.																				*
		*																										*
		*??$ - Show the amount of tokens the player has??														*
		*																										*
		*d - Deal the cards. Can only be used in the beginning of a round, after the bet.						*
		*																										*
		*h - Holds the cards in the positions chosen by the player. Can only be used after the deal.			*
		*Use:	h i: keeps the card at position i, replacing the rest with random cards from the remaining deck *
		*			(cards are 1 indexed). Can be used to replace multiple cards by writing multiple numbers,	*
		*			separated by space (" ").																	*
		*																										*
		*a - Asks the computer for the "perfect" strategy.														*
		*																										*
		*s - Prints the statistics of the current game.		
		*
		*q - Quit the game													*
		********************************************************************************************************/
		
		
		//The program is a cycle that only ends when the player quits. ??Maybe also end when the player has no credits left.
		while(state != hasQuit){
			
			if(state == 0){
				//For each new play, we need to open a new deck
				//Then we need to shuffle the deck
				deck = new Deck();
				deck.shuffle();
				
				//now we create the initial player's hand and the 5 replacement cards,
				//that will be used to replace the cards chosen by the player.
				hand = new Hand(deck, handSize);
				hand.sort();
			}			
			
			
			System.out.println("What will you do?");
			
			//Showing the player messages to guide him through the game
			System.out.println("[$] credit");
			
			if(state == 0){
				System.out.println("[b] bet");
				System.out.println("[s] statistics");
				System.out.println("[q] quit");
			}
			
			if(state == 1){
				System.out.println("[d] deal");
			}

			if(state == 2){
				System.out.println("[h] hold");
				System.out.println("[a] advice");
			}
			

			//First we read the whole line written by the user
			userInput = reader.nextLine().toLowerCase().split(" ");
			
			//Then we take the first word
			if(userInput[0].length() == 1){
				input = userInput[0].charAt(0);
			}else{
				System.out.println(userInput[0] + ": illegal command, please choose from the list.");
				continue;
			}
			

			//After getting input, we process it
			switch(input){
			case 'b':
				if(state == 0){
					//Change the value of the bet. if there is no value, the bet keeps its previous value.
					if(userInput.length > 2){
						System.out.println("Please, choose only one value to bet: [b i]\n Try Again");
						break;
					}else if(userInput.length == 2){
						bet = Integer.parseInt(userInput[1]);
						if(bet < 1 || bet > 5){
							System.out.println("Invalid bet, please choose a proper value [1 - 5].");
							bet = previousBet;
							break;
						}else{
							if(bet <= credit){
								previousBet = bet;
								System.out.println(previousBet);
							}else{
								System.out.println("Not enough credits. Betting what you have");
								bet = credit;
							}
						}
					}
					credit -= bet;
					state = 1;
					System.out.println("You bet " + bet);
				}else{
					System.out.println("You can't bet right now.");
				}
				break;
				
			case '$':
				System.out.println("Your credit is " + credit);
				break;
				
			case 'd':
				if(state == 1){
					System.out.println(hand);
					state = 2;
				}else{
					System.out.println("d : illegal command");
				}
				break;
				
			case 'h':
				if(state == 2){
					//Creating the toDiscard array that will be used for the "hold" function
					toDiscard = new int[hand.length()];
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
						
						if(state == 3){
							state = 2;
							break;
						}
					}
				}else{
					System.out.println("h: ilegal command");
					break;
				}
				
				for(int i = 0; i < toDiscard.length; i++){
					if(toDiscard[i] == 1){
						hand.replace(i);
					}
				}
				hand.sort();

				System.out.println("player's hand " + hand);
				if(hand.isCombination()){
					//should print the type of hand he won with, not the cards itself
					System.out.println("You won with a " + hand + " and now your credit is " + credit + "!");
				}else{
					System.out.println("You lost and your credit is " + credit + ".");
				}
				state = 0;
				break;
				
			case 'a':
				
				break;
				
			case 's':
				
				break;
				
			case 'q':
				if(state == 0){
					state = hasQuit;
					System.out.println("Thank you for playing!");
				}else{
					System.out.println("You can't quit now. You're locked here with me. :) ");
				}
				break;
				
			default:
				System.out.println(input +": illegal command. Available options:");
			}
		}
		
		//We need to close the reader at the end.
		reader.close();
	}

}
