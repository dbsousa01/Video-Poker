package video_poker;


import java.util.Arrays;

public class Hand {

	
	//Class variables
	private Card[] cards;
	private Card[] replacement;
	int handSize;
	
	
	public Hand(Deck deck, int size){
		this.handSize = size;
		this.cards = new Card[handSize];
		this.replacement = new Card[handSize];
		
		for(int i = 0; i < handSize; i++){
			this.cards[i] = deck.drawCard();
		}
		
		for(int i = 0; i < handSize; i++){
			this.replacement[i] = deck.drawCard();
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
	
	
	public int length(){
		
		return handSize;
	}
	
	
	public void replace(int index){
		
		if(index > handSize){
			System.out.println("Card out of range. The hand only has " + handSize);
		}else{
			cards[index] = replacement[index];
		}
	}
	
	
	public void sort(){
		
		Arrays.sort(this.cards);
	}
	
	
	
	
	public boolean isCombination(){
		
		return false;
	}
	
	
	
	
	
	
	
	
	
}
