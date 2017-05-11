package group18;

import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;


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
	
	//Areas
	JTextArea stats;
	
	//Images
	private Image cback;
	
	String betString;
	
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
		
		Fframe = new JFrame("Video Poker");
		Fframe.setVisible(false);
		Fframe.setSize(350,200);
		Fframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Fframe.setResizable(false);
		
		JPanel panel_start = new JPanel();
		Fframe.add(panel_start);
		Fframe.setLayout(null);
		
		creditLabel = new JLabel("Credit:");
		creditLabel.setBounds(30, 20, 40, 25);
		Fframe.add(creditLabel);
		
		credit_text = new JTextField(20);
        credit_text.setBounds(100,20,165,25);
        Fframe.add(credit_text);
        
        playButton.setBounds(100, 60, 80, 25);
        Fframe.add(playButton);
		
        playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				textCredit = credit_text.getText().trim();
				try{
					credit = Integer.parseInt(textCredit);
					if(credit.equals(0) || credit < 0){
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
		credit_disp = new JTextField(String.valueOf(credit));
		credit_disp.setEditable(false);
		creditLabel = new JLabel("Credit: ");
		contentPane.add(creditLabel);
		layout.putConstraint(SpringLayout.WEST,creditLabel,730,SpringLayout.WEST,contentPane);
		layout.putConstraint(SpringLayout.NORTH,creditLabel,22,SpringLayout.NORTH,contentPane);
		
		contentPane.add(credit_disp);
		layout.putConstraint(SpringLayout.WEST,credit_disp,10,SpringLayout.EAST,creditLabel);
		layout.putConstraint(SpringLayout.NORTH,credit_disp,20,SpringLayout.NORTH,contentPane);
		
		result = new JTextField("This will print the player's result. Did he lose? Did he win?");
		result.setEditable(false);
		result.setVisible(false);
		result.setSize(100, 20);
		contentPane.add(result);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,result,420,SpringLayout.EAST,contentPane);
		layout.putConstraint(SpringLayout.NORTH,result,20,SpringLayout.NORTH,contentPane);
		
	}
	
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
		stats = new JTextArea("");
		stats.setEditable(false);
		stats.setPreferredSize(new Dimension(220,320));
		cardContainer.add(stats);
		layout.putConstraint(SpringLayout.WEST,stats,120,SpringLayout.WEST,cards[i-1]);
		layout.putConstraint(SpringLayout.NORTH,stats,100,SpringLayout.NORTH,cardContainer);
	}
	
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
	
	public void setButtons(Score score){
		mainButtons[0].addActionListener(new ActionListener(){ //bet button
			 public void actionPerformed(ActionEvent event){
				 	betString = "b "+bet_text.getText();
				 	userInput = betString.trim().split("\\s+");
					bet();
					updateCredit();
					result.setVisible(false);
					mainButtons[0].setEnabled(false);
					bet_text.setEnabled(false);
					mainButtons[2].setEnabled(true);
					adviceText.setText("");
				}
			});
		mainButtons[1].addActionListener(new ActionListener() {
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
				hold(score,hand);
				mainButtons[3].setEnabled(false);
				updateCredit();
				updateImageHand();
				result.setText(score.output);
				result.setVisible(true);
				mainButtons[1].setEnabled(false);
				mainButtons[0].setEnabled(true);
				mainButtons[2].setEnabled(true);
				adviceText.setText("");
				bet_text.setEnabled(true);
				score.printStats(Integer.parseInt(textCredit), credit);
				stats.setText(score.result);
			}
		});
		
		mainButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				createDeck();
				if(state == 0 && score.getPlays() != 0){
					mainButtons[0].setEnabled(false);
					bet_text.setEnabled(false);;
					bet(previousBet);
					updateCredit();
					result.setVisible(false);;
					adviceText.setText("");
					state = 1;
				}
				System.out.println("player's hand: " + hand);
				
				updateImageHand();
				mainButtons[1].setEnabled(true);
				mainButtons[2].setEnabled(false);
				mainButtons[3].setEnabled(true);
				state = 2;
			}
		});
		mainButtons[3].addActionListener(new ActionListener() { //stats button
			public void actionPerformed(ActionEvent event){
				adviceText.setText("player should "+sortString(Advice.getAdvice(hand)));
			}
		});
	}
	
	public void updateImageHand(){
		Image[] cards = new Image[5];
		for(int i = 0;i<holdArray.length;i++){
			try{
				cards[i] = ImageIO.read(getClass().getResource("/Cards/"+
			Card.suits[hand.cards[i].getSuit()]+
			Card.values[hand.cards[i].getValue()]+".png"));
			}catch(IOException e){
				e.printStackTrace();
			}
			handLabel[i].setIcon(new ImageIcon(cards[i].getScaledInstance(Cardpxx,Cardpxy,Image.SCALE_SMOOTH)));
			holdArray[i].setEnabled(true);
		}
	}
	
	public void updateCredit(){
		credit_disp.setText(String.valueOf(credit));
	}
	
	public void runner(String[] args, Score score){
		Fframe.setVisible(true);
		if(state == 0){
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck, handSize);
		}
		while(credit.equals(0)){ //Waits for the user's valid input
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setButtons(score);
	}
}
