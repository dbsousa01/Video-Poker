package group18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList; //List or array? :thinking:
import java.util.Scanner;

public class DebugMode extends GameMode{
	String cmd_file;
	String card_file;
	
	LinkedList<String> play = new LinkedList<String>();
	LinkedList<Card> cards = new LinkedList<Card>();
	
	private final static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	private final static String[] suits = {"S", "C", "D", "H"};
	 
	@SuppressWarnings("resource")
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
			input = new Scanner(file_cmd);
			
			while(input.hasNextLine()){ //Reads all lines of the file
				String[] line = input.nextLine().toLowerCase().split(" ");//Separates all chars from a line
				for(String s:line){
					play.addLast(s); //Adds the char to the linked list.
				}
			}
			input = new Scanner(card_file);
			file_cmd = new File(input.nextLine());
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
		System.out.println("You chose the debug mode. Loading game...");
		for(Card c :cards){
			System.out.println(c);
		}
		
	}

}
