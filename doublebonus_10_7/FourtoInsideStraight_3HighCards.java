package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class FourtoInsideStraight_3HighCards{

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
		
		//15. 4 to an inside straight with 3 high cards
		for(int i = 0; i < aux_hand.length()-2; i++){
			Hand.resetCards(aux_cards);
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).getValue() <= aux_cards[0].getValue() + 4){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
				}else{
					break;
				}
			}
			
			if(counter == 3 && aux_hand.getCardAt(i).getValue() >= Card.TEN && aux_hand.getCardAt(0).getValue() == Card.ACE){
				aux_cards[counter] = aux_hand.getCardAt(0);
				counter++;
			}
			
			if(counter == 4){
				aux_count = 0;
				for(int j = 0; j < counter; j++){
					if(aux_cards[j].getValue() == Card.ACE || aux_cards[j].getValue()>= Card.JACK){
						aux_count++;
					}
					
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				
				if(aux_count >= 3){
					return s;
				}else{
					s = "";
				}
			}
		}
		
		
		return null;
	}

}
