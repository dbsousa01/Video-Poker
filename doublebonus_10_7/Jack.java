package doublebonus_10_7;

import group18.Card;
import group18.Hand;

public class Jack {

	public static String getStrategy(Hand hand) {
		
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.JACK){
				int a = i + 1;
				return a + "";
			}
		}
		
		return null;
	}
}
