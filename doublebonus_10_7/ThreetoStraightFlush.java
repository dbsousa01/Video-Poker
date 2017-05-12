package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreetoStraightFlush{

	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";aux_hand.rigHand(hand.getCards());
		
		//Start by sorting the aux_hand
		aux_hand.sort();

		//27. 3 to a straight flush (type 3)
		for(int i = 0; i < aux_hand.length()-2; i++){
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 4)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
					
					if(counter == 3){
						for(i = 0; i < counter; i++){
							int a = hand.isInHand(aux_cards[i]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						return s;
					}
				}
			}
			s = "";
			Hand.resetCards(aux_cards);
		}
		
		return null;
	}

}