package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having a king and a jack of the same suit in hand.
 */
public class KT_Suited{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();

		//30. KT suited
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.KING){
				aux_cards[0] = aux_hand.getCardAt(aux_hand.length()-1);
				for(int k = aux_hand.length() - 2; k >= 0; k--){ //Starting from the second card from the end
					if(aux_hand.getCardAt(k).getValue() == Card.TEN && aux_hand.getCardAt(k).equalsSuit(aux_cards[0])){
						aux_cards[1] = aux_hand.getCardAt(k);
						
						for(i = 0; i < 2; i++){
							int a = hand.isInHand(aux_cards[i]); 
							if(a != 0){
								s += a + " ";
							}
						}
						return s;
					}
				}
			}
		}
		return null;
	}

}