package doublebonus_10_7;

import group18.Card;
import group18.Hand;

/**
 * Class that refers to having a jack in hand.
 */
public class Jack {

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {
		
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.JACK){
				i++;
				return String.valueOf(i);
			}
		}
		
		return null;
	}
}
