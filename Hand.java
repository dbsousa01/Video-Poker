package group18;

import java.util.Arrays;
/**
 * Class that represents a player's hand. It has two arrays of type hand, both with size 5.
 * One array is the actual hand of the player and the other is the potentially discard
 * hand if the player wishes to do so. This is done to ensure that if the player discards
 * any card, the following ones will be different.
 */
public class Hand {
	
	//Class variables
	Card[] cards;
	Card[] replacement;
	int handSize;
	
	/**
	 * Constructor of the class. Fills the array hand and the possible discard array with
	 * random cards, all different from each other.
	 * @param deck where the 10 cards are drawn from.
	 * @param size the size of the arrays, 5 in this case.
	 */
	public Hand(Deck deck, int size){
		this.handSize = size;
		this.cards = new Card[handSize];
		this.replacement = new Card[handSize];
		
		for(int i = 0; i < handSize; i++){
			this.cards[i] = deck.drawCard();
			this.replacement[i] = deck.drawCard();
		}
	}
	
	/**
	 * Getter that returns all the cards of the player's hand
	 * @return the array of the player's hand
	 */
	public Card[] getCards(){
		return this.cards;
	}
	
	/**
	 * Getter that gets a card from the player's hand on a specific index
	 * @param i index of the card
	 * @return the card
	 */
	public Card getCardAt(int i){
		return this.cards[i];
	}
	
	/**
	 * Checks if a card is in the player's hand
	 * @param card card that needs to be searched through the player's hand
	 * @return if the card is in the player's hand the method returns the index of the card
	 * (+1), else it returns 0.
	 */
	public int isInHand(Card card){
		for(int i = 0; i < this.length(); i++){
			if(card.equalsValue(this.getCardAt(i)) && card.equalsSuit(this.getCardAt(i))){
				return i+1;
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		String aux = "";
		
		for(int i = 0; i < handSize; i++){
			aux = aux + cards[i] + " ";
		}
		return aux;
	}
	
	/**
	 * Getter
	 * @return the size of the player's hand.
	 */
	public int length(){
		return handSize;
	}
	
	/**
	 * Method that replaces a card from the player's hand with the replacement
	 * array.
	 * @param index of the card that needs to be switched.
	 */
	public void replace(int index){
		if(index > handSize){
			System.out.println("Card out of range. The hand only has " + handSize);
		}else{
			cards[index] = replacement[index];
		}
	}
	
	/**
	 * Method that replaces a card from the player's hand with a specific card.
	 * @param index of the card that needs to be switched.
	 * @param card that you want to replace the old one with in the array.
	 */
	public void replace(int index, Card card){
		if(index > handSize){
			System.out.println("Card out of range. The hand only has " + handSize);
		}else{
			cards[index] = card;
		}
	}
	
	/**
	 * Sorts the player's hand, used for a simpler analysis of the hand.
	 */
	public void sort(){
		Arrays.sort(this.cards);
	}
	
	/**
	 * Method that checks if all the cards in hand are all from the same suit
	 * @return true if they are, else false.
	 */
	public boolean compareSuit(){
		for(int i=1; i<this.length(); i++){
			if(!(this.cards[0].equalsSuit(this.cards[i])))
				return false;
		}
		return true;
	}
	
	/**
	 * Method that checks if the player has any kind of straight in his hand.
	 * @param opt Chooses which straight is to be checked. 0 for a royal straight, 1 for
	 * a flush followed by a simple straight check.
	 * @return an integer that represents the value of the player's hand, being the
	 * highest the most important and the lowest the least important.
	 */
	public int checkStraight(int opt){ 
		
		boolean flush = this.compareSuit();
		//Royal Flush: same suit, A K Q J 10
		int[] royal = new int[]{Card.ACE,Card.TEN,Card.JACK,Card.QUEEN,Card.KING};
		
		//all values of a deck to check for a sequence.
		int[] deck = new int[]{Card.ACE,Card.DEUCE,Card.THREE,Card.FOUR,Card.FIVE,Card.SIX,Card.SEVEN,Card.EIGHT,Card.NINE,Card.TEN,Card.JACK,Card.QUEEN,Card.KING};
		int value=0;
		int i,j;
		
		if(opt == 0){
			if(!flush)
				return 0; //not any type of flush
			
			for(i=0;i<this.length();i++){
				if(!(this.cards[i].getValue()==royal[i])){ //compares value of hand with royal flush hand
					value = 0;
					break; //not a royal flush	
				}else
					value =11; //So far it is a royal flush
			}
			if(value == 11) //Found a royal flush
				return value;
			
			for(i=0;i<deck.length;i++){
				if(this.cards[0].getValue()==deck[i]) //finds the first value of the seq in the deck array
					break;
			}
			for(j=0;j<this.length();j++){//Compares the ordered hand with the ordered deck to check for a seq
				if(!(this.cards[j].getValue()==deck[i])){
					value = 0; //not a sequence
					break;
				}else{
					value=10;
					i++;
				}
			}
			if(value==10) //Found a sequence! 
				return 10;
		}else if(opt == 1){//Check for Flush and Straight
			if(flush){ //Check for a simple flush
				return 5;
			}else{
				for(i=0;i<deck.length;i++){
					if(this.cards[0].getValue()==deck[i]) //finds the first value of the seq in the deck array
						break;
				}
				for(j=0;j<this.length();j++){//Compares the ordered hand with the ordered deck to check for a seq
					if(!(this.cards[j].getValue()==deck[i])){
						value = 0; //not a sequence
						break;
					}else{
						value=4;
						i++;
					}
				}
				if(value==4) //It is a Straight
					return value;
			}
		}
		return 0;
	}
	
	/**
	 * Method that checks if the hand has any type of a Four of a kind or a triple.
	 * @param opt that let's it chose if we are checking for a four of a kind or a triple,
	 * used for efficiency.
	 * @return an int value that represents the value of the hand as previously described.
	 */
	public int checkOcurrence(int opt){
		Integer i;
		Integer counter=1;
		Integer value=0;
		if(opt==0){
			if(this.cards[1].getValue() != this.cards[3].getValue()) //Check if maybe it is a full house
				return 0;
			for(i=0;i<this.length()-1;i++){
				if(this.cards[i].getValue()==this.cards[i+1].getValue()){
					counter++;
					value = this.cards[i].getValue();
					if(counter.equals(4)){
						if(value.equals(0)) //Found four aces! 
								return 9;
						if(value.equals(1)||value.equals(2)||value.equals(3)) //Found four 2-4
							return 8;
						if(value.equals(4)||value.equals(5)||value.equals(6)||value.equals(7)||value.equals(8)||value.equals(9)
								||value.equals(10)||value.equals(11)||value.equals(12))
							return 7; //Found four 5-K
					}
				}
			}
		}else if(opt ==1){
			for(i=0;i<this.length()-1;i++){
				if(this.cards[i].getValue()==this.cards[i+1].getValue()){
					counter++;
					if(counter.equals(3))
						return 3; //Found a triple! 
				}else
					counter = 1;
			}
		}
		return 0;
	}
	
	/**
	 * Method that checks if there is a Full House on the player's hand.
	 * @return int value that represents the value of the hand as described.
	 */
	public int isFullHouse(){
		int i;
		
		if(this.cards[0].getValue()==this.cards[1].getValue()){
			if(this.cards[1].getValue()==this.cards[2].getValue()){ //If this happens, triple
				if(this.cards[3].getValue()==this.cards[4].getValue())
					return 6; //Found a full house. First a triple then a double
				else
					return 0; // Found just a triple, not a full house
			}else{ //else it is a double
				for(i=2;i<this.length()-1;i++){ //Check if the remaining 3 cards are a triple
					if(this.cards[i].getValue()!=this.cards[i+1].getValue()){
						return 0; //if they are not a triple, not a full house.
					}
				}
				return 6;// if they are, found a full house! 
			}
		}
		return 0;
	}
	
	/**
	 *Method that checks if there is any kind of a pair in a hand.
	 * @return the value of the player's hand.
	 */
	public int isNPair(){
		Integer counter=0;
		Integer value = 0;
		for(int i=0; i<this.length()-1;i++){
			if(this.cards[i].getValue() == this.cards[i+1].getValue()){ //it's a pair
				counter++;
				value = this.cards[i].getValue(); 
			}
		}
		if(counter.equals(2))
			return 2;
		else if(counter.equals(1)&&(value.equals(Card.JACK)||value.equals(Card.QUEEN)||value.equals(Card.KING)||
				value.equals(Card.ACE))){
			//Only one pair but Jacks or Higher
			return 1;
		}
		return 0;
	}
	
	/**
	 * Method that Calls other methods to check if the player's hand as any of the
	 * poker combinations
	 * @return the value that represents the type of the player's hand.
	 */
	public int isCombination(){ //Giant function, checks if the player's hand is worth something
		int value = 0;
		
		value = this.checkStraight(0); //Check for Royal and Straight flush
		if(value!=0)
			return value;
		value = this.checkOcurrence(0); //Checks for Fours
		if(value != 0)
			return value;
		value = this.isFullHouse(); //Check for Full House
		if(value !=0)
			return value;
		value = this.checkStraight(1); //Check for Flush and Straight
		if(value!=0)
			return value;
		value = this.checkOcurrence(1); //Checks for Triples
		if(value !=0)
			return value;
		value = this.isNPair();//Checks for a 2Pair and a J or + pair
		if(value !=0)
			return value;
		return 0;
	}
	
	/**
	 * Method that overwrites the current hand with a new one of your choice.
	 * @param vals array of the value of the cards .
	 * @param suits array of the suits of the cards.
	 */
	public void rigHand(int[] vals, int[] suits){
		for(int i =0; i< handSize;i++){
			this.cards[i] = new Card(vals[i], suits[i]);
		}
		return;
	}
	
	/**
	 * Method that resets a card array, filling the array with null objects.
	 * @param aux_cards Array that needs to be reseted.
	 */
	public static void resetCards(Card[] aux_cards){
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
	}
}