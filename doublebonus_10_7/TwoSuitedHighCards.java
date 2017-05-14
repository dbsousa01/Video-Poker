package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having two high cards of the same suit in hand.
 */
public class TwoSuitedHighCards{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		for(int i = 0; i < aux_hand.length(); i++){
			if(aux_hand.getCardAt(i).getValue() == Card.ACE || aux_hand.getCardAt(i).getValue() >= Card.JACK){
				if(counter == 0){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}else if(aux_hand.getCardAt(i).equalsSuit(aux_cards[0])){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter ++;
				}
				
				if(counter == 2){
					for(i = 0; i < counter; i++){
						int a = hand.isInHand(aux_cards[i]);
						
						if(a != 0){
							s += a + " ";
						}
					}
					
					return s;
				}
			}
		}
		
		return null;
	}
}