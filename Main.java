package video_poker;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Macros
		
		//Class variables
		Deck deck = new Deck();
		Hand hand;
		int bet = 0;
		Scanner reader = new Scanner(System.in);
		char input;
		
		//At the beggining we need to shuffle the deck
		deck.shuffle();
		
		//now we create the initial player's hand and the 5 replacement cards,
		//that will be used to replace the cards chosen by the player.
		hand = new Hand(deck);
		
		//Starting the interface with the player
		System.out.println("What will you do?");
		System.out.println("[b] bet");
		System.out.println("[$] credit");
		System.out.println("[d] deal");
		System.out.println("[h] hold");
		System.out.println("[a] advice");
		System.out.println("[s] statistics");
		
		while(true){ 
			input = reader.next().toLowerCase().charAt(0);
			if(input == 'b' || input == '$' || input == 'd' || input == 'h' || input == 'a' || input == 's'){
				System.out.println(input);
				reader.close();
				break;
			}else{
				System.out.println("Invalid command. Please choose from the list above.");
			}
		}

		
		
		
	}

}
