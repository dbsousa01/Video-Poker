package group18;

/**
 * Class that stores everything related to the player's score. Calculates it based on
 * the player's result and creates the statistics table so the player can successfully
 * Analyze its results.
 * It has an array of ints, used to store the amount of a specific play that has happened,
 * the overall number of games played and auxiliary strings to aid implementation in other
 * classes.
 */
public class Score {
	
	//Variables
	//{Jacks or better, Two pairs, Three of a kind, Straight, Flush, Full house, 4 of a kind, Straight flush, Royal flush, other}
	int[] stats = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
	int plays = 0;
	String output;
	String resultGUI;
	
	/**
	 * Method that prints the statistics of the player so far.
	 * @param iniCredit The amount of credit that the player has started with.
	 * @param credit The current player's credit.
	 */
	public void printStats(int iniCredit, int credit){
		
		//Used only in the GUI mode. used because of indentation.
		resultGUI = "  Hand                        nb,  Jacks or better        "+stats[0]
				+",  Two pairs                 "+stats[1]
				+",  Three of a kind        "+stats[2]
				+",  Straight                     "
				+stats[3]+",  Flush                         "+stats[4]
				+",  Full house                "
				+stats[5]
				+",  Four of a kind           "+stats[6]
				+",  Straight flush            "+stats[7]
				+",  Royal flush                "+stats[8]+",  Other                          "
				+stats[9]
				+",  Total                           "+plays
				+",   Credit                    "+credit+" "
				+"  ("+((credit*100)/iniCredit) + "%)";
		
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
		System.out.println("\nCredit        " + credit + " " + ((credit*100)/iniCredit) + "%");
		
	}
	
	/**
	 * Getter of the number of games played.
	 * @return the overall number of plays.
	 */
	public int getPlays(){
		return this.plays;
	}
	
	/**
	 * Method that, based on the bet made and on the type of hand that the player has
	 * prints the result. If the player wins, it increments its due credit of the play
	 * and it prints a message saying he won and with what type of hand.In case of loss
	 * a simple message of "player lost" appears.
	 * @param hand The current hand of the player, to check if has any type of combination.
	 * @param bet The bet made by the player, it defines the return value in case of a win.
	 * @param credit The credit the player has, to be incremented in case of a win.
	 * @return Returns the player's total credit.
	 */
	public int result(Hand hand, int bet, int credit){
	
		String type_hand = new String();
		
		plays++;
		
		switch(hand.isCombination() ){
		case 11:
			type_hand = "ROYAL FLUSH";
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
			type_hand = "STRAIGHT FLUSH";
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
			type_hand = "FOUR ACES";
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
			type_hand = "HIGH FOUR OF A KIND";
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
			type_hand = "LOW FOUR OF A KIND";
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
			type_hand = "FULL HOUSE";
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
			type_hand = "FLUSH";
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
			type_hand = "STRAIGHT";
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
			type_hand = "THREE OF A KIND";
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
			type_hand = "TWO PAIR";
			stats[1]++;
			
			credit += bet;
			
			break;
			
		case 1:
			type_hand = "JACKS OR BETTER";
			stats[0]++;
			
			credit += bet;
			
			break;
			
		default:
			stats[9]++;
			output = "player loses";
			System.out.println("player loses and your credit is " + credit + ".");
			return credit;
		}
		output = "player wins with a "+type_hand;
		System.out.println("player wins with a " + type_hand + " and now his credit is " + credit + "!");
		return credit;
	}
}

	