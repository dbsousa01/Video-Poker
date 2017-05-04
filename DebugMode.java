package group18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList; //List or array? :thinking:
import java.util.ListIterator;
import java.util.Scanner;

public class DebugMode extends GameMode{
	String cmd_file;
	String card_file;
	
	LinkedList<String> play = new LinkedList<String>();
	LinkedList<Card> cards = new LinkedList<Card>();
	
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
	 
	public DebugMode(String[] args){
		super(args);
		if(args.length <4){
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		cmd_file = args[2];
		card_file = args[3];
		
		int i,j;
		try{
			Scanner input = new Scanner(cmd_file);
			File file_cmd = new File(input.nextLine());
			input.close();
			input = new Scanner(file_cmd);
			
			while(input.hasNextLine()){ //Reads all lines of the file
				String[] line = input.nextLine().toLowerCase().split(" ");//Separates all chars from a line
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
				String[] line_card = input.nextLine().split(" ");//Separates all chars from a line
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
			System.out.println("One of the files was not found, please try again");
			System.exit(0);
		}
	}
	
	public void runner(String[] args, Score score){
		ListIterator<String> itr = play.listIterator();
		ListIterator<Card> card_itr = cards.listIterator();
		
		Integer userBet = 0;
		int betted = 0;
		int[] vals = new int[5];
		int[] suits = new int[5];
		state = 4;
		
		while(itr.hasNext()){ //Parses through the linked list of plays
			while(state == 4){
				userInput = reader.nextLine().toLowerCase().split(" "); // reads input user
				if(userInput.length <= 1 || !(userInput[0].equals("-cmd"))){
					System.out.println("Wrong Input");
				}else{
					switch(userInput[1].charAt(0)){ //user input
					case 'b':
						if(!(itr.next().equals("b"))){ //List does not have a b next
							System.out.println("Unexpected Input. Try Again");
							itr.previous();
							break;
						}
						try{
							bet = Integer.parseInt(itr.next()); //check if the next element is a number
							previousBet = bet;
						}catch(NumberFormatException e){
							bet = previousBet;
							itr.previous(); //goes back on the list
						}
						try{
							userBet = Integer.parseInt(userInput[2]); //Check if the user wrote a number
						}catch(ArrayIndexOutOfBoundsException e){
							userBet = previousBet;
						}
						if(userBet.equals(bet)){
							if(credit < bet){
								System.out.println("Oops. Something went wrong. You can bet in the file but have no funds to do it. Load more credit");
								System.exit(1);
							}
							credit-=bet;
							bet();
							state = 1;
							betted = 1;
						}else{
							System.out.println("Unexpected Input. Try Again");
							itr.previous();
						}
						break;
					case 'd':
						if(!(itr.next().equals("d")) || userInput.length > 2){
							System.out.println("Unexpected Input. Try Again");
							itr.previous();
							break;
						}
						if(betted == 1 && score.getPlays() != 0)
							bet(previousBet);
						for(int i=0;i<=4;i++){
							vals[i] = card_itr.next().getValue();
							card_itr.previous();
							suits[i]= card_itr.next().getSuit();
						}
						hand.rigHand(vals, suits);
						System.out.println("player's hand " + hand);
						break;
					case 'h':
						if(!(itr.next().equals("h")) || userInput.length > 7){
							System.out.println("Unexpected Input. Try Again");
							itr.previous();
							break;
						}
						System.out.println("player's hand " + hand);
						hand.sort();
						credit = score.result(hand, bet, credit);
						break;
					case '$':
						if(!(itr.next().equals("$")) || userInput.length > 2){
							System.out.println("Wrong Input. Try Again");
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
						System.out.println("The advice is...");
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
		}
		System.out.println("File has ended");
	}
}
