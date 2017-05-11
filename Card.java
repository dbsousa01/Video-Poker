package group18;
/**
 * Class that represents a card of a deck, it has 2 arrays of strings for
 * the value and the suit of each different card and card macros, for
 * comparison purposes. 
 * Each card has its own value and suit.
 */
public class Card implements Comparable<Card>{
	
	//Macros
	final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	final static String[] suits = {"S", "C", "D", "H"};
	
	// Kinds of suits
    public final static int SPADES   = 0;
    public final static int CLUBS    = 1;
    public final static int DIAMONDS = 2;
    public final static int HEARTS   = 3;

    // Kinds of ranks
    public final static int ACE   = 0;
    public final static int DEUCE = 1;
    public final static int THREE = 2;
    public final static int FOUR  = 3;
    public final static int FIVE  = 4;
    public final static int SIX   = 5;
    public final static int SEVEN = 6;
    public final static int EIGHT = 7;
    public final static int NINE  = 8;
    public final static int TEN   = 9;
    public final static int JACK  = 10;
    public final static int QUEEN = 11;
    public final static int KING  = 12;
    
	//Class variables
	private int value;
	private int suit;
	
	/**
	 * 
	 * @param val is the value of the card
	 * @param suit is the suit of the card
	 */
	public Card(int val, int suit){
		this.value = val;
		this.suit = suit;
	}
	
	/**
	 * a getter of the suit
	 * @return the integer that corresponds the suit of the card
	 */
	public int getSuit(){
		return this.suit;
	}
	
	/**
	 * a getter of the value
	 * @return the integer that corresponds the value of the card
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * 
	 * @return the name of the card(Ace,King, etc)
	 */
	public String getName(){
		
		return values[this.getValue()];
	}

	@Override
	public String toString() {
		return values[value] + suits[suit];
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}
	
	/**
	 * Compares the suit of two cards
	 * @param card
	 * @return true if two cards have the same suit, false otherwise
	 */
	public boolean equalsSuit(Card card){
		if(this.suit==card.suit)
			return true;
		else
			return false;
	}

	/**
	 * Compares the value of two cards
	 * @param card
	 * @return true if two cards have the same value, false otherwise
	 */
	public boolean equalsValue(Card card) {
		if(this.value==card.value)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		if (suit != other.suit) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

	/**
	 * Compares two cards relative to their value.
	 * @return 1, if a card value is greater that the other, -1 if it is 
	 * smaller or 0 if they have the same value
	 */
	public int compareTo(Card other){
		if(this.value > other.value){
			return 1;
		}else if(this.value < other.value){
			return -1;
		}else{
			return 0;
		}	
	}
		
	
}
