package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having a queen and a jack of the same suit in hand.
 */
public class QJ_Suited{

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
		
		//16. QJ suited
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.QUEEN){
				aux_cards[0] = aux_hand.getCardAt(i);
				for(int j = i-1; j >= 0; j--){
					if(aux_hand.getCardAt(j).getValue() == Card.JACK && aux_hand.getCardAt(j).equalsSuit(aux_cards[0])){
						aux_cards[1] = aux_hand.getCardAt(j);
						
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
