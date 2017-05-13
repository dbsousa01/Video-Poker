package doublebonus_10_7;

import group18.Hand;

/**
 * Class that refers to having three cards to a flush in hand.
 */
public class ThreetoFlush{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {

		for(int i = 0; i < hand.length()-2; i++){
			for(int j = i + 1; j < hand.length()-1; j++){
				if(hand.getCardAt(i).getSuit() == hand.getCardAt(j).getSuit()){
					for(int k = j + 1; k < hand.length(); k++){
						if(hand.getCardAt(i).getSuit() == hand.getCardAt(k).getSuit()){
							i++;
							j++;
							k++;
							return i + " " + j + " " + k;
						}
					}
				}
			}
		}
		
		return null;
	}

}