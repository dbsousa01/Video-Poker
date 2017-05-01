package group18;

public class Main {

	public static void main(String[] args) {
		//Class variables
		GameMode game = null;
		int opt =0;
		
		//Reading the arguments to see if the program was initialized correctly
		//Initializing the credit variable with the amount requested by the player
		if(args[0].equals("-i")){
			game = new InteractiveMode(args);
			opt = 1;
		}else if(args[0].equals("-d")){
			game = new DebugMode(args);
			opt = 2;
		}else if(args[0].equals("-s")){
			game = new SimulationMode(args);
			opt = 3;
		}else{
			System.out.println("Not enough arguments\nUsage: -i or -d (file) or -s"); //Falta uma cena antes do -i... n�o sei correr programas T-T
			System.exit(-1);
		}
		
		game.runner(args, opt);
		/************************************
		 * ??(TO BE DISCUSSED WITH GROUP.	*
		 * Particularly statistics)			*
		 * 									*
		 * Possible states of the game:		*
		 * 0 - You haven't bet yet.			*
		 * 	   possible plays:				*
		 * 		-bet						*
		 * 		-credit						*
		 * 		-quit						*
		 * 		-statistics					*
		 * 									*
		 * 1 - You have bet.				*
		 * 	   possible plays:				*
		 * 		-deal						*
		 * 		-credit						*
		 * 									*
		 * 2 - The cards have been dealt.	*
		 * 	   possible plays:				*
		 * 		-hold						*
		 * 		-credit						*
		 * 		-advice						*
		 * 		-statistics					*
		 ************************************/
		
		
		/********************************************************************************************************
		*Starting the interface with the player.																*
		*b - Place a bet. Cannot be used after the deal or before the end of the dealer�s turn. 				*
		*Use:	b: bet the same as the last bet or 5 tokens, if there is no previews bet;						*
		*		b i: bet i tokens.																				*
		*																										*
		*??$ - Show the amount of tokens the player has??														*
		*																										*
		*d - Deal the cards. Can only be used in the beginning of a round, after the bet.						*
		*																										*
		*h - Holds the cards in the positions chosen by the player. Can only be used after the deal.			*
		*Use:	h i: keeps the card at position i, replacing the rest with random cards from the remaining deck *
		*			(cards are 1 indexed). Can be used to replace multiple cards by writing multiple numbers,	*
		*			separated by space (" ").																	*
		*																										*
		*a - Asks the computer for the "perfect" strategy.														*
		*																										*
		*s - Prints the statistics of the current game.		
		*
		*q - Quit the game													*
		********************************************************************************************************/
	}
}