package video_poker;

public class Main {

	public static void main(String[] args) {

		//Macros
		int handSize = 5;
		
		//Class variables
		Deck deck = new Deck();
		Card[] hand = new Card[handSize*2];
		
		
		//At the beggining we need to shuffle the deck
		deck.shuffle();
		
		//now we create the initial player's hand and the 5 replacement cards,
		//that will be used to replace the cards chosen by the player.
		for(int i = 0; i < handSize*2; i++){
			hand[i] = deck.drawCard();
		}
		
		System.out.print("Hand: [");
		for(int i = 0; i < handSize; i++){
			System.out.print(hand[i] + " ");
		}
		System.out.println("]");

	}

}
