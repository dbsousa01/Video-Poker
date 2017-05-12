package doublebonus_10_7;

import group18.Hand;

public class Flush {

	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(new int[]{hand.getCardAt(0).getValue() ,  hand.getCardAt(1).getValue(), hand.getCardAt(2).getValue(), hand.getCardAt(3).getValue(), hand.getCardAt(4).getValue()}, new int[]{hand.getCardAt(0).getSuit() ,  hand.getCardAt(1).getSuit(), hand.getCardAt(2).getSuit(), hand.getCardAt(3).getSuit(), hand.getCardAt(4).getSuit()});
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		int res = aux_hand.isCombination();
		
		if(res == 5){
			return "1 2 3 4 5";
		}
		return null;
	}
}
