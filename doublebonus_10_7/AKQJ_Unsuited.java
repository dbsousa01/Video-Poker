package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class AKQJ_Unsuited{

	public static String getStrategy(Hand hand) {

		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//13. AKQJ unsuited
		Hand.resetCards(aux_cards);
		
		if(aux_hand.getCardAt(0).getValue() == Card.ACE){//If the first card isn't an A, leave
			aux_cards[0] = aux_hand.getCardAt(0);
			counter++;
			
			for(int i = 1; i < aux_hand.length(); i++){ //Starting on the second card from the right. No need to check suits
				if(aux_hand.getCardAt(i).getValue() >= Card.JACK){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 4){
				for(int i = 0; i < aux_cards.length; i++){
					if(aux_cards[i] != null){
						int a = hand.isInHand(aux_cards[i]); 
						if(a != 0){
							s += a + " ";
						}
					}else{
						break;
					}
				}
				return s;
			}
		}
		
		return null;
	}

}