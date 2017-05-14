package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having four cards to an outside Straight in hand.
 */
public class FourtoOutsideStraight{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string.
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
		
		counter = 0;
		for(int i = 0; i < aux_hand.length(); i++){
			if(counter == 0){
				aux_cards[0] = aux_hand.getCardAt(i);
			}else{
				if(aux_hand.getCardAt(i).getValue() ==(aux_cards[counter-1].getValue() + 1)){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					
					if(counter == 4){
						for(i = 0; i < counter; i++){
							int a = hand.isInHand(aux_cards[i]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						return s;
					}
				}else{
					Hand.resetCards(aux_cards);
					aux_cards[0] = aux_hand.getCardAt(i);
					counter = 1;
				}
			}
		}
		
		return null;
	}

}
