package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having a king in hand.
 */
public class King{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {
		
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.KING){
				i++;
				return String.valueOf(i);
			}
		}
		
		return null;
	}

}