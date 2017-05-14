package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having four cards to an inside Straight and one high card in hand.
 */
public class FourtoInsideStraight_1HighCard{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string.
	 */
	public static String getStrategy(Hand hand) {

		//Auxiliary variables
		int aux_count = 0;
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		for(int i = 0; i < aux_hand.length()-3; i++){
			Hand.resetCards(aux_cards);
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).getValue() <= aux_cards[0].getValue() + 4){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
					
					if(counter == 3 && aux_hand.getCardAt(i).getValue() >= Card.TEN && aux_hand.getCardAt(0).getValue() == Card.ACE){
						aux_cards[counter] = aux_hand.getCardAt(0);
						counter++;
					}
					
					if(counter == 4){
						aux_count = 0;
						for(int k = 0; k < counter; k++){
							if(aux_cards[k].getValue() >= Card.JACK || aux_cards[k].getValue() == Card.ACE){
								aux_count++;
							}
							
							int a = hand.isInHand(aux_cards[k]);
							
							if(a != 0){
								s += a + " ";
							}
						}
						
						if(aux_count == 1){
							return s;
						}
						
						s = "";
					}
				}else{
					break;
				}
			}
		}
		
		return null;
	}

}