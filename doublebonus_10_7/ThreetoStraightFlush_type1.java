package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreetoStraightFlush_type1{

	public static String getStrategy(Hand hand) {

		//Auxiliary variables
		int aux_count = 0;
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//14. 3 to a straight flush (type 1)
		for(int i = 0; i < aux_hand.length()-2; i++){
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 4)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
					
					if(counter == 3){
						for(int k = 0; k < counter; k++){
							if(aux_cards[k].getValue()>= Card.JACK){
								aux_count++;
							}
							
							int a = hand.isInHand(aux_cards[k]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						
						if(aux_count >= 2){ //if we don't have 2 High Cards it's not type 1
							return s;
						}
					}
				}
			}
			s = "";
			Hand.resetCards(aux_cards);
		}
		
		return null;
	}

}
