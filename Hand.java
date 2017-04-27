package video_poker;

public class Hand {

	//Macros
	int handSize = 5;
	
	//Class variables
	Card[] cards = new Card[handSize];
	Card[] replace = new Card[handSize];
	
	public Hand(Deck deck){
		for(int i = 0; i < handSize; i++){
			this.cards[i] = deck.drawCard();
			this.replace[i] = deck.drawCard();
		}
	}
	
	
	@Override
	public String toString() {
		String aux = "";
		
		for(int i = 0; i < handSize; i++){
			aux = aux + cards[i] + " ";
		}
		
		return aux;
	}
	
	
	
	
	
	
	
}
