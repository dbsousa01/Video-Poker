package doublebonus_10_7;

import group18.Card;
import group18.Hand;

public class HighPair{


	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		int res = aux_hand.isCombination();
		
		//8. High pair
		if(res == 2){
			counter = 0;
			for(Card card: aux_hand.getCards()){
				if(counter == 0){
					aux_cards[0] = card;
					counter++;
				}else{
					if(card.equalsValue(aux_cards[0]) && card.getValue() >= Card.JACK){
						aux_cards[1] = card;
						break;
					}else{
						aux_cards[0] = card;
					}
				}
			}
			
			for(int i = 0; i < 2; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			return s;
		}
		
		return null;
	}

}
