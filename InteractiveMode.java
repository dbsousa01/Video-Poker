package group18;

/**
 * Class that implements the Interactive game mode, it is an extension of the abstract 
 * class GameMode.
 */
public class InteractiveMode extends GameMode {

	InteractiveMode(String[] args){
		super(args);
	}
	
	/**
	 *Main method of the class, it checks the user inputs and compares them to the actual
	 *state, depending on the input and the current state, a transition between states is
	 *allowed or not. e.g: the user cannot deal or hold any cards if he has not made a
	 *bet yet. 3 different states are taken into consideration.
	 */
	public void runner(String[] args){
		//The program is a cycle that only ends when the player quits. ??Maybe also end when the player has no credits left.
				while(state != hasQuit){
					
					if(player.getCredit() == 0 && state == 0){
						System.out.println("Player has no credit");
						//player.showScore();
						break;
					}
					
					userInput = reader.nextLine().toLowerCase().trim().split("\\s+");
					
					//The first letter of the input should be the command to perform
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
						System.out.println("player's credit is " + player.getCredit());
						break;
						
					case 'd':
						if(state == 0 && player.nbPlays() != 0){
							bet(previousBet);
							state = 1;
						}
						
						if(state == 1){
							//For each new deal, we need to create a new hand for the player.
							//The new hand should come from a newly opened deck, to ensure card randomness.
							player.createHand();
							System.out.println("player's hand: " + player.getHand());
							state = 2;
						}else{
							System.out.println("d: illegal command");
						}
						break;
						
					case 'h':
						hold(player.getHand());
						break;
						
					case 'a':
						if(state == 2){
							System.out.println("player should " + sortString(player.getAdvice()));
						}else{
							System.out.println("a: Illegal command");
						}
						break;
					case 's':
						player.showScore();
						break;
						
					case 'q':
						if(state == 0){
							state = hasQuit;
							//player.showScore();
							System.out.println("Thank you for playing!");
						}else{
							System.out.println(userInput[0] + ": Illegal command");
						}
						
						break;
						
					default:
						System.out.println(input + ": illegal command.");
					}
				}
	}
}
