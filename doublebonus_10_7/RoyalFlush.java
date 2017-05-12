package doublebonus_10_7;

import group18.Hand;


public class RoyalFlush{

	public static String getStrategy(Hand hand) {

		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
			
		//Start by sorting the aux_hand
		aux_hand.sort();
		int result = aux_hand.isCombination();
		
		//If the player has a royal flush, tell him to hold all cards 
		if(result == 11){
			return "1 2 3 4 5";
		}
		
		return null;
		
	}

}