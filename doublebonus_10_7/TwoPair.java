package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having two pairs in hand.
 */
public class TwoPair{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public  static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		int res = aux_hand.isCombination();
		
		if(res == 2){
			counter = 0;
			for(int i = 0; i < aux_hand.length()-1; i++){
				if(aux_hand.getCardAt(i).equalsValue(aux_hand.getCardAt(i+1))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					aux_cards[counter] = aux_hand.getCardAt(i+1);
					counter++;
					
					if(counter == 4){
						for(i = 0; i < counter; i++){
							int a = hand.isInHand(aux_cards[i]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						return s;
					}

					i++;
				}
			}
		}
		
		return null;
	}

}
