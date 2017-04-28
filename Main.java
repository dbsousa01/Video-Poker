package video_poker;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Macros
		int hasQuit = 3;
		int maxBet = 5;
		
		//Auxiliary variables
		int previousBet = maxBet;
		int[] toDiscard;
		int toHold;
		
		//Class variables
		Deck deck = new Deck();
		Hand hand = new Hand(deck);
		int bet = maxBet;
		Scanner reader = new Scanner(System.in);
		String[] userInput;
		char input;
		int credit = 0;
		int state = 0; //to track the state of the game
		
		//Reading the args to see if the program was initialized correctly
		//Initializing the credit variable with the amount requested by the player
		if(args.length != 1){
			System.out.println("Not enough arguments\nUsage: -i"); //Falta uma cena antes do -i... não sei correr programas T-T
			System.exit(-1);
		}else{
			credit = Integer.parseInt(args[0]);
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
				hand = new Hand(deck);	
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
				System.out.println("Invalid command, please choose from the list.");
				continue;
			}
			

			//After getting input, we process it
			switch(input){
				case 'b':
					//??TO DO: what happens when you try to bet more than your credit?
					if(state == 0){
						//Change the value of the bet. if there is no value, the bet keeps its previous value.
						if(userInput.length > 2){
							System.out.println("Please, chose only one value to bet: [b i]");
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
									System.out.println("Not enough credits.");
									break;
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
						System.out.println("You can't deal cards right now");
					}
					break;
					
				case 'h':
					if(state == 2){
						//Creating the toDiscard array that will be used for the "hold" function
						toDiscard = new int[hand.length()];
						Arrays.fill(toDiscard, 1);
						
						if(userInput.length > 6){
							System.out.println("How many cards are you trying to replace? You can only choose 5 cards (max).");
							break;
						}else{
							for(String aux: userInput){
								System.out.println(aux);
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
								break;
							}
							
						}
						
					}else{
						System.out.println("You haven't even seen your hand yet... How can you know what to hold?");
						break;
					}
					
					for(int i = 0; i < toDiscard.length; i++){
						if(toDiscard[i] == 1){
							hand.replace(i);
						}
					}
					
					//??Insert comparisons to check if player won anything.
					
					System.out.println(hand);
					state = 0;
					break;
					
				case 'a':
					
					break;
					
				case 's':
					
					break;
					
				case 'q':
					//??Have to change so it's only possible to quit when state = 0
					state = hasQuit;
					System.out.println("Thank you for playing!");
					break;
					
				default:
					System.out.println("Invalid command. Please choose from the list:");
			}
		}
		//We need to close the reader, or it won't stop reading.
		//reader.close();;
		
	}

}
