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
	
	
	public boolean isPair(){
		
		for(int i = 0; i < this.handSize-1; i++){
			for(int j = i; j < this.handSize-1; i++){
				if(this.cards[i].getValue() == 0 || this.cards[i].getValue() > 9){
					if(this.cards[i].equals(this.cards[j]) ){
						return true;
					}else{
						continue;
					}
				}
			}
		}
		
		return false;
	}
	
	
	//Method to create a rigged hand
	public void rigHand(int[] vals, int[] suits){
		
		for(int i = 0; i < handSize; i++){
			this.cards[i] = new Card(vals[i], suits[i]);
		}
	}
	
	public int isCombination(){
		
		//Check if the hand has a straight
		
		
		//Check if the hand has a pair
		for(int i = 0; i < this.handSize-2; i++){
			for(int j = i + 1; j < this.handSize-1; j++){
				if(this.cards[i].getValue() == 0 || this.cards[i].getValue() > 9){
					if(this.cards[i].compareTo(this.cards[j]) == 0){
						return 1;
					}
				}
			}
		}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
}
