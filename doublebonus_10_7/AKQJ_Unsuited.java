package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having an ace, king, queen and jack unsuited in hand.
 */
public class AKQJ_Unsuited{

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
		for(int j = 0; j < 2; j++){
			Hand.resetCards(aux_cards);
			counter = 0;
			if(aux_hand.getCardAt(j).getValue() == Card.ACE){//If the first card isn't an A, leave
				aux_cards[0] = aux_hand.getCardAt(0);
				counter++;
				
				for(int i = 1; i < aux_hand.length(); i++){ //Starting on the second card from the right. No need to check suits
					if(aux_hand.getCardAt(i).getValue() >= Card.JACK){
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
					}
				}
			}
		}		
		return null;
	}

}