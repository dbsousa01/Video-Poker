package group18;

import java.util.Random;

public class Deck {
	
	//Macros
	int numCards = 13;
	int suitAmount = 4;
	
	//Class variables
	private Card[] cards = new Card[numCards*suitAmount];
	private int cardsOnDeck = 0;
	
	
	public Deck(){
		for(int i = 0; i < numCards; i++){
			for(int j = 0; j < suitAmount; j++){
				this.cards[cardsOnDeck] = new Card(i, j);
				cardsOnDeck++;
			}
		}
	}
	
	
	//function that shuffles a deck
	public void shuffle(){
		Random rand1 = new Random(); //we need two seeds to get more randomness
		Random rand2 = new Random();
		int pos1;	//positions of the cards that will be taken from the deck
		int pos2;
		Card temp;
		
		//shuffle the deck by switching the place of 2 random cards 1000 times
		for(int i = 0; i < numCards*suitAmount*100; i++){
			pos1 = rand1.nextInt(numCards*suitAmount);
			pos2 = rand2.nextInt(numCards*suitAmount);
			
			while(true){
				if(pos1 == pos2){
					pos2 = rand2.nextInt(numCards*suitAmount);
				}else{
					break;
				}
			}
			
			temp = this.cards[pos1];
			this.cards[pos1] = this.cards[pos2];
			this.cards[pos2] = temp;
		}
	}
	
	
	public Card drawCard(){
		int top = this.cardsOnDeck - 1;
		
		if(top >= 0){
			this.cardsOnDeck--;
			return this.cards[top];
		}else{
			System.out.println("The deck is empty.");
			System.exit(-1);
		}
		//Because it gave an error message... should never be reached
		return this.cards[0];
	}
	
}
