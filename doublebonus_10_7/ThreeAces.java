package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreeAces{

	public static String getStrategy(Hand hand) {

		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		String s = "";
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//3. Three aces
		if(aux_hand.getCardAt(0).getValue() == 0 && aux_hand.getCardAt(2).getValue() == 0){
			for(int i = 0; i < hand.length(); i++){
				if(hand.getCardAt(i).getValue() == Card.ACE){
					s += i + " ";
				}
			}
			
			return s;
		}
		
		return null;
	}

}
