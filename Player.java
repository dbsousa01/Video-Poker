package group18;

public class Player {

	private Hand player_hand;
	private int player_credit;
	final int ini_credit;
	Score player_score;
	
	
	public Player(int credit){
		player_hand = new Hand(5);
		player_credit = credit;
		ini_credit = player_credit;
		player_score = new Score();
	}
	
	
	public void createHand(){
		
		Deck deck = new Deck();
		deck.shuffle();
		
		player_hand = new Hand(deck, 5);
	}
	
	
	public Hand getHand(){
		return player_hand;
	}
	
	
	public int getCredit(){
		return player_credit;
	}
	
	public void setCredit(int amount){
		player_credit = amount;
	}
	
	
	public void showScore(){
		this.player_score.printStats(ini_credit, player_credit);
	}
	
	public Score getScore(){
		return this.player_score;
	}
	
	public int nbPlays(){
		return player_score.getPlays();
	}
	
	public String getOutput(){
		return player_score.output;
	}
	
	public void takeCredit(int amount){
		player_credit -= amount;
	}
	
	
	public String getAdvice(){
		String s = "";
		
		for(Advice ad: Advice.values()){
			if((s = ad.getStrategy(this.player_hand)) != null){
				return "h " + s;
			}
		}
		return "h";
	}

	
	public int evaluateHand(int bet){
		//Auxiliary variable
		Hand sorted = new Hand(player_hand.length());
		
		//Creating an auxiliary hand with the player's cards to sort
		sorted.rigHand(this.getHand().getCards());
		sorted.sort();
		
		return player_score.result(sorted, bet, player_credit);
	}
	
}