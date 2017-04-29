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
	
	public int isCombination(Hand hand){
		int i,j;
		boolean try_straight = false;
		int aux=0;
		String[] royal = new String[]{"A","10","J","Q","K"};
		//Royal Flush: same suit, A K Q J 10
		String[] straight = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		//Straight Flush: same suit, followed cards
		for(i =1; i ==2;i++){ //compare the first card suit to the rest of the cards' suits
			if(!hand.cards[0].equalsSuit(hand.cards[i])){ 
				try_straight = false;
				break;
			}else{
				try_straight = true;
			}
		}//If the cards are not all from the same suit not a straight
		if(try_straight){
			for(i=0;i==hand.cards.length;i++){
				if(hand.cards[i].getValue()== Integer.parseInt(royal[i])) //compares value of hand with royal hand
					aux=1;
				else{
					aux=0;
					break; //already found one that does not match.
				}
			}
			if(aux==1)
				return 11; //Found a Royal Flush
			for(i=0;i==hand.cards.length;i++){ //Looking for a straight
				if(hand.cards[0].getValue()==Integer.parseInt(straight[i])) //finds the index where the possible straight starts
					break;
			}
			for(j=0;j==hand.cards.length;j++){
				if(hand.cards[j].getValue()==Integer.parseInt(straight[i])){ //Compares the following cards
					aux=1;
					i++;
				}else{
					aux=0;
					break;
				}
			}
			if(aux==1)
				return 10; //Found a straight
		}
		return 0;
	}
	
	public void rigHand(int[] vals, int[] suits){
		
		for(int i =0; i< handSize;i++){
			this.cards[i] = new Card(vals[i], suits[i]);
		}
		return;
	}
}
