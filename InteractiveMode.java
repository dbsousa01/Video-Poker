package group18;

public class InteractiveMode extends GameMode {

	InteractiveMode(String[] args){
		super(args);
	}
	
	public void runner(String[] args, Score score){
		System.out.println("You chose the interactive mode with "+credit+ " credit");
		
		//The program is a cycle that only ends when the player quits. ??Maybe also end when the player has no credits left.
				while(state != hasQuit){
					
					if(credit == 0 && state == 0){
						System.out.println("You have no credit");
						score.printStats(Integer.parseInt(args[1]), credit);
						break;
					}
					
					if(state == 0){
						//For each new play, we need to open a new deck
						//Then we need to shuffle the deck
						deck = new Deck();
						deck.shuffle();
						
						//now we create the initial player's hand and the 5 replacement cards,
						//that will be used to replace the cards chosen by the player.
						hand = new Hand(deck, handSize);
						//hand.sort();
					}
					/* estes prints so ocupam espaço no programa. Não são precisos, too cluttered
					System.out.println("What will you do?");
					
					//Showing the player messages to guide him through the game
					System.out.println("[$] credit");
					
					if(state == 0){
						System.out.println("[b] bet");
						if(score.getPlays() != 0){
							System.out.println("[d] deal");
						}
						System.out.println("[s] statistics");
						System.out.println("[q] quit");
					}
					
					if(state == 1){
						System.out.println("[d] deal");
					}

					if(state == 2){
						System.out.println("[h] hold");
						System.out.println("[a] advice");
					}
					*/
					//First we read the whole line written by the user
					userInput = reader.nextLine().toLowerCase().split(" ");
					
					//Then we take the first word
					if(userInput[0].length() == 1){
						input = userInput[0].charAt(0);
					}else{
						System.out.println(userInput[0] + ": illegal command, choose a valid one.");
						continue;
					}
					
					//After getting input, we process it
					switch(input){
					case 'b':
						bet();
						break;
						
					case '$':
						Show_credit();
						break;
						
					case 'd':
						if(state == 0 && score.getPlays() != 0){
							bet(previousBet);
							state = 1;
						}
						
						if(state == 1){
							System.out.println("player's hand: " + hand);
							state = 2;
						}else{
							System.out.println("d: illegal command");
						}
						break;
						
					case 'h':
						hold(score,hand);
						break;
						
					case 'a':
						System.out.println("Listen here mate");
						break;
						
					case 's':
						score.printStats(Integer.parseInt(args[1]), credit);
						break;
						
					case 'q':
						if(state == 0){
							state = hasQuit;
							//score.printStats(Integer.parseInt(args[1]), credit);
							System.out.println("Thank you for playing!");
						}else{
							System.out.println("You can't quit now. You're locked here with me. :) ");
						}
						
						break;
						
					default:
						System.out.println(input +": illegal command.");
					}
				}
	}
}
