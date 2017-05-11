package group18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList; //List or array? :thinking:
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DebugMode extends GameMode{
	String cmd_file;
	String card_file;
	
	LinkedList<String> play = new LinkedList<String>();
	LinkedList<Card> cards = new LinkedList<Card>();
	
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
	
	Integer[] holdUser = new Integer[5];
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
		for(i = 0;i<holdUser.length; i++){
			holdUser[i]= 0;
			holdList[i]=0;
		}
		try{
			Scanner input = new Scanner(cmd_file);
			File file_cmd = new File(input.nextLine());
			input.close();
			input = new Scanner(file_cmd);
			
			while(input.hasNextLine()){ //Reads all lines of the file
				String[] line = input.nextLine().toLowerCase().split("\\s+");//Separates all chars from a line
				for(String s:line){
					play.addLast(s); //Adds the char to the linked list.
				}
			}
			input.close();
			input = new Scanner(card_file);
			file_cmd = new File(input.nextLine());
			input.close();
			input = new Scanner(file_cmd);
			
			while(input.hasNextLine()){ //Reads all lines of the file
				String[] line_card = input.nextLine().split("\\s+");//Separates all chars from a line
				for(String s:line_card){
					for(i=0;i<values.length;i++){ //i has the card number
						if(values[i].equals(s.substring(0,1)))
							break;
					}
					for(j=0;j<suits.length;j++){
						if(suits[j].equals(s.substring(1,2)))
							break;
					}
					cards.addLast(new Card(i,j)); //Adds Card to the linked list.
				}
			}
			input.close();
		}catch(FileNotFoundException ex){ //Catches if the file does not exist
			System.out.println("One of the files was not found, please try again.");
			System.exit(0);
		}
	}
	public void cleanVectors(){
		for(int i = 0;i<holdUser.length; i++){
			holdUser[i]= 0;
			holdList[i]=0;
		}
	}
	public void runner(String[] args, Score score){
		ListIterator<String> itr = play.listIterator();
		ListIterator<Card> card_itr = cards.listIterator();
		
		Integer userBet = 0;
		int betted = 2;
		int delt= 0;
		int aux, i;
		Integer backup = 0;
		int[] vals = new int[5];
		int[] suits = new int[5];
		state = 4;
		
		while(itr.hasNext() && state == 4){ //Parses through the linked list of plays
			userInput = reader.nextLine().toLowerCase().split("\\s+"); // reads input user
			if(userInput.length <= 1 || !(userInput[0].equals("-cmd"))){
				System.out.println("Wrong Input");
			}else{
				switch(userInput[1].charAt(0)){ //user input
				case 'b':
					if(!(itr.next().equals("b"))){ //List does not have a b next
						System.out.println("Unexpected Input. Try Again.");
						itr.previous();
						break;
					}
					try{
						bet = Integer.parseInt(itr.next()); //check if the next element is a number
						if(bet < 1 || bet > 5){
							System.out.println(userInput[1] + ": Illegal amount.");
							break;
						}
						previousBet = bet;
					}catch(NumberFormatException e){
						bet = previousBet;
						itr.previous(); //goes back on the list
					}
					try{
						userBet = Integer.parseInt(userInput[2]); //Check if the user wrote a number
					}catch(ArrayIndexOutOfBoundsException e){
						userBet = previousBet;
					}catch(NumberFormatException e){
						System.out.println("Unexpected Input. Try Again");
						itr.previous();
						break;
					}
					if(userBet.equals(bet)){ //Compares the user bet and the file bet
						if(credit < bet){ //If the credit on startup is not enough, program exits
							System.out.println("Oops. Something went wrong. You can bet in the file but have no funds to do it. Load more credit");
							System.exit(1);
						}
						bet();
						betted = 1;
					}else{
						System.out.println("Unexpected Input. Try Again.");
						itr.previous();
					}
					break;
				case 'h':
					if(!(itr.next().equals("h")) || userInput.length > 7){
						System.out.println("Unexpected Input. Try Again.");
						itr.previous();
						break;
					}
					if(delt==0){
						System.out.println("h: Illegal command");
						break;
					}
					cleanVectors();
					for(i=2;i<userInput.length;i++){
						try{
							aux = Integer.parseInt(userInput[i]);
							this.holdUser[aux-1] = 1; //cards user wants to hold
						}catch(ArrayIndexOutOfBoundsException e){ //end of user input
							break;
						}catch(NumberFormatException e){ //user input not a number
							System.out.println("Unexpected Input. Try Again");
							state = 0;
							break;
						}
					}
					backup = 1;
					while(true){
						try{
							aux = Integer.parseInt(itr.next());
							this.holdList[aux-1] = 1; //card file wants to hold
							backup++;
						}catch(NoSuchElementException e){ //end of the list
							break;
						}catch(NumberFormatException e){ //not a number
							itr.previous();
							break;
						}
					}
					if(state == 0)
						break;
					if(!(Arrays.equals(holdUser,holdList))){ //checks if arrays do not match
						while(!backup.equals(0)){ //if they don't, we need to back on the list
							backup--; //to be able to try again
							itr.previous();
						}
						System.out.println("Unexpected Input. Try Again.");
						break; //the input does not match the file
					}
					for(i =0;i<holdUser.length;i++){
						if(holdUser[i].equals(0)){
							try{
								hand.replace(i,card_itr.next()); //replaces the hand with the card on the card file
							}catch(IndexOutOfBoundsException e){ //card list empty
								System.out.println("Something went terribly wrong!"); //Card list is empty
								System.exit(-1);
							}
						}
					}
					System.out.println("player's hand " + hand);
					hand.sort();
					credit = score.result(hand, bet, credit);
					betted = 0;
					break;
				case 'd':
					if(!(itr.next().equals("d")) || userInput.length > 2){
						System.out.println("Unexpected Input. Try Again");
						itr.previous();
						break;
					}
					if(betted == 2){
						System.out.println("d: Illegal command");
						break;
					}
					if(betted == 0 && score.getPlays() != 0){
						bet(previousBet); //if b is not written before d, we bet previous bet 
					}
					for( i=0;i<=4;i++){//parses through the card list and fills the hand
						vals[i] = card_itr.next().getValue();
						card_itr.previous();//has to go back to get the suit as well
						suits[i]= card_itr.next().getSuit();
					}
					hand.rigHand(vals, suits); //Creates a hand with the cards wanted
					System.out.println("player's hand " + hand);
					delt = 1;
					break;
				case '$':
					if(!(itr.next().equals("$")) || userInput.length > 2){
						System.out.println("Wrong Input. Try Again.");
						itr.previous();
					}else
						Show_credit();
					break;
				case 'a':
					if(!(itr.next().equals("a"))|| userInput.length > 2){
						System.out.println("Unexpected Input. Try Again");
						itr.previous();
						break;
					}
					if(delt == 0){
						System.out.println("a: Illegal command");
						break;
					}
					//Print the advice
					System.out.println("player should "+sortString(Advice.getAdvice(hand)));
					break;
				case 's':
					if(!(itr.next().equals("s")) || userInput.length > 2){
						System.out.println("Unexpected Input. Try Again");
						itr.previous();
						break;
					}
					score.printStats(Integer.parseInt(args[1]), credit);
					break;
				case 'q':
					if(!(itr.next().equals("q")) || userInput.length > 2){
						System.out.println("Wrong Input. Try Again");
						itr.previous();
						break;
					}
					System.out.println("Thank you for playing");
					System.exit(0);
					break;
				default:
					System.out.println("Unexpected Input. Try Again");
				}	
			}
		}
		state = 4;
		System.out.println("File has ended");
	}
}
