package doublebonus_10_7;

import group18.Card;
import group18.Hand;

/**
 * Class that refers to having a high pair in hand.
 */
public class HighPair{


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
		int res = aux_hand.isCombination();
		
		if(res == 2){
			counter = 0;
			for(Card card: aux_hand.getCards()){
				if(counter == 0){
					aux_cards[0] = card;
					counter++;
				}else{
					//Because aux_hand is sorted, pairs must be together. If we don't find two consecutive cards of equal value,
					//we don't need to compare the first one again.
					if(card.equalsValue(aux_cards[0]) && (card.getValue() >= Card.JACK || card.getValue() == Card.ACE)){
						aux_cards[1] = card;
						
						for(int i = 0; i < 2; i++){
							int a = hand.isInHand(aux_cards[i]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						return s;
					}else{
						aux_cards[0] = card;
					}
				}
			}
		}
		
		return null;
	}

}
