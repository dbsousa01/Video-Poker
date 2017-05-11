package group18;


public class Advice {
	
	private static void resetCards(Card[] aux_cards){
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
	}
	
	public static String getAdvice(Hand hand){
		
		//Auxiliary variables
		Deck deck = new Deck();
		int aux_count = 0;
		
		//Creating an auxiliary hand with the same cards as the hand
		Hand aux_hand = new Hand(deck, hand.length());
		aux_hand.rigHand(new int[]{hand.getCardAt(0).getValue() ,  hand.getCardAt(1).getValue(), hand.getCardAt(2).getValue(), hand.getCardAt(3).getValue(), hand.getCardAt(4).getValue()}, new int[]{hand.getCardAt(0).getSuit() ,  hand.getCardAt(1).getSuit(), hand.getCardAt(2).getSuit(), hand.getCardAt(3).getSuit(), hand.getCardAt(4).getSuit()});
		
		Card[] aux_cards = new Card[hand.length()];
		int counter = 0;
		String s = "h ";
		
		
		//Start by sorting the aux_hand
		aux_hand.sort();
		int res = aux_hand.isCombination();
		
		
		
		//1. Straight flush, four of a kind, royal flush
		if(res >= 7 && res <= 11){
			System.out.println("1");
			return "h 1 2 3 4 5";
		}

		
		//2. 4 to a royal flush
		for(int i = 0; i < 2; i++){ //if the first 2 cards aren't valid, we don't have 4 to a R.F.
			resetCards(aux_cards);
			counter = 0;
			if(counter == 0){
				if(aux_hand.getCardAt(i).getValue() == Card.ACE || aux_hand.getCardAt(i).getValue() >= Card.TEN){ //Because aux_hand is sorted, if we don't find an A or a 10, we don't have 4 to a R.F.
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					
					for(int j = i; j < aux_hand.length(); j++){//keep searching the hand
						if(aux_hand.getCardAt(j).getValue() >= Card.TEN){
							//NOTE: even if the first card wasn't an ace, the rest won't possibly be, since aux_hand is sorted.
							//if it's a valid value && valid suit
							if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0])){
								aux_cards[counter] = aux_hand.getCardAt(j);
								counter++;
							}
						}
					}
				}
			}
		}
		
		if(counter == 4){
			for(int i = 0; i < counter; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("2");
			return s;
		}
		
		
		//3. Three aces
		resetCards(aux_cards);
		if(aux_hand.getCardAt(0).getValue() == Card.ACE && aux_hand.getCardAt(2).getValue() == Card.ACE){
			for(int i = 0; i < 3; i++){
				int a = hand.isInHand(aux_hand.getCardAt(i));
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("3");
			return s;
		}
		

		//4. Straight, flush, full house
		if(res >= 4 && res <= 6){
			System.out.println("4");
			return "h 1 2 3 4 5";
		}
		

		//5. Three of a kind (except aces)
		resetCards(aux_cards);
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
					System.out.println("5");
					return s;
				}
			}else{
				resetCards(aux_cards);
				aux_cards[0]=aux_hand.getCardAt(i); //because aux_hand is ordered, trios are all together
				counter = 1;
			}
		}
		
				
		//??Please re-read this
		//6. 4 to a straight flush
		for(int j = 0; j < 2; j++){
			resetCards(aux_cards);
			counter = 0;
			aux_cards[0] = aux_hand.getCardAt(j);
			counter++;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_cards[0].equalsSuit(aux_hand.getCardAt(i))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			for(int i = 1; i < counter; i++){
				if(aux_cards[0].getValue() <= aux_cards[i].getValue() + 4){
					counter = 0;
				}
			}

			if(counter == 4){	
				for(int i = 0; i < counter; i++){
					int a = hand.isInHand(aux_cards[i]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				System.out.println("6");
				return s;
			}
		}
		

		//7. Two pair
		if(res == 2){
			counter = 0;
			for(int i = 0; i < aux_hand.length()-1; i++){
				if(aux_hand.getCardAt(i).equalsValue(aux_hand.getCardAt(i+1))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
					aux_cards[counter] = aux_hand.getCardAt(i+1);
					counter++;
				}
			}
			
			for(int i = 0; i < counter; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("7");
			return s;
		}
		
		
		//8. High pair
		if(res == 2){
			counter = 0;
			for(Card card: aux_hand.getCards()){
				if(counter == 0){
					aux_cards[0] = card;
					counter++;
				}else{
					if(card.equalsValue(aux_cards[0]) && card.getValue() >= Card.JACK){
						aux_cards[1] = card;
						break;
					}else{
						aux_cards[0] = card;
					}
				}
			}
			
			for(int i = 0; i < 2; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("8");
			return s;
		}
		
		
		//9. 4 to a flush
		for(int j = 0; j < 2; j++){
			resetCards(aux_cards);
			counter = 0;
			aux_cards[0] = aux_hand.getCardAt(j);
			counter++;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_cards[0].equalsSuit(aux_hand.getCardAt(i))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 4){
				for(int i = 0; i < counter; i++){
					int a = hand.isInHand(aux_cards[i]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				System.out.println("9");
				return s;
			}
		}
		
		
		//10. 3 to a royal flush
		for(int j = 0; j < aux_hand.length() - 2; j++){
			resetCards(aux_cards);
			counter = 0;
			if(aux_hand.getCardAt(j).getValue() == Card.ACE || aux_hand.getCardAt(j).getValue() >= Card.TEN){
				aux_cards[0] = aux_hand.getCardAt(j);
				counter++;
				
				for(int i = j + 1; i < aux_hand.length(); i++){//keep searching the hand
					if(aux_hand.getCardAt(i).getValue() >= Card.TEN){
						aux_cards[counter] = aux_hand.getCardAt(i);
						counter++;
						//NOTE: even if the first card wasn't an ace, the rest won't possibly be, since aux_hand is sorted.
						//if it's a valid value && valid suit
						if(counter >= 2 && !(aux_cards[counter-1].equalsSuit(aux_cards[0]))){
							counter--;
						}
					}
				}
			}
		}
		
		if(counter == 3){ //If we haven't found 3 cards with valid values, this won't happen
			for(int i = 0; i < counter; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("10");
			return s;
		}
		
		
		//11. 4 to an outside straight
		counter = 0;
		for(int i = 0; i < aux_hand.length(); i++){
			if(counter == 0){
				aux_cards[0] = aux_hand.getCardAt(i);
			}else{
				if(aux_hand.getCardAt(i).getValue() ==(aux_cards[counter-1].getValue() + 1)){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}else{
					resetCards(aux_cards);
					aux_cards[0] = aux_hand.getCardAt(i);
					counter = 1;
				}
			}
		}
		
		if(counter == 4){
			for(int i = 0; i < counter; i++){
				int a = hand.isInHand(aux_cards[i]);
				
				if(a != 0){
					s += a + " ";
				}
			}
			System.out.println("11");
			return s;
		}
		
		
		//12. Low pair - it's a copy of the pair function, but with no value restriction
		//Because aux_hand is sorted, pairs are always together
		for(int i = 0; i < aux_hand.length() - 1; i++){
			if(aux_hand.getCardAt(i).equalsValue(aux_hand.getCardAt(i+1))){
				aux_cards[0] = aux_hand.getCardAt(i);
				aux_cards[1] = aux_hand.getCardAt(i+1);

				for(i = 0; i < 2; i++){
					int a = hand.isInHand(aux_cards[i]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				System.out.println("12");
				return s;
			}
		}
		
		
		//13. AKQJ unsuited
		resetCards(aux_cards);
		counter = 0;
		
		if(aux_hand.getCardAt(0).getValue() == Card.ACE){//If the first card isn't an A, leave
			aux_cards[0] = aux_hand.getCardAt(0);
			counter++;
			
			for(int i = 1; i < aux_hand.length(); i++){ //Starting on the second card from the right. No need to check suits
				if(aux_hand.getCardAt(i).getValue() >= Card.JACK){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 4){
				for(int i = 0; i < aux_cards.length; i++){
					if(aux_cards[i] != null){
						int a = hand.isInHand(aux_cards[i]); 
						if(a != 0){
							s += a + " ";
						}
					}else{
						break;
					}
				}
				System.out.println("13");
				return s;
			}
		}
		
		
		//14. 3 to a straight flush (type 1)
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 4)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
				}
			}
			if(counter == 3){
				for(int j = 0; j < counter; j++){
					if(aux_cards[j].getValue()>= Card.JACK){
						aux_count++;
					}
					
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				
				if(aux_count >= 2){
					System.out.println("14");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//15. 4 to an inside straight with 3 high cards
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
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
					System.out.println("15");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//16. QJ suited
		resetCards(aux_cards);
		s = "h ";
		
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.QUEEN){
				aux_cards[0] = aux_hand.getCardAt(i);
				for(; i >= 0; i--){
					if(aux_hand.getCardAt(i).getValue() == Card.JACK && aux_hand.getCardAt(i).equalsSuit(aux_cards[0])){
						aux_cards[1] = aux_hand.getCardAt(i);
						
						for(i = 0; i < 2; i++){
							int a = hand.isInHand(aux_cards[i]); 
							if(a != 0){
								s += a + " ";
							}
						}
						System.out.println("16");
						return s;
					}
				}
			}
		}
		
		
		//17. 3 to a flush with 2 high cards
		for(int j = 0; j < 3; j++){ //if the 1st 3 cards fail, we can no longer have 3 cards of the same suit
			resetCards(aux_cards);
			counter = 0;
			aux_cards[0] = aux_hand.getCardAt(j);
			counter++;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_cards[0].equalsSuit(aux_hand.getCardAt(i))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 3){
				aux_count = 0;
				for(int i = 0; i < counter; i++){
					//To verify if we have 2 high cards
					if(aux_cards[i].getValue() == Card.ACE || aux_cards[i].getValue() >= Card.JACK){
						aux_count++;
					}
					
					int a = hand.isInHand(aux_cards[i]);
					if(a != 0){
						s += a + " ";
					}
				}
				
				if(aux_count >= 2){
					System.out.println("17");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//18. 2 suited high cards
		counter = 0;
		for(int i = 0; i < aux_hand.length(); i++){
			if(aux_hand.getCardAt(i).getValue() == Card.ACE || aux_hand.getCardAt(i).getValue() >= Card.JACK){
				aux_cards[counter] = aux_hand.getCardAt(i);
				counter ++;
			}
			
			if(counter == 2 && !(aux_cards[0].equalsSuit(aux_cards[counter-1]))){
				counter--;
			}
			
			if(counter == 2){
				for(i = 0; i < counter; i++){
					int a = hand.isInHand(aux_cards[i]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				
				return s;
			}
			
		}
		
		
		//19. 4 to an inside straight with 2 high cards
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
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
					if(aux_cards[j].getValue()>= Card.JACK){
						aux_count++;
					}
					
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}

				if(aux_count >= 2){
					System.out.println("19");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		
		//20. 3 to a straight flush (type 2)
		s = "h ";
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 5)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
				}
			}
			
			if(counter == 3){
				aux_count = 0;
				for(int j = 0; j < counter; j++){
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
					
					if(aux_cards[j].getValue() == Card.ACE || aux_cards[j].getValue() >= Card.JACK){
						aux_count++;
					}
				}
				if(aux_cards[0].getValue() == Card.DEUCE && aux_cards[1].getValue() == Card.THREE && aux_cards[2].getValue() == Card.FOUR){
					aux_count++;
				}
				
				if(aux_count >= 1){
					System.out.println("20");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//21. 4 to an inside straight with 1 high card
		for(int i = 0; i < aux_hand.length()-3; i++){
			resetCards(aux_cards);
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
					if(aux_cards[j].getValue() >= Card.JACK || aux_cards[j].getValue() == Card.ACE){
						aux_count++;
					}
					
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				
				if(aux_count == 1){
					System.out.println("21");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//22. KQJ unsuited
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
		counter = 0;
		
		if(aux_hand.getCardAt(aux_hand.length()-1).getValue() == Card.KING){
			aux_cards[0] = aux_hand.getCardAt(aux_hand.length()-1);
			counter++;
			
			for(int i = aux_hand.length()-2; i >= 0; i--){ //Starting on the second card from the right
				if(aux_hand.getCardAt(i).getValue() == Card.QUEEN || aux_hand.getCardAt(i).getValue() == Card.JACK){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			for(int i = 0; i < aux_cards.length; i++){
				if(aux_cards[i] != null){
					int a = hand.isInHand(aux_cards[i]); 
					if(a != 0){
						s += a + " ";
					}
				}else{
					break;
				}
			}
			System.out.println("22");
			return s;
		}


		//23. JT suited
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
		
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.JACK){
				aux_cards[0] = aux_hand.getCardAt(i);
				for(;i >= 0; i--){
					if(aux_hand.getCardAt(i).getValue() == Card.TEN && aux_hand.getCardAt(i).equalsSuit(aux_cards[0])){
						aux_cards[1] = aux_hand.getCardAt(i);
						
						for(i = 0; i < aux_cards.length; i++){
							if(aux_cards[i] != null){
								int a = hand.isInHand(aux_cards[i]); 
								if(a != 0){
									s += a + " ";
								}
							}else{
								break;
							}
						}
						System.out.println("23");
						return s;
					}
				}
			}
		}
		
		
		//24. QJ unsuited
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
		
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.QUEEN){
				aux_cards[0] = aux_hand.getCardAt(i);
				for(; i >= 0; i--){
					if(aux_hand.getCardAt(i).getValue() == Card.JACK){
						aux_cards[1] = aux_hand.getCardAt(i);
						
						for(i = 0; i < aux_cards.length; i++){
							if(aux_cards[i] != null){
								int a = hand.isInHand(aux_cards[i]); 
								if(a != 0){
									s += a + " ";
								}
							}else{
								break;
							}
						}
						System.out.println("24");
						return s;
					}
				}
			}
		}
		
		
		//25. 3 to a flush with 1 high card
		for(int j = 0; j < 3; j++){ //if the 1st 3 cards fail, we can no longer have 3 cards of the same suit
			resetCards(aux_cards);
			counter = 0;
			aux_cards[0] = aux_hand.getCardAt(j);
			counter++;
			for(int i = j + 1; i < aux_hand.length(); i++){
				if(aux_cards[0].equalsSuit(aux_hand.getCardAt(i))){
					aux_cards[counter] = aux_hand.getCardAt(i);
					counter++;
				}
			}
			
			if(counter == 3){
				aux_count = 0;
				for(int i = 0; i < counter; i++){
					if(aux_cards[i].getValue() == Card.ACE || aux_cards[i].getValue() >= Card.JACK){
						aux_count++;
					}//To verify if we have 1 high cards
					
					int a = hand.isInHand(aux_cards[i]);
					if(a != 0){
						s += a + " ";
					}
				}
				
				if(aux_count >= 1){
					System.out.println("25");
					return s;
				}else{
					s = "h ";
				}
			}
		}
		
		
		//26. QT suited
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
		
		for(int i = aux_hand.length()-1; i >= 0; i--){
			if(aux_hand.getCardAt(i).getValue() == Card.QUEEN){
				aux_cards[0] = aux_hand.getCardAt(i);
				for(;i >= 0; i--){
					if(aux_hand.getCardAt(i).getValue() == Card.TEN){
						aux_cards[1] = aux_hand.getCardAt(i);
						
						for(i = 0; i < aux_cards.length; i++){
							if(aux_cards[i] != null){
								int a = hand.isInHand(aux_cards[i]); 
								if(a != 0){
									s += a + " ";
								}
							}else{
								break;
							}
						}
						System.out.println("26");
						return s;
					}
				}
			}
		}
		
		
		//27. 3 to a straight flush (type 3)
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
			aux_cards[0] = aux_hand.getCardAt(i);
			counter = 1;
			for(int j = i + 1; j < aux_hand.length(); j++){
				if(aux_hand.getCardAt(j).equalsSuit(aux_cards[0]) && aux_hand.getCardAt(j).getValue() < (aux_cards[0].getValue() + 4)){
					aux_cards[counter] = aux_hand.getCardAt(j);
					counter++;
				}
			}
			
			if(counter == 3){
				aux_count = 0;
				for(int j = 0; j < counter; j++){
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				System.out.println("20");
				return s;
			}
		}
		
		
		//28. KQ, KJ unsuited
		for(int i = 0; i < aux_cards.length; i++){
			aux_cards[i] = null;
		}
		
		if(aux_hand.getCardAt(aux_hand.length()-1).getValue() == Card.KING){ //Last card on aux_hand
			aux_cards[0] = aux_hand.getCardAt(aux_hand.length()-1);
			for(int k = aux_hand.length() - 2; k >= 0; k--){ //Starting from the second card from the end
				if(aux_hand.getCardAt(k).getValue() == Card.QUEEN || aux_hand.getCardAt(k).getValue() == Card.JACK){
					aux_cards[1] = aux_hand.getCardAt(k);
					
					for(int i = 0; i < aux_cards.length; i++){
						if(aux_cards[i] != null){
							int a = hand.isInHand(aux_cards[i]); 
							if(a != 0){
								s += a + " ";
							}
						}else{
							break;
						}
					}
					System.out.println("28");
					return s;
					
				}
			}
		}
		
		
		//29. Ace
		if(aux_hand.getCardAt(0).getValue() == Card.ACE){
			aux_cards[0] = aux_hand.getCardAt(0);
			int a = hand.isInHand(aux_cards[0]); 
			s += a + " ";
			System.out.println("29");
			return s;
		}
		
		
		//30. KT suited
		resetCards(aux_cards);
		
		if(aux_hand.getCardAt(aux_hand.length()-1).getValue() == Card.KING){ //Last card on aux_hand
			aux_cards[0] = aux_hand.getCardAt(aux_hand.length()-1);
			for(int k = aux_hand.length() - 2; k >= 0; k--){ //Starting from the second card from the end
				if(aux_hand.getCardAt(k).getValue() == Card.TEN && aux_hand.getCardAt(k).equalsSuit(aux_cards[0])){
					aux_cards[1] = aux_hand.getCardAt(k);
					
					for(int i = 0; i < aux_cards.length; i++){
						if(aux_cards[i] != null){
							int a = hand.isInHand(aux_cards[i]); 
							if(a != 0){
								s += a + " ";
							}
						}else{
							break;
						}
					}
					System.out.println("30");
					return s;
				}
			}
		}

		
		//31. Jack, Queen or King
		for(int i = 0; i < hand.length(); i++){
			if(hand.getCardAt(i).getValue() == Card.JACK || hand.getCardAt(i).getValue() == Card.QUEEN || hand.getCardAt(i).getValue() == Card.KING){
				int a = i+1;
				System.out.println("31");
				return "h " + a;
			}
		}
		
		
		//32. 4 to an inside straight with no high cards
		for(int i = 0; i < aux_hand.length()-2; i++){
			resetCards(aux_cards);
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
			
			if(counter == 4){
				aux_count = 0;
				for(int j = 0; j < counter; j++){
					int a = hand.isInHand(aux_cards[j]);
					
					if(a != 0){
						s += a + " ";
					}
				}
				System.out.println("32");
				return s;
			}
		}
		
		
		
		//33. 3 to a flush with no high cards
		//No need to verify if we have high cards. The verification was done previously
		for(int i = 0; i < hand.length()-2; i++){
			for(int j = i + 1; j < hand.length()-1; j++){
				if(hand.getCardAt(i).getSuit() == hand.getCardAt(j).getSuit()){
					for(int k = j + 1; k < hand.length(); k++){
						if(hand.getCardAt(i).getSuit() == hand.getCardAt(k).getSuit()){
							int a = i + 1;
							int b = j + 1;
							int c = k + 1;
							System.out.println("33");
							return "h " + a + " " + b + " " + c;
						}
					}
				}
			}
		}
		
		
		//34. Discard everything
		System.out.println("34");
		return "h";
		
		
	}

}
