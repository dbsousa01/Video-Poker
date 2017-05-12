package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreetoFlush_2HighCards{

	public static String getStrategy(Hand hand) {

		//Auxiliary variables
		int aux_count = 0;
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//17. 3 to a flush with 2 high cards
		for(int j = 0; j < 3; j++){ //if the 1st 3 cards fail, we can no longer have 3 cards of the same suit
			aux_cards[0] = aux_hand.getCardAt(j);
			counter = 1;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_hand.getCardAt(i).equalsSuit(aux_cards[0])){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					
					if(counter == 3){
						aux_count = 0;
						for(i = 0; i < counter; i++){
							if(aux_cards[i].getValue() == Card.ACE || aux_cards[i].getValue() >= Card.JACK){
								aux_count++; //To verify if we have 2 high cards
							}
							
							int a = hand.isInHand(aux_cards[i]);
							if(a != 0){
								s += a + " ";
							}
						}
						
						if(aux_count == 2){
							return s;
						}else{
							s = "";
						}
					}
				}
			}
			Hand.resetCards(aux_cards);
		}
	
		return null;
	}

}
