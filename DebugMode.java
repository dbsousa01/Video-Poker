package group18;

public class DebugMode extends GameMode{
	String cmd_file;
	String card_file;
	public DebugMode(String[] args){
		super(args);
		cmd_file = args[2];
		card_file = args[3];
	}
	
	public void runner(String[] args, Score score){
		System.out.println("You chose the debug mode. Loading game...");
		
	}

}
