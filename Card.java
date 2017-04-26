package video_poker;

public class Card implements Comparable<Card>{

	//Class variables
	private int value;
	private int suit;
	
	//Macros
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private final static String[] suits = {"C","D","H","S"};
	
	
	public Card(int val, int suit){
	
		this.value = val;
		this.suit = suit;
	}
	
	
	public int getSuit(Card a){
		
		return a.suit;
	}
	
	
	public int getValue(Card a){
		
		return a.value;
	}


	@Override
	public String toString() {
		
		return values[value] + suits[suit];
	}
	
	
	//Necessary to implement Comparable<T>
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
