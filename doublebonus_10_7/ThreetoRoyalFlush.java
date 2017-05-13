package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having three cards of a royal flush in hand.
 */
public class ThreetoRoyalFlush{
	
	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		for(int j = 0; j < aux_hand.length() - 2; j++){
			counter = 0;
			if(aux_hand.getCardAt(j).getValue() == Card.ACE || aux_hand.getCardAt(j).getValue() >= Card.TEN){
				aux_cards[0] = aux_hand.getCardAt(j);
				counter = 1;
				
				for(int i = j + 1; i < aux_hand.length(); i++){//keep searching the hand
					//if it's a valid value && valid suit
					if(aux_hand.getCardAt(i).getValue() >= Card.TEN && aux_hand.getCardAt(i).equalsValue(aux_cards[0])){//Even if the first card wasn't an ace, the rest won't possibly be, since aux_hand is sorted.
						aux_cards[counter] = aux_hand.getCardAt(i);
						counter++;
						
						if(counter == 3){ //If 3 valid cards were found, we have 3 to a royal flush
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
			}
			//If 3 cards were not found, reset the aux_cards and do over
			Hand.resetCards(aux_cards);
		}
		
		return null;
	}

}
