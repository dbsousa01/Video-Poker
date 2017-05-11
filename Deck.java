package group18;

import java.util.Random;
/**
 * Class that implements a Deck, it has the number of suits on a deck
 * and the number of cards in each suit.
 * The Deck class is an array of cards with 52 positions(the size of a deck).
 */
public class Deck {
	
	//Macros
	int numCards = 13;
	int suitAmount = 4;
	
	//Class variables
	private Card[] cards = new Card[numCards*suitAmount];
	private int cardsOnDeck = 0;
	
	/**
	 * Constructor that initiates the deck, the 52 different cards
	 */
	public Deck(){
		for(int i = 0; i < numCards; i++){
			for(int j = 0; j < suitAmount; j++){
				this.cards[cardsOnDeck] = new Card(i, j);
				cardsOnDeck++;
			}
		}
	}
	
	
	/**
	 * Method that shuffles the deck based on two random numbers that
	 * get a random position of the vector deck and change the position.
	 * To ensure randomness, this process is repeated several times.
	 */
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
	
	/**
	 * Method that draws a card from the top of the deck array.
	 * @return the top card of the array
	 */
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
