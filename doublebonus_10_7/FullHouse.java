package doublebonus_10_7;

import group18.Hand;

public class FullHouse {

	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		int res = aux_hand.isCombination();
		
		if(res == 6){
			System.out.println("4");
			return "1 2 3 4 5";
		}
		return null;
	}
}
