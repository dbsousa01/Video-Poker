package group18;

import java.util.Arrays;
/**
 * Class that extends the abstract class GameMode. It plays the game alone, trying always to 
 * make the best possible choice in order to achieve the best return. It only ends if
 * the number of deals chosen by the user are met or if the player runs out of credit to
 * continue betting.
 */
public class SimulationMode extends GameMode{
	int nbdeals;
	
	/**
	 * Constructor of the class, it checks if the arguments provided by the user are valid.
	 * @param args
	 */
	public SimulationMode(String[] args) {
		super(args);
		//Checking if simulation mode was called properly and starting it if true.
		if(args.length < 4){
			System.out.println("Error: incorrect number of arguments. Usage -s credit bet nbdeals");
			System.exit(1);
		}
		
		try{
			bet = Integer.parseInt(args[2]);
			if(bet < 1 || bet > 5){
				System.out.println("Invalid bet. Please chose a value between 1 and 5.");
				System.exit(1);
			}
			nbdeals = Integer.parseInt(args[3]);
		}catch(NumberFormatException ex){
			System.out.println("Wrong input, exiting");
			System.exit(1);
		}
	}
	/**
	 * Main method of the class. It bets every time the value chosen by the user, deals
	 * and always asks for the advice and chooses every time whatever the advice tells it
	 * to do
	 */
	public void runner(String[] args){
		//Variables
		String str;
		toDiscard = new int[player.getHand().length()];
		
		for(int i = 0; i < nbdeals; i++){
			
			if(player.getCredit() > 0){ // checks if there is enough credit
				player.createHand();
				System.out.println(player.getHand());
				bet(bet);
				str = player.getAdvice();
				
				Arrays.fill(toDiscard, 1);
				
				for(String aux_str: str.split(" ")){
					if(!(aux_str.equals("h")) && aux_str != (null)){
						int a = Integer.parseInt(aux_str) - 1;
						toDiscard[a] = 0;
					}
				}
				
				for(int j = 0; j < toDiscard.length; j++){
					if(toDiscard[j] == 1){
						player.getHand().replace(toDiscard[j]);
					}
				}
				
				player.getHand().sort();
				player.setCredit(player.evaluateHand(bet));
				
			}else{
				System.out.println("You ran out of credits");
				break;
			}
		}

		player.showScore();
	}
}