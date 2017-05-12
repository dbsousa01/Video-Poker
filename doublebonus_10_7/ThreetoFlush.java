package doublebonus_10_7;

import group18.Hand;

public class ThreetoFlush{

	public static String getStrategy(Hand hand) {

		for(int i = 0; i < hand.length()-2; i++){
			for(int j = i + 1; j < hand.length()-1; j++){
				if(hand.getCardAt(i).getSuit() == hand.getCardAt(j).getSuit()){
					for(int k = j + 1; k < hand.length(); k++){
						if(hand.getCardAt(i).getSuit() == hand.getCardAt(k).getSuit()){
							i++;
							j++;
							k++;
							return i + " " + j + " " + k;
						}
					}
				}
			}
		}
		
		return null;
	}

}