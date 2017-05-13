package group18;
/**
 * Class that represents the player. it stores everything related to the player, the credit
 * of the player, the score of the player the hand of the player.
 */
public class Player {

	private Hand player_hand;
	private int player_credit;
	final int ini_credit;
	Score player_score;
	
	/**
	 * Constructor. initializes all variables of the class, the credit, the player's hand
	 * and his score.
	 * @param credit initial credit of the player.
	 */
	public Player(int credit){
		player_hand = new Hand(5);
		player_credit = credit;
		ini_credit = player_credit;
		player_score = new Score();
	}
	
	/**
	 * Method that creates the player's hand.
	 */
	public void createHand(){
		Deck deck = new Deck();
		deck.shuffle();
		
		player_hand = new Hand(deck, 5);
	}
	/**
	 * Getter.
	 * @return the player's hand.
	 */
	public Hand getHand(){
		return player_hand;
	}
	/**
	 * Getter.
	 * @return the player's credit.
	 */
	public int getCredit(){
		return player_credit;
	}
	/**
	 * Setter that updates the player's credit.
	 * @param amount new player's credit.
	 */
	public void setCredit(int amount){
		player_credit = amount;
	}
	/**
	 * Method that prints the result of the game.
	 */
	public void getResultGUI(){
		this.player_score.getResult(ini_credit, player_credit);
	}
	/**
	 * Method that prints the player's score.
	 */
	public void showScore(){
		this.player_score.printStats(ini_credit, player_credit);
	}
	/**
	 * Getter.
	 * @return the player's score.
	 */
	public Score getScore(){
		return this.player_score;
	}
	/**Getter.
	 * @return the number of plays made by the player.
	 */
	public int nbPlays(){
		return player_score.getPlays();
	}
	/**
	 * Getter.
	 * @return the string output saying the result of a play. Win or lose.
	 */
	public String getOutput(){
		return player_score.outputGUI;
	}
	/**
	 * Method that updates the player's credit after a bet
	 * @param amount the integer that to discount from the player's credit.
	 */
	public void takeCredit(int amount){
		player_credit -= amount;
	}
	/**
	 * Getter.
	 * @return the advice, as a string, on what the player should do next.
	 */
	public String getAdvice(){
		String s = "";
		
		for(Advice ad: Advice.values()){
			if((s = ad.getStrategy(this.player_hand)) != null){
				return "h " + s;
			}
		}
		return "h";
	}
	
	/**
	 * Method that prints the output text of hand's result.
	 * @return string that contains the output text
	 */
	public String printOutput(){
		return player_score.outputText;
	}
	
	/**
	 * Method that calls the hand checker, to see if the player's hand has any of the 
	 * combinations.
	 * @param bet made by the player, to calculate the return value in case of a win.
	 * @return returns the result of the player's hand, did he win with what type of hand
	 * and how much did he win.
	 */
	public int evaluateHand(int bet){
		//Auxiliary variable
		Hand sorted = new Hand(player_hand.length());
		
		//Creating an auxiliary hand with the player's cards to sort
		sorted.rigHand(this.getHand().getCards());
		sorted.sort();
		
		return player_score.result(sorted, bet, player_credit);
	}
	
}