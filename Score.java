package group18;

public class Score {

	//This class handles everything relative to scores: calculating it, defining the best strategy and statistics.
	//
	//DONE: calculating points;
	//		Statistics;
	//
	
	//Macros
	
	//Auxiliary variables
	
	//Variables
	int[] stats = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //{Jacks or better, Two pairs, Three of a kind, Straight, Flush, Full house, 4 of a kind, Straight flush, Royal flush, other <- what does this mean?}
	int plays = 0;
	
	
	public void printStats(int iniCredit, int credit){

		System.out.println("\nHand            nb");
		System.out.println("______________________");
		System.out.println("\nJacks or better  " + stats[0]);
		System.out.println("Two pairs        " + stats[1]);
		System.out.println("Three of a kind  " + stats[2]);
		System.out.println("Straight         " + stats[3]);
		System.out.println("Flush            " + stats[4]);
		System.out.println("Full house       " + stats[5]);
		System.out.println("Four of a kind   " + stats[6]);
		System.out.println("Straight flush   " + stats[7]);
		System.out.println("Royal flush      " + stats[8]);
		System.out.println("Other            " + stats[9]);
		System.out.println("______________________");
		System.out.println("\nTotal            " + plays);
		System.out.println("______________________");
		System.out.println("\nCredit        " + credit + " " + ((credit*100)/iniCredit) + "%\n");
		
	}
	
	
	public int getPlays(){
		return this.plays;
	}
	
	public int result(Hand hand, int bet, int credit){
	
		String type_hand = "";
		
		plays++;
		
		switch( hand.isCombination() ){
		case 11:
			type_hand = "Royal Flush";
			stats[8]++;
			
			if(bet == 1)
				credit += 250;
			if(bet == 2)
				credit += 500;
			if(bet == 3)
				credit += 750;
			if(bet == 4)
				credit += 1000;
			if(bet == 5)
				credit += 4000;
			break;
			
		case 10:
			type_hand = "Straight Flush";
			stats[7]++;
			
			if(bet == 1)
				credit += 50;
			if(bet == 2)
				credit += 100;
			if(bet == 3)
				credit += 150;
			if(bet == 4)
				credit += 200;
			if(bet == 5)
				credit += 250;
			break;
			
		case 9:
			type_hand = "Four Aces";
			stats[6]++;
			
			if(bet == 1){
				credit += 160;
			}else if(bet == 2){
				credit += 320;
			}else if(bet == 3){
				credit += 480;
			}else if(bet == 4){
				credit += 640;
			}else if(bet == 5){
				credit += 800;
			}
			
			break;
			
		case 8:
			type_hand = "High Four of a kind";
			stats[6]++;
			
			if(bet == 1){
				credit += 80;
			}else if(bet == 2){
				credit += 160;
			}else if(bet == 3){
				credit += 240;
			}else if(bet == 4){
				credit += 320;
			}else if(bet == 5){
				credit += 400;
			}
			
			break;
			
		case 7:
			type_hand = "Low four of a kind";
			stats[6]++;
			
			if(bet == 1){
				credit += 50;
			}else if(bet == 2){
				credit += 100;
			}else if(bet == 3){
				credit += 150;
			}else if(bet == 4){
				credit += 200;
			}else if(bet == 5){
				credit += 250;
			}
			
			break;
			
		case 6:
			type_hand = "Full House";
			stats[5]++;
			
			if(bet == 1){
				credit += 10;
			}else if(bet == 2){
				credit += 20;
			}else if(bet == 3){
				credit += 30;
			}else if(bet == 4){
				credit += 40;
			}else if(bet == 5){
				credit += 50;
			}
			
			break;
			
		case 5:
			type_hand = "Flush";
			stats[4]++;
			if(bet == 1){
				credit += 7;
			}else if(bet == 2){
				credit += 14;
			}else if(bet == 3){
				credit += 21;
			}else if(bet == 4){
				credit += 28;
			}else if(bet == 5){
				credit += 35;
			}
			
			break;
			
		case 4:
			type_hand = "Straight";
			stats[3]++;
			
			if(bet == 1){
				credit += 5;
			}else if(bet == 2){
				credit += 10;
			}else if(bet == 3){
				credit += 15;
			}else if(bet == 4){
				credit += 20;
			}else if(bet == 5){
				credit += 25;
			}
			
			break;
			
		case 3:
			type_hand = "Three of a Kind";
			stats[2]++;
			
			if(bet == 1){
				credit += 3;
			}else if(bet == 2){
				credit += 6;
			}else if(bet == 3){
				credit += 9;
			}else if(bet == 4){
				credit += 12;
			}else if(bet == 5){
				credit += 15;
			}
			
			break;
			
		case 2:
			type_hand = "Two Pair";
			stats[1]++;
			
			credit += bet;
			
			break;
			
		case 1:
			type_hand = "Jacks or Better";
			stats[0]++;
			
			credit += bet;
			
			break;
			
		default:
			System.out.println("You lost and your credit is " + credit + ".");
			return credit;
		}
		
		System.out.println("You won with a " + type_hand + " and now your credit is " + credit + "!");
		
		return credit;
	}
	
	
	
	
	//public int[] strategy(Hand hand){
		
		//return new int[]{0 0};
	//}
}

	