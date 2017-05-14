package doublebonus_10_7;

import group18.Hand;
import group18.Card;


/**
 * Class that refers to having an ace in hand.
 */
public class Ace{
	
	/**
	 * @param hand
	 * @return index of the intended card in the hand in the form of a string.
	 */
	public static String getStrategy(Hand hand) {
		
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.ACE){
				i++;
				return String.valueOf(i);
			}
		}
		
		return null;
	}

}
