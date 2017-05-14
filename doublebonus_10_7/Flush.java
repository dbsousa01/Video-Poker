package doublebonus_10_7;

import group18.Hand;

/**
 * Class that refers to having a flush in hand.
 */
public class Flush {

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string.
	 */
	public static String getStrategy(Hand hand) {
		
		if(hand.compareSuit()){
			return "1 2 3 4 5";
		}
		return null;
	}
}
