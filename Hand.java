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
	
	
	//Method to create a rigged hand
	public void rigHand(int[] vals, int[] suits){
		
		for(int i = 0; i < handSize; i++){
			this.cards[i] = new Card(vals[i], suits[i]);
		}
	}
	
	//Check if the hand has a pair
	private boolean isPair(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < this.handSize-1; i++){
			if(this.cards[i].getValue() == 0 || this.cards[i].getValue() > 9){
				if(this.cards[i].compareTo(this.cards[i+1]) == 0){
					counter ++;
				}
			}
		}
		
		if(counter == 1){
			return true;
		}else{		
			return false;
		}
	}
	
	
	//Check if the hand has two pairs.
	//This will return true if there is a trio in hand, but since the verification for a trio is done first, it's cool
	private boolean isTwoPair(){
		int counter = 0;
		
		for(int i = 0; i < this.handSize-1; i++){
			if(this.cards[i].compareTo(this.cards[i+1]) == 0){
				counter++;
			}
		}
		
		if(counter == 2){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isTrio(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].compareTo(this.cards[i+1]) == 0){
				counter++;
				if(counter == 2){
					return true;
				}
			}else{
				counter = 0;
			}
		}

		return false;
	}
	
	
	//Check if the hand has a straight
	private boolean isStraight(){
		//Auxiliary variables
		int counter = 0;
				
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].getValue() == 12 || this.cards[0].getValue() == 0){ //If the last card is a King and the first is an Ace, it counts for the straight
				counter++;
			}else if(this.cards[i].getValue() + 1 == this.cards[i+1].getValue()){
				counter ++; //If the next card's value is the current card's value + 1, we are 1 card closer to a straight.
			}else{
				counter = 0; //If the next card's value is not the current card's value + 1, we reset the counter.
				//We can make an if(i > 2) to break out, since it's impossible to make a straight then.
			}
		}
		
		if(counter == 4){
			return true;
		}else{
			return false;
		}
	}

	//Check if the hand has a flush
	private boolean isFlush(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].compareSuit(this.cards[i+1])){
				counter++;
			}
		}
		
		if(counter >= 4){
			return true;
		}else{
			return false;
		}
	}
	
	//Check if the hand has a full house
	private boolean isFullHouse(){
		//Auxiliary variables
		int counter = 0;
		
		//This returns true if a four of a kind is found, but since the verification for
		// a 4 of a kind comes first, all is cool
		for(int i = 0; i < this.handSize-1; i++){
			if(this.cards[i].compareTo(this.cards[i+1]) == 0){
				counter ++;
			}
		}
		
		if(counter == 3){
			return true;
		}else{		
			return false;
		}
	
	}
	
	//Check if the hand has 4 of a kind (type 1)
	private boolean isFour1(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].getValue() >= 4 && this.cards[i].getValue() <= 12){
				if(this.cards[i].compareTo(this.cards[i+1]) == 0){
					counter++;
				}else{
					counter = 0;
				}
			}
		}
		
		if(counter == 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Check if the hand has 4 of a kind (type 2)
	private boolean isFour2(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].getValue() >= 1 && this.cards[i].getValue() <= 3){
				if(this.cards[i].compareTo(this.cards[i+1]) == 0){
					counter++;
				}else{
					counter = 0;
				}
			}
		}
		
		if(counter == 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Check if the hand has 4 aces
	private boolean isFourA(){
		//Auxiliary variables
		int counter = 0;
		
		for(int i = 0; i < handSize-1; i++){
			if(this.cards[i].getValue() >= 0){
				if(this.cards[i].compareTo(this.cards[i+1]) == 0){
					counter++;
				}else{
					counter = 0;
				}
			}
		}
		
		if(counter == 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Check if hand has a straight flush
	private boolean isStraightFlush(){
		
		if(this.isStraight()){
			for(int i = 0; i < handSize-2; i++){
				if(!this.cards[i].compareSuit(this.cards[i+1])){
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	public boolean isRoyalFlush(){
		
		if(this.isStraightFlush() && this.cards[1].getValue() == 9){
			return true;
		}
		
		return false;
	}
	
	
	
	public int isCombination(){
		
		if(this.isRoyalFlush()){
			return 11;
		}else if(this.isStraightFlush()){
			return 10;
		}else if(this.isFourA()){
			return 9;
		}else if(this.isFour2()){
			return 8;
		}else if(this.isFour1()){
			return 7;
		}else if(this.isFullHouse()){
			return 6;
		}else if(this.isFlush()){
			return 5;
		}else if(this.isStraight()){
			return 4;
		}else if(this.isTrio()){
			return 3;
		}else if(this.isTwoPair()){
			return 2;
		}else if(this.isPair()){
			return 1;
		}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
}
