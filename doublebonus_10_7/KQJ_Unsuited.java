package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having a king, a queen and a jack in hand.
 */
public class KQJ_Unsuited{
	
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
		
		//22. KQJ unsuited
		for(int i = aux_hand.length()-1; i > 1; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.KING){
				aux_cards[0] = aux_hand.getCardAt(i);
				
				for(int j = i-1; j > 0; j--){
					if(aux_hand.getCardAt(j).getValue() == Card.QUEEN){
						aux_cards[1] = aux_hand.getCardAt(j);
						
						for(int k = j-1; k >= 0; k--){
							if(aux_hand.getCardAt(k).getValue() == Card.JACK){
								aux_cards[2] = aux_hand.getCardAt(k);
										
								for(i = 0; i < 3; i++){
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
			}
		}
		return null;
	}

}