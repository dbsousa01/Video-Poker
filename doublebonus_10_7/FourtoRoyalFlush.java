package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class FourtoRoyalFlush{

	public static String getStrategy(Hand hand) {
				
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();

		//2. 4 to a royal flush
		for(int i = 0; i < 2; i++){ //if the first 2 cards aren't valid, we don't have 4 to a R.F.
			Hand.resetCards(aux_cards);
			if(counter == 0){
				if(aux_hand.getCardAt(i).getValue() == Card.ACE || aux_hand.getCardAt(i).getValue() == Card.TEN){ //Because aux_hand is sorted, if we don't find an A or a 10, we don't have 4 to a R.F.
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					
					for(int j = i; j < aux_hand.length(); j++){//keep searching the hand
						if(aux_hand.getCardAt(j).getValue() >= Card.TEN){
							//NOTE: even if the first card wasn't an ace, the rest won't possibly be, since aux_hand is sorted.
							//if it's a valid value && valid suit
							if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0])){
								aux_cards[counter] = aux_hand.getCardAt(j);
								counter++;
							}
						}
					}
				}
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
		return null;
		
		
	}

}
