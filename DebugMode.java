package group18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList; //List or array? :thinking:
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Class that extends the abstract class GameMode, it is used to run
 * the debugging of any cmd-file.txt and card-file.txt, printing what the
 * file has and its cards. The files represent past plays.
 *
 */
public class DebugMode extends GameMode{
	String cmd_file;
	String card_file;
	/**
	 * It was used a linked list to store the content of the files.
	 */
	LinkedList<String> play = new LinkedList<String>();
	LinkedList<Card> cards = new LinkedList<Card>();
	
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
	
	Integer [] holdList = new Integer[5];
	 
	DebugMode(String[] args){
		super(args);
		if(args.length <4){
			System.out.println("Not enough arguments.");
			System.exit(-1);
		}
		cmd_file = args[2];
		card_file = args[3];
		
		int i,j;
		
		cleanVectors();
		try{
			Scanner input = new Scanner(cmd_file);
			File file_cmd = new File(input.nextLine()+".txt");
			input.close();
			input = new Scanner(file_cmd);
			/**
			 * Reads all lines of a file, splits them and stores each
			 * command/card read in a linked list. In case of the card file
			 * a Linked List of object cards is used.
			 * @throw FileNotFound in case one of the files does not exist.
			 */
			while(input.hasNextLine()){ 
				String[] line = input.nextLine().toLowerCase().trim().split("\\s+");//Separates all chars from a line
				for(String s:line){
					play.addLast(s);
				}
			}
			input.close();
			input = new Scanner(card_file);
			file_cmd = new File(input.nextLine()+".txt");
			input.close();
			input = new Scanner(file_cmd);
			
			while(input.hasNextLine()){
				String[] line_card = input.nextLine().toUpperCase().trim().split("\\s+");//Separates all chars from a line
				for(String s:line_card){
					for(i=0;i<values.length;i++){ //i has the card number
						if(values[i].equals(s.substring(0,1)))
							break;
					}
					for(j=0;j<suits.length;j++){
						if(suits[j].equals(s.substring(1,2)))
							break;
					}
					cards.addLast(new Card(i,j));
				}
			}
			input.close();
		}catch(FileNotFoundException ex){
			System.out.println("One of the files was not found, please try again.");
			System.exit(0);
		}
	}
	public void cleanVectors(){
		for(int i = 0;i<holdList.length; i++){
			holdList[i]=0;
		}
	}
	public void runner(String[] args, Score score){
		/**
		 * 	Both lists are iterated and its content is used 
		 * to play a normal game.
		 * 	Implemented a switch case to avoid errors and to 
		 * adapt depending on what the file has. Even if the input is the
		 * correct one, it might not be the correct one, depending on
		 * the previous state.
		 */
		ListIterator<String> itr = play.listIterator();
		ListIterator<Card> card_itr = cards.listIterator();
		
		int betted = 2;
		int dealt= 0;
		int out = 0;
		int aux, i;
		
		int[] vals = new int[5];
		int[] suits = new int[5];
		
		String holdhands = new String();
		
		while(itr.hasNext()){ //Parses through the linked list of plays
			if(credit == 0){
				System.out.println("Player has no credit");
				System.exit(0);
			}
			switch(itr.next()){
			case "b":
				try{
					bet = Integer.parseInt(itr.next()); //check if the next element is a number
					System.out.println("\n-cmd b "+bet);
					if(betted == 1){
						bet = previousBet;
						System.out.println("b: Illegal command");
						break;
					}
					if(bet < 1 || bet > 5){ //Checks if it is a valid bet
						System.out.println("b: Illegal amount.");
						break;
					}
					previousBet = bet;
				}catch(NumberFormatException e){
					bet = previousBet; //does not have a number, so previousBet it is.
					System.out.println("\n-cmd b");
					itr.previous(); //goes back on the list
				}
				if(credit < bet){ //If the credit on startup is not enough, program exits
					System.out.println("Oops. Something went wrong. You can bet in the file but have no funds to do it. Load more credit");
					System.exit(1);
				}
				bet(bet);
				betted = 1;
				state = 1;
				break;
			case "h":
				out = 0;
				holdhands = "-cmd h";
				cleanVectors();
				while(true){
					try{
						aux = Integer.parseInt(itr.next());
						holdhands += " "+aux; 
						this.holdList[aux-1] = 1; //card file wants to hold
					}catch(NoSuchElementException e){ //end of the list
						break;
					}catch(NumberFormatException e){ //not a number
						itr.previous();
						break;
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println("\n"+ holdhands);
						System.out.println("h: Invalid command");
						out = 1;
						break;
					}
				}
				if(dealt==0){
					System.out.println("\n"+ holdhands);
					System.out.println("h: Illegal command");
					break;
				}
				if(out == 1)
					break;
				System.out.println("\n"+ holdhands);
				state = 0;
				for(i =0;i<holdList.length;i++){
					if(holdList[i].equals(0)){
						try{
							hand.replace(i,card_itr.next()); //replaces the hand with the card on the card file
						}catch(IndexOutOfBoundsException e){ //Card list is empty
							System.out.println("Something went terribly wrong!");
							System.exit(-1);
						}catch(NoSuchElementException e){ //not enough cards on the file
							System.out.println("Something went terribly wrong!");
							System.exit(-1);
						}
					}
				}
				System.out.println("player's hand " + hand);
				hand.sort();
				credit = score.result(hand, bet, credit);
				betted = 0;
				break;
			case "d":
				System.out.println("\n-cmd d");
				if(state == 0 && score.getPlays() != 0){ //if there is no bet previously
					bet(previousBet);
					state = 1;
				}else if(state != 1){
					System.out.println("d: illegal command");
					break;
				}
				for( i=0;i<=4;i++){//parses through the card list and fills the hand
					vals[i] = card_itr.next().getValue();
					card_itr.previous();//has to go back to get the suit as well
					suits[i]= card_itr.next().getSuit();
				}
				hand.rigHand(vals, suits); //Creates a hand with the cards wanted
				System.out.println("player's hand " + hand);
				dealt = 1;
				state = 2;
				break;
			case "$":
				System.out.println("\n-cmd $");
				Show_credit();
				break;
			case "a":
				System.out.println("\n-cmd a");
				if(dealt == 0){ //only prints the advice if the cards have been dealt
					System.out.println("a: Illegal command");
					break;
				}
				//Prints the advice
				System.out.println("player should "+sortString(Advice.getAdvice(hand)));
				break;
			case "s":
				System.out.println("\n-cmd s");
				score.printStats(Integer.parseInt(args[1]), credit);
				break;
			case "q":
				System.out.println("\n-cmd q");
				System.out.println("Thank you for playing");
				System.exit(0);
				break;
			default:
				System.out.println("Unexpected Input. Try Again");
			}	
		}
	}
}
