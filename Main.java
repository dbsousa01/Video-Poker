package video_poker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Macros
		
		//Class variables
		Deck deck = new Deck();
		Hand hand;
		int bet = 5;
		Scanner reader = new Scanner(System.in);
		char input;
		int credit = 0; //Check which arg is the credit
		
		//At the beggining we need to shuffle the deck
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
		while(true){
			System.out.println("[b] bet");
			System.out.println("[$] credit");
			System.out.println("[d] deal");
			System.out.println("[h] hold");
			System.out.println("[a] advice");
			System.out.println("[s] statistics");
			System.out.println("[q] quit");
			
			//NEED TO READ MORE THAN 1 ARGUMENT, INPUT NOT READ PROPERLY			
			input = reader.next().toLowerCase().charAt(0);
			

			//After getting input, we process it
			switch(input){
			case 'b':
				//Change the value of the bet. if there is no value, the bet keeps its previews value.
				if(reader.hasNext()){
					while(true){
						bet = reader.nextInt();
						System.out.println(bet);
						if(bet > 5 || bet < 1){
							System.out.println("please choose a valid bet [1 - 5]");
						}else{
							break;
						}
					}
				}
				break;
			case '$':
				System.out.println("Your credit is: " + credit);
				break;
			case 'd':
				System.out.println(hand);
				break;
			case 'h':
				
				break;
			case 'a':
				
				break;
			case 's':
				
				break;
			case 'q':
				break;
			default:
				System.out.println("Invalid command. Please choose from the list:");
			}
		}
		//We need to close the reader, or it won't stop reading.
		//reader.close();;
		
	}

}
