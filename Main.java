package video_poker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Macros
		int hasQuit = 3;
		
		//Class variables
		Deck deck = new Deck();
		Hand hand;
		int bet = 5;
		int previousBet = bet;
		Scanner reader = new Scanner(System.in);
		String[] userInput;
		char input;
		int credit = 0; //Check which arg is the credit
		int state = 0; //to track the state of the game
		/************************************
		 * ??(TO BE DISCUSSED WITH GROUP.	*
		 * Particularly statistics)			*
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
		
		//At the beginning we need to shuffle the deck
		deck.shuffle();
		
		//now we create the initial player's hand and the 5 replacement cards,
		//that will be used to replace the cards chosen by the player.
		hand = new Hand(deck);
		
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
		
		System.out.println("What will you do?");
		
		while(state != hasQuit){

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
						//Change the value of the bet. if there is no value, the bet keeps its previews value.
						if(userInput.length > 1){
							bet = Integer.parseInt(userInput[1]);
							if(bet < 1 || bet > 5){
								System.out.println("Invalid bet, please choose a proper value [1 - 5].");
								bet = previousBet;
								break;
							}else{
								previousBet = bet;
								System.out.println(previousBet);
							}
						}
						
						state = 1;
						System.out.println("You bet " + bet);
					}else{
						System.out.println("You can't bet right now.");
					}
					
					break;
					
				case '$':
					System.out.println("Your credit is: " + credit);
					break;
					
				case 'd':
					System.out.println(hand);
					state = 2;
					break;
					
				case 'h':
					
					break;
					
				case 'a':
					
					break;
					
				case 's':
					
					break;
					
				case 'q':
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
