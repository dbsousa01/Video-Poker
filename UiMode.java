package group18;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
/**
 * Class that extends the abstract class GameMode.
 * It implements a GUI using the Swing library, based on a Spring layout. It mimics
 * the interactive mode but with a user friendly GUI.
 * All images should be placed in the src folder of the java program in the directory
 *  /Cards/...png
 */

public class UiMode extends GameMode{
	//Macros
	public final static int Cardpxx = 90;
	public final static int Cardpxy = 135;
	
	//Frames
	JFrame Fframe;
	JFrame mainFrame;
	
	//Panels
	JPanel onpanel;
	JPanel cardPanel;
	SpringLayout layout;
	
	//Text caught
	String textCredit;
	String textBet;
	
	//Buttons
	JToggleButton[] holdArray;
	JButton[] mainButtons;
	
	//Labels
	JLabel[] handLabel;
	JLabel creditLabel;
	
	//TextFields
	JTextField credit_text;
	JTextField bet_text;
	JTextField credit_disp;
	JTextField adviceText;
	JTextField result;
	JTextField[] stats;
	
	//Images
	private Image cback;
	
	String betString;
	
	/**
	 * Constructor of the class. Creates an initial panel where the player can place
	 * its initial credit leading him to the main panel where the rest of the game is placed.
	 * Initiates every kind of auxiliary array used by the class and places all the
	 * buttons meant to be used by the player and the cards which represent the player's
	 * hand. Before dealing, all cards are faced laying down.
	 */
	public UiMode(String[] args){
		super(args);
		
		if(args.length > 1){
			System.out.println("Invalid arguments");
			System.exit(0);
		}
		//initializing first panel screen
		placeInitial();
		//initializing second and main panel screen
		placeMain();
		//placing card backs and initializing card places
		try{
			cback = ImageIO.read(getClass().getResource("/Cards/back.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		handLabel = new JLabel[5];
		for(int i = 0; i<handLabel.length;i++){
			handLabel[i] = new JLabel(new ImageIcon(cback.getScaledInstance(Cardpxx,Cardpxy,Image.SCALE_SMOOTH)));
		}
		//Sets the positions of the cards and the hold toggle
		setCards(handLabel);
		//place Buttons
		placeButtons();
	}
	
	public void placeInitial(){
		JButton playButton = new JButton("Play");
		//Creates a frame titled Video Poker
		Fframe = new JFrame("Video Poker");
		Fframe.setVisible(false);
		Fframe.setSize(350,200);
		Fframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Fframe.setResizable(false);
		
		JPanel panel_start = new JPanel();
		Fframe.add(panel_start);
		Fframe.setLayout(null);
		//A label Credit and a text box following it, where the player writes the
		//credit he wishes to start with.
		creditLabel = new JLabel("Credit:");
		creditLabel.setBounds(30, 20, 40, 25);
		Fframe.add(creditLabel);
		
		credit_text = new JTextField(20);
        credit_text.setBounds(100,20,165,25);
        Fframe.add(credit_text);
        
        playButton.setBounds(100, 60, 80, 25);
        Fframe.add(playButton);
		
        //places the button which leads to the beginning of the game.
        playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				textCredit = credit_text.getText().trim();
				try{
					player.setCredit(Integer.parseInt(textCredit));
					player = new Player(Integer.parseInt(textCredit));
					if(player.getCredit() <= 0){
						System.out.println("Credit is invalid.");
						return;
					}
					updateCredit();
					mainFrame.setVisible(true);
					Fframe.setVisible(false);
					state = 0;
				}catch(NumberFormatException e){
					System.out.println("Not a valid argument.");
				}
			}
		});
	}
	
	/**
	 * Method that places the main Frame and adds all that is needed to play a normal game.
	 */
	public void placeMain(){
		layout = new SpringLayout();
		Container contentPane;
		
		mainFrame = new JFrame("Video Poker");
		mainFrame.setVisible(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1000,600);
		mainFrame.setResizable(false);
		
		contentPane = mainFrame.getContentPane();
		contentPane.setLayout(layout);
		credit_disp = new JTextField(String.valueOf(player.getCredit()));
		credit_disp.setEditable(false);
		//A displayer with the player's credit
		creditLabel = new JLabel("Credit: ");
		contentPane.add(creditLabel);
		layout.putConstraint(SpringLayout.WEST,creditLabel,730,SpringLayout.WEST,contentPane);
		layout.putConstraint(SpringLayout.NORTH,creditLabel,22,SpringLayout.NORTH,contentPane);
		
		contentPane.add(credit_disp);
		layout.putConstraint(SpringLayout.WEST,credit_disp,10,SpringLayout.EAST,creditLabel);
		layout.putConstraint(SpringLayout.NORTH,credit_disp,20,SpringLayout.NORTH,contentPane);
		
		//A text box where the result of a hand is printed.
		result = new JTextField("This will print the player's result. Did he lose? Did he win?");
		result.setEditable(false);
		result.setVisible(false);
		result.setSize(100, 20);
		contentPane.add(result);
		layout.putConstraint(SpringLayout.WEST,result,300,SpringLayout.WEST,contentPane);
		layout.putConstraint(SpringLayout.NORTH,result,20,SpringLayout.NORTH,contentPane);
		
	}
	
	/**
	 * Method that places the cards on the panel and its respective hold toggle buttons.
	 * Initially all cards are loaded up facing backwards and then its images 
	 * are updated depending on the drawn cards.
	 * @param cards Array that represents all 5 cards of the player's hand.
	 */
	public void setCards(JLabel[] cards){
		Container cardContainer = mainFrame.getContentPane();
		int i;
		holdArray = new JToggleButton[5];
		
		cardContainer.setLayout(layout);
		for(i=0;i<cards.length;i++){
			holdArray[i] = new JToggleButton("Hold");
			cardContainer.add(cards[i]);
			cardContainer.add(holdArray[i]);
		}
		layout.putConstraint(SpringLayout.WEST,cards[0],100,SpringLayout.WEST,cardContainer);
		layout.putConstraint(SpringLayout.NORTH,cards[0],100,SpringLayout.NORTH,cardContainer);
		layout.putConstraint(SpringLayout.NORTH,holdArray[0],10,SpringLayout.SOUTH,cards[0]);
		layout.putConstraint(SpringLayout.WEST,holdArray[0],115,SpringLayout.WEST,cardContainer);
		holdArray[0].setEnabled(false);
		for(i=1;i<cards.length;i++){
			layout.putConstraint(SpringLayout.WEST,cards[i],40,SpringLayout.EAST,cards[i-1]);
			layout.putConstraint(SpringLayout.NORTH,cards[i],100,SpringLayout.NORTH,cardContainer);
			layout.putConstraint(SpringLayout.NORTH,holdArray[i],10,SpringLayout.SOUTH,cards[i]);
			layout.putConstraint(SpringLayout.WEST,holdArray[i],130,SpringLayout.WEST,holdArray[i-1]);
			holdArray[i].setEnabled(false);
		}
		stats = new JTextField[13];
		stats[0] = new JTextField("Jacks or better  0         make room           ");
		stats[0].setEditable(false);
		stats[0].setVisible(false);
		cardContainer.add(stats[0]);
		layout.putConstraint(SpringLayout.WEST,stats[0],120,SpringLayout.WEST,cards[i-1]);
		layout.putConstraint(SpringLayout.NORTH,stats[0],100,SpringLayout.NORTH,cardContainer);
		for(i = 1;i<13;i++){
			stats[i] = new JTextField("Jacks or better  0              make room      ");
			stats[i].setEditable(false);
			stats[i].setVisible(false);
			cardContainer.add(stats[i]);
			layout.putConstraint(SpringLayout.SOUTH,stats[i],20,SpringLayout.SOUTH,stats[i-1]);
			layout.putConstraint(SpringLayout.WEST,stats[i],740,SpringLayout.WEST,cardContainer);
		}
	}
	
	/**
	 * Method that places all the main Buttons for the player to use, The button bet,
	 * deal, hold, advice.
	 */
	public void placeButtons(){
		mainButtons = new JButton[4];
		Container buttonContainer = mainFrame.getContentPane();
		String[] buttonsName = {"Bet","Hold","Deal","Advice"};
		int i;
		
		buttonContainer.setLayout(layout);
		for(i=0;i<mainButtons.length;i++){
			mainButtons[i] = new JButton(buttonsName[i]);
			buttonContainer.add(mainButtons[i]);
		}
		layout.putConstraint(SpringLayout.WEST,mainButtons[0],115,SpringLayout.WEST,buttonContainer);
		layout.putConstraint(SpringLayout.NORTH,mainButtons[0],330,SpringLayout.NORTH,buttonContainer);
		
		bet_text = new JTextField(5);
		buttonContainer.add(bet_text);
		adviceText = new JTextField(30);
		buttonContainer.add(adviceText);
		adviceText.setEditable(false);
		layout.putConstraint(SpringLayout.WEST,bet_text,70,SpringLayout.WEST,mainButtons[0]);
		layout.putConstraint(SpringLayout.NORTH,bet_text,335,SpringLayout.NORTH,buttonContainer);
		for(i =1;i<mainButtons.length-1;i++){
			layout.putConstraint(SpringLayout.WEST,mainButtons[i],(i*130),SpringLayout.WEST,bet_text);
			layout.putConstraint(SpringLayout.NORTH,mainButtons[i],330,SpringLayout.NORTH,buttonContainer);
			mainButtons[i].setEnabled(false);
		}
		layout.putConstraint(SpringLayout.SOUTH,mainButtons[i],100,SpringLayout.SOUTH,mainButtons[i-1]);
		layout.putConstraint(SpringLayout.WEST,mainButtons[i],110,SpringLayout.WEST,buttonContainer);
		layout.putConstraint(SpringLayout.WEST,adviceText,90,SpringLayout.WEST,mainButtons[i]);
		layout.putConstraint(SpringLayout.NORTH,adviceText,435,SpringLayout.NORTH,buttonContainer);
		mainButtons[i].setEnabled(false);
	}
	
	/**
	 * Method that sets what each button is meant to do when pressed. E.g: 
	 * when the bet button is pressed, what is in the bet box is read and that value
	 * is betted.
	 * @param score is used to update the game's score after a hold command.
	 */
	public void setButtons(Player score){
		mainButtons[0].addActionListener(new ActionListener(){ //Bet button
			 public void actionPerformed(ActionEvent event){
				 if(player.getCredit() == 0){
					 System.out.println("Player has no credit");
					 System.exit(0);
				 }
			 	betString = "b "+bet_text.getText();
			 	userInput = betString.trim().split("\\s+");
				bet();
				updateCredit();
				if(player.getCredit()<0){
					mainFrame.setVisible(false);
				}
				result.setVisible(false);
				mainButtons[0].setEnabled(false);
				bet_text.setEnabled(false);
				mainButtons[2].setEnabled(true);
				adviceText.setText("");
				}
			});
		mainButtons[1].addActionListener(new ActionListener() { //Hold button
			public void actionPerformed(ActionEvent event){
				String hold = new String("h");
				for(int i =0;i<holdArray.length;i++){
					if(holdArray[i].isSelected()){
						hold += " "+(i+1);
						holdArray[i].doClick();
					}
					holdArray[i].setEnabled(false);
				}
				System.out.println(hold);
				userInput = hold.trim().split("\\s+");
				hold(player.getHand());
				mainButtons[3].setEnabled(false);
				updateCredit();
				updateImageHand();
				result.setText(score.getOutput());
				result.setVisible(true);
				mainButtons[1].setEnabled(false);
				mainButtons[0].setEnabled(true);
				mainButtons[2].setEnabled(true);
				adviceText.setText("");
				bet_text.setEnabled(true);
				updateStats();
			}
		});
		
		mainButtons[2].addActionListener(new ActionListener(){ //Deal button
			public void actionPerformed(ActionEvent event){
				player.createHand();
				if(state == 0 && score.nbPlays() != 0){
					mainButtons[0].setEnabled(false);
					bet_text.setEnabled(false);;
					bet(previousBet);
					updateCredit();
					result.setVisible(false);;
					adviceText.setText("");
					state = 1;
				}
				System.out.println("player's hand: " + player.getHand());
				
				updateImageHand();
				mainButtons[1].setEnabled(true);
				mainButtons[2].setEnabled(false);
				mainButtons[3].setEnabled(true);
				state = 2;
			}
		});
		mainButtons[3].addActionListener(new ActionListener() { //Advice button
			public void actionPerformed(ActionEvent event){
				adviceText.setText("player should "+sortString(player.getAdvice()));
			}
		});
	}
	
	/**
	 * Method that updates the table statistics based on what has been played so far.
	 * @param score used to access the string result that has the table score.
	 */
	public void updateStats(){
		String[] resultText = new String[13];
		player.showScore();
		
		for(int i= 0;i<resultText.length;i++){
			resultText[i] = new String();
		}
		resultText = player.player_score.resultGUI.split(",");
		for(int i= 0;i<resultText.length;i++){
			stats[i].setText(resultText[i]);
			stats[i].setVisible(true);
		}
	}
	/*
	 * Method that updates the player's card image. 
	 */
	public void updateImageHand(){
		Image[] cards = new Image[5];
		for(int i = 0;i<holdArray.length;i++){
			try{
				//concatenates the player's hand in order to load up the right image
				cards[i] = ImageIO.read(getClass().getResource("/Cards/"+
			Card.suits[player.getHand().getCardAt(i).getSuit()]+
			Card.values[player.getHand().getCardAt(i).getValue()]+".png"));
			}catch(IOException e){
				e.printStackTrace();
			}
			handLabel[i].setIcon(new ImageIcon(cards[i].getScaledInstance(Cardpxx,Cardpxy,Image.SCALE_SMOOTH)));
			holdArray[i].setEnabled(true);
		}
	}
	
	/**
	 * Method that updates the player's credit.
	 */
	public void updateCredit(){
		credit_disp.setText(String.valueOf(player.getCredit()));
	}
	
	/**
	 * method that runs the class. Used to read the credit input of the user and call all
	 * other methods.
	 */
	public void runner(String[] args){
		Fframe.setVisible(true);
		if(state == 0){
			player.createHand();
		}
		while(player.getCredit() == 0){ //Waits for the user's valid input
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setButtons(player);
	}
}
