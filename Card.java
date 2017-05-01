package video_poker;

public class Card implements Comparable<Card>{
	
	//Macros
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
		
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
	
	public boolean compareSuit(Card card){
		
		if(this.getSuit() == card.getSuit()){
			return true;
		}else{
			return false;
		}
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
