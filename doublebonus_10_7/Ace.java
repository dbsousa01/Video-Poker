package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class Ace{
	
	public static String getStrategy(Hand hand) {
		
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.ACE){
				int a = i + 1;
				return a + "";
			}
		}
		
		return null;
	}

}
