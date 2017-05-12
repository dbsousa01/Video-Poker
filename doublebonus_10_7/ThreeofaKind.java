package doublebonus_10_7;

import group18.Hand;
import group18.Card;

public class ThreeofaKind{

	public static String getStrategy(Hand hand) {
		
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(hand.length());
		aux_hand.rigHand(hand.getCards());
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		
		//5. Three of a kind (except aces)
		aux_cards[0] = aux_hand.getCardAt(0);
		counter = 1;
		
		for(int i = 1; i < aux_hand.length() - 1; i++){
			if(aux_hand.getCardAt(i).equalsValue(aux_cards[0])){
				aux_cards[counter] = aux_hand.getCardAt(i);
				counter++;
				if(counter == 3){
					for(i = 0; i < counter; i++){
						int a = hand.isInHand(aux_cards[i]);
						if(a != 0){
							s += a + " ";
						}
					}
					return s;
				}
			}else{//because aux_hand is ordered, trios are all together
				Hand.resetCards(aux_cards);
				aux_cards[0]=aux_hand.getCardAt(i);
				counter = 1;
			}
		}
		
		return null;
	}

}
