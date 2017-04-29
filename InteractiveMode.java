package video_poker;

import java.util.Arrays;

public class InteractiveMode extends GameMode {

	public InteractiveMode(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	
	public void interactive(){
		System.out.println("You chose the interactive mode with "+credit+ "credit");
		//The program is a cycle that only ends when the player quits. ??Maybe also end when the player has no credits left.
				while(state != hasQuit){
					
					if(state == 0){
						//For each new play, we need to open a new deck
						//Then we need to shuffle the deck
						deck = new Deck();
						deck.shuffle();
						
						//now we create the initial player's hand and the 5 replacement cards,
						//that will be used to replace the cards chosen by the player.
						hand = new Hand(deck, handSize);
						hand.sort();
					}			
					
					
					System.out.println("What will you do?");
					
					//Showing the player messages to guide him through the game
					System.out.println("[$] credit");
					
					if(state == 0){
						System.out.println("[b] bet");
						System.out.println("[s] statistics");
						System.out.println("[q] quit");
					}
					
					if(state == 1){
						System.out.println("[d] deal");
					}

					if(state == 2){
						System.out.println("[h] hold");
						System.out.println("[a] advice");
					}
					
					//First we read the whole line written by the user
					userInput = reader.nextLine().toLowerCase().split(" ");
					
					//Then we take the first word
					if(userInput[0].length() == 1){
						input = userInput[0].charAt(0);
					}else{
						System.out.println(userInput[0] + ": illegal command, please choose from the list.");
						continue;
					}
					
					//After getting input, we process it
					switch(input){
					case 'b':
						if(state == 0){
							//Change the value of the bet. if there is no value, the bet keeps its previous value.
							if(userInput.length > 2){
								System.out.println("Please, choose only one value to bet: [b i]\n Try Again");
								break;
							}else if(userInput.length == 2){
								bet = Integer.parseInt(userInput[1]);
								if(bet < 1 || bet > 5){
									System.out.println("Invalid bet, please choose a proper value [1 - 5].");
									bet = previousBet;
									break;
								}else{
									if(bet <= credit){
										previousBet = bet;
										System.out.println(previousBet);
									}else{
										System.out.println("Not enough credits. Betting what you have");
										bet = credit;
									}
								}
							}
							credit -= bet;
							state = 1;
							System.out.println("You bet " + bet);
						}else{
							System.out.println("You can't bet right now.");
						}
						break;
						
					case '$':
						System.out.println("Your credit is " + credit);
						break;
						
					case 'd':
						if(state == 1){
							System.out.println(hand);
							state = 2;
						}else{
							System.out.println("d : illegal command");
						}
						break;
						
					case 'h':
						if(state == 2){
							//Creating the toDiscard array that will be used for the "hold" function
							toDiscard = new int[hand.length()];
							Arrays.fill(toDiscard, 1);
							
							for(String aux: userInput){
								if(!aux.equals("h")){
									toHold = Integer.parseInt(aux);
									if(toHold >= 1 && toHold <= 5){
										toDiscard[toHold - 1] = 0;
									}else{
										System.out.println("Invalid value, please choose cards from those in hand [1 - 5].");
										state = 3;
										break;
									}
								}
							}
							if(state == 3){
								state = 2;
								break;
							}
						}else{
							System.out.println("h: ilegal command");
							break;
						}
						
						for(int i = 0; i < toDiscard.length; i++){
							if(toDiscard[i] == 1){
								hand.replace(i);
							}
						}
						hand.rigHand(new int[]{0, 9, 10, 11, 12}, new int[]{1, 1, 1, 1, 1});
						hand.sort();
						System.out.println("player's hand " + hand);
						switch( hand.isCombination(hand) ){
						case 11:
							type_hand = "Royal Flush";
							if(bet==1)
								credit+=250;
							if(bet==2)
								credit=+500;
							if(bet==3)
								credit+=750;
							if(bet==4)
								credit+=1000;
							if(bet==5)
								credit+=4000;
							break;
						case 10:
							type_hand = "Straight Flush";
							if(bet==1)
								credit=+50;
							if(bet==2)
								credit=+100;
							if(bet==3)
								credit=+150;
							if(bet==4)
								credit=+200;
							if(bet==5)
								credit=+250;
							break;
						case 9:
							type_hand = "Four Aces";
							if(bet==1)
								credit=+160;
							if(bet==2)
								credit=+320;
							break;
						case 8:
							type_hand = "Four 2-4";
							break;
						case 7:
							type_hand = "Four 5-K";
							break;
						case 6:
							type_hand = "Full House";
							break;
						case 5:
							type_hand = "Flush";
							break;
						case 4:
							type_hand = "Straight";
							break;
						case 3:
							type_hand = "Three of a Kind";
							break;
						case 2:
							type_hand = "Two Pair";
							break;
						case 1:
							type_hand = "Jacks or Better";
							break;
						default:
							System.out.println("You lost and your credit is " + credit + ".");
						}
						if(hand.isCombination(hand) != 0){
							//should print the type of hand he won with, not the cards itself
							System.out.println("You won with a " + type_hand + " and now your credit is " + credit + "!");
						}
						state = 0;
						break;
						
					case 'a':
						
						break;
						
					case 's':
						
						break;
						
					case 'q':
						if(state == 0){
							state = hasQuit;
							System.out.println("Thank you for playing!");
						}else{
							System.out.println("You can't quit now. You're locked here with me. :) ");
						}
						break;
						
					default:
						System.out.println(input +": illegal command. Available options:");
					}
				}
	}
}
