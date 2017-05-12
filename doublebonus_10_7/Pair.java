package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class Pair{

	public static String getStrategy(Hand hand) {

		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		String s = "";
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//12. Low pair - it's a copy of the pair function, but with no value restriction
		//Because aux_hand is sorted, pairs are always together
		for(int i = 0; i < aux_hand.length() - 1; i++){
			if(aux_hand.getCardAt(i).equalsValue(aux_hand.getCardAt(i+1))){
				aux_cards[0] = aux_hand.getCardAt(i);
				aux_cards[1] = aux_hand.getCardAt(i+1);

				for(i = 0; i < 2; i++){
					int a = hand.isInHand(aux_cards[i]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				return s;
			}
		}
		
		
		return null;
	}

}