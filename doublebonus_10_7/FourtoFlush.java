package doublebonus_10_7;

import group18.Card;
import group18.Hand;


public class FourtoFlush{

	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();

		//9. 4 to a flush
		for(int j = 0; j < 2; j++){
			Hand.resetCards(aux_cards);
			counter = 0;
			aux_cards[0] = aux_hand.getCardAt(j);
			counter++;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_cards[0].equalsSuit(aux_hand.getCardAt(i))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 4){
				for(int i = 0; i < counter; i++){
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
