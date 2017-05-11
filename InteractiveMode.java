package group18;

public class InteractiveMode extends GameMode {

	InteractiveMode(String[] args){
		super(args);
	}
	
	public void runner(String[] args, Score score){
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
						//now we create the initial player's hand and the 5 replacement cards,
						//that will be used to replace the cards chosen by the player.
						createDeck();
					}
					
					userInput = reader.nextLine().toLowerCase().trim().split("\\s+");
					
					//Then we take the first word
					if(userInput[0].length() == 1){
						input = userInput[0].charAt(0);
					}else{
						System.out.println(userInput[0] + ": illegal command.");
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
						if(state == 2){
							System.out.println("player should "+sortString(Advice.getAdvice(hand)));
						}else{
							System.out.println("a: Illegal command");
						}
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
							System.out.println(userInput[0] + ": Illegal command");
						}
						
						break;
						
					default:
						System.out.println(input +": illegal command.");
					}
				}
	}
}
