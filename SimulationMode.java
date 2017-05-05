package group18;

public class SimulationMode extends GameMode{
	int bet;
	int nbdeals;

	public SimulationMode(String[] args) {
		// TODO Auto-generated constructor stub
		super(args);
		bet = Integer.parseInt(args[2]);
		nbdeals = Integer.parseInt(args[3]);
	}
	
	public void runner(String[] args, Score score){
		System.out.println("You chose the simulation mode");
	}
}
