package video_poker;

import java.util.Random;

public class Deck {
	
	//Class variables
	Card[] cards = new Card[32];
	
	//Macros
	int numCards = 13;
	int suitAmmount = 4;
	
	
	public Deck(){
		
		for(int i = 0; i < numCards; i++){
			for(int j = 0; j < suitAmmount; j++){
				this.cards[i] = new Card(i, j);
			}
		}
	}
	
	//function that shuffles a deck
	public void shuffle(){
		Random rand1 = new Random(); //we need two seed to get more randomness
		Random rand2 = new Random();
		int card1;	//positions of the cards that will be taken from the deck
		int card2;
		
		Card temp;
		
		//shuffle the deck by switching the place of 2 random cards 1000 times
		for(int i = 0; i < numCards*suitAmmount*100; i++){
			card1 = rand1.nextInt(52);
			card2 = rand2.nextInt(52);
			
			temp = this.cards[card1];
			this.cards[card1] = this.cards[card2];
			this.cards[card2] = temp;
		}
	}
	
	
}
