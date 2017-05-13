package doublebonus_10_7;

import group18.Hand;
import group18.Card;

/**
 * Class that refers to having three cards to a flush, including one high card, in hand.
 */
public class ThreetoFlush_1HighCard{

	/**
	 * @param hand
	 * @return indices of the intended cards in the hand, in the form of a string. If no card is found, returns null.
	 */
	public static String getStrategy(Hand hand) {

		//Auxiliary variables
		int aux_count = 0;
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//25. 3 to a flush with 1 high card
		for(int j = 0; j < 3; j++){ //if the 1st 3 cards fail, we can no longer have 3 cards of the same suit
			aux_cards[0] = aux_hand.getCardAt(j);
			counter = 1;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_hand.getCardAt(i).equalsSuit(aux_cards[0])){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					
					if(counter == 3){
						aux_count = 0;
						for(i = 0; i < counter; i++){
							if(aux_cards[i].getValue() == Card.ACE || aux_cards[i].getValue() >= Card.JACK){
								aux_count++; //To verify if we have 1 high card
							}
							
							int a = hand.isInHand(aux_cards[i]);
							if(a != 0){
								s += a + " ";
							}
						}
						
						if(aux_count == 1){
							return s;
						}else{
							s = "";
						}
					}
				}
			}
			Hand.resetCards(aux_cards);
		}
		
		return null;
	}

}