package group18;


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
	
	public boolean compareSuit(){ //Function that checks if the cards in hand are all the same suit
		for(int i=1; i<this.length(); i++){
			if(!(this.cards[0].equalsSuit(this.cards[i])))
				return false;
		}
		return true;
	}
	
	
	//opt chooses what kind of straight it will check, 0 for royal and straight; 1 for flush and straight
	//this is done for efficiency
	public int checkStraight(int opt){ 
		
		boolean flush = this.compareSuit();
		//Royal Flush: same suit, A K Q J 10
		int[] royal = new int[]{Card.ACE,Card.TEN,Card.JACK,Card.QUEEN,Card.KING};
		
		//all values of a deck
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
	
	public int checkOcurrence(int opt){
		Integer i;
		Integer counter=1;
		Integer value=0;
		if(opt==0){
			if(this.cards[1].getValue() != this.cards[3].getValue()) //quando era full house dava porcaria(esparguete)
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
	
	
	public int isFullHouse(){ //isto está um bocado hardcoded lol
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
	
	public void rigHand(int[] vals, int[] suits){
		for(int i =0; i< handSize;i++){
			this.cards[i] = new Card(vals[i], suits[i]);
		}
		
		return;
	}
}
