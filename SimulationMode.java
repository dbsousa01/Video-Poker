package group18;

import java.util.Arrays;

public class SimulationMode extends GameMode{
	int bet;
	int nbdeals;

	public SimulationMode(String[] args) {
		// TODO Auto-generated constructor stub
		super(args);
	}
	
	public void runner(String[] args, Score score){
		//Checking if simulation mode was called properly and starting it if true.
		if(args.length < 4){
			System.out.println("Error: incorrect number of arguments. Usage -s credit bet nbdeals");
			System.exit(1);
		}
		System.out.println("You chose the simulation mode");
		
		try{
			bet = Integer.parseInt(args[2]);
			nbdeals = Integer.parseInt(args[3]);
		}catch(NumberFormatException ex){
			System.out.println("Wrong input, exiting");
			System.exit(1);
		}
		
		//Finally all verifications of args are done. Initializing variables
		//Variables
		String str;
		toDiscard = new int[hand.length()];
		int i = 0;
		
		for(i = 0; i < nbdeals; i++){
			if(credit > 0){
				deck = new Deck();
				deck.shuffle();
				hand = new Hand(deck, handSize);
				
				bet(bet);
				str = Advice.getAdvice(hand);
				
				for(String aux_str: str.split(" ")){
					if(!(aux_str.equals("h")) && aux_str != (null)){
						int a = Integer.parseInt(aux_str) - 1;
						toDiscard[a] = 0;
					}
				}
				
				Arrays.fill(toDiscard, 1);
				
				for(int j = 0; j < toDiscard.length; j++){
					if(toDiscard[j] == 1){
						hand.replace(toDiscard[j]);
					}
				}
				
				hand.sort();
				
				credit = score.result(hand, bet, credit);
				
			}else{
				score.printStats(Integer.parseInt(args[1]), credit);
				System.out.println("You ran out of credits");
				return;
			}
		}

		score.printStats(Integer.parseInt(args[1]), credit);
	}
}
