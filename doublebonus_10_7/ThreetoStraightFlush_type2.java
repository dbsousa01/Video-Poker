package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreetoStraightFlush_type2{

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
		
		//20. 3 to a straight flush (type 2)
		for(int i = 0; i < aux_hand.length()-2; i++){
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 5)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
					
					if(counter == 3){
						aux_count = 0;
						for(int k = 0; k < counter; k++){
							int a = hand.isInHand(aux_cards[k]);
							
							if(a != 0){
								s += a + " ";
							}
							
							if(aux_cards[k].getValue() == Card.ACE || aux_cards[k].getValue() >= Card.JACK){
								aux_count++;
							}
						}
						
						if(aux_cards[0].getValue() == Card.DEUCE && aux_cards[1].getValue() == Card.THREE && aux_cards[2].getValue() == Card.FOUR){
							aux_count++;
						}
						
						if(aux_count == 1){ //if not, it's not type 2
							return s;
						}
					}
				}
			}
			//Do over
			s = "";
			Hand.resetCards(aux_cards);
		}
		
		return null;
	}

}