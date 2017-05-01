package group18;

public class Card implements Comparable<Card>{
	
	//Macros
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
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
	
	
	public Card(int val, int suit){
	
		this.value = val;
		this.suit = suit;
	}
	
	
	public int getSuit(){
		
		return this.suit;
	}
	
	
	public int getValue(){
		
		return this.value;
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

	public boolean equalsSuit(Card card){
		if(this.suit==card.suit)
			return true;
		else
			return false;
	}

	public boolean equalsValue(Card card) {
		if(this.value==card.value)
			return true;
		else
			return false;
	}

	//Necessary when implementing Comparable<T>
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
