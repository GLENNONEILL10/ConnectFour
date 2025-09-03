import java.util.*;

public class Game {
	
	private Menu menu;
	private Board board;
	private MediumAI mediumAi;;
	private EasyAI easyAI;
	private char currentPlayerSymbol;
	private char otherPlayer;
	private char AIPlayer;
	
	Scanner input = new Scanner(System.in);
	
	public Game() {
		
		menu = new Menu();
		
	}
	
	public void Play() {
		
		boolean gameMenu = false;
		
		//keeps user in initial menu
		while(!gameMenu) {
			
			boolean keepPlaying;
			
			
			int option = menu.gameTypeSelect();
			
			
			//if user selects 2 player
			if(option == 1) {
				
				do {
					
					//initialise new board
					board = new Board();
					
					boolean gameOver = false;
					
					//the first player chooses a symbol
					char player1Symbol = menu.symbolChoice();
					char player2Symbol;
					
				
					while (true) {
						
						//then second player chooses a different symbol
					    player2Symbol = menu.symbolChoice();
					    
					    //if symbols are different
					    if (player2Symbol != player1Symbol) {
					    	
					        break;
					        
					    } 
					    //if symbols are the same it continues the loop
					    else {
					        System.out.println("That symbol is already taken! Pick another one.");
					    }
					}
					
					char currentPlayer = player1Symbol;
					
					//while the game is not over
					while(!gameOver) {
						
						//prints the board
						board.printBoard();
						
						//prints a number from 0 - 6 underneath the board
						board.columnSelector();
						
						System.out.println();
						
						int columnChoice = input.nextInt();
						
						
						//if the user selects invalid choice
						if(columnChoice < 0 || columnChoice > 6){
							
							System.out.println("Out of Range. Enter 0 - 6 ");
							continue;
							
						}
						
						//if the move is not valid
						if(!board.isValidMove(columnChoice)){
							
							System.out.println("Invalid Move (column full).");
	                        continue;
							
							
						}
						
						//place the move
						int row = board.placeMove(columnChoice, currentPlayer);
						
						//if move is invalid
	                    if (row == -1) { // safety, if placeMove fails
	                        System.out.println("Column full. Try again.");
	                        continue;
	                    }
						
						//checks if the current player has won is true
						if(board.checkWin(currentPlayer)){
							
							//prints the board
							board.printBoard();
							
							System.out.println("Congratulations " + currentPlayer + " You have won");
							
							//exits loop
							gameOver = true;
							
							break;
							
						}
						
						//if the board is full
						if(board.isFull()){
							
							board.printBoard();
							
							System.out.println("Draw");
							
							gameOver = true;
							
							break;
							
						}
						
						//switches to next players turn
						
						if(currentPlayer == player1Symbol){
							
							currentPlayer = player2Symbol;
							
						}
						
						else if(currentPlayer == player2Symbol){
							
							currentPlayer = player1Symbol;
							
							
						}
					
					}
					
					//if user want to keep playing
					keepPlaying = menu.playAgain();
					
					
				}while(keepPlaying);
				
				
			}
			
			//if user selects play against computer
			else if(option == 2) {
							
				do {
					
					board = new Board();
					
					boolean gameOver = false;
					
					//calls easy or medium choice function
					int aiGameType = menu.AIChoice();
					
			
					//if gametype = 1 then assign ai to easyAI or else assign ai to mediumAI
					AI ai = (aiGameType == 1) ? new EasyAI() : new MediumAI();
						
					
					//the user selects a symbol
					char humanSymbol = menu.symbolChoice();
					
					
					//checks if humanSymbol is equal to a symbol and then assigns another symbol to aiSymbol
					char aiSymbol = (humanSymbol == 'X') ? 'O':
									(humanSymbol == 'O') ? 'X':
									(humanSymbol == 'R') ? 'B':
									(humanSymbol == 'B') ? 'R':
									(humanSymbol == '@') ? '?':
									(humanSymbol == '?') ? '@': 'O';
					
					
					//asks user if they want to go first
					boolean humanGoesFirst = menu.playFirst();
					
					
					//if humanGoesFirst is true then humanSymbol is assigned to current else aiSymbol is assigned to current
					char current = humanGoesFirst ? humanSymbol : aiSymbol;
					
					//while the game is not over
					while(!gameOver) {
						
						//print board and number for each column
						board.printBoard();
						board.columnSelector();
						
						System.out.println();
						
						int columnChoice;
						
						//if the human user is going
						if(current == humanSymbol){
							
							columnChoice = input.nextInt();
							
							//if choice is invalid
	                        if (columnChoice < 0 || columnChoice > 6) {
	                        	
	                            System.out.println("Out of Range. Enter 0 - 6");
	                            
	                            continue;
	                        }
	                        
	                        //if move is invalid
	                        if (!board.isValidMove(columnChoice)) {
	                        	
	                            System.out.println("Invalid Move (column full).");
	                            
	                            continue;
	                        }
	                        
	                        //move is placed
	                        int row = board.placeMove(columnChoice, humanSymbol);
	                        
	                        //if move is invalid
	                        if (row == -1) { 
	                        	
	                        	System.out.println("Column full. Try again.");
	                        	
	                        	continue; 
	                        	
	                        }
							
							
						}
						
						//if the current player is the ai
						else {
							
							//gets the ai move
							int aiColumn = ai.getMove(board, aiSymbol, humanSymbol);
							
							//if the ai cant find an empty space 
							if (aiColumn == -1) {
								
		                            board.printBoard();
		                            
		                            System.out.println("Draw");
		                            
		                            break;
							}
							
							//ai places move
	                        int row = board.placeMove(aiColumn, aiSymbol);
	                        
	                        //if move is invalid
	                        if (row == -1) {
	                            
	                            System.out.println("AI chose a full column (unexpected). Skipping.");
	                            continue;
	                        }
						
						}
						
						//current player is stored 
						char justMoved = current;
						
						
						//checks if win is true
						if(board.checkWin(justMoved)){
							
							//prints board
							board.printBoard();
							
							//if the current player is the human
	                        if (justMoved == humanSymbol) {
	                        	
	                            System.out.println("You win!");
	                            
	                        } 
	                        
	                        //if the current player is the ai
	                        else {
	                        	
	                            System.out.println("Computer (" + justMoved + ") wins!");
	                        }
	                        
	                        //exits loop
	                        gameOver = true;
	                        break;
							
						}
						
						//if the board is full
						if(board.isFull()) {
							
							board.printBoard();
							
	                        System.out.println("Draw");
	                        
	                        gameOver = true;
	                        break;
							
							
						}
						
						//if the current is equal to human switch to ai and vice versa, it switches who is playing
						current = (current == humanSymbol) ? aiSymbol : humanSymbol;
						
					}
					
					//option to keep playing
					keepPlaying = menu.playAgain();
					
				}while(keepPlaying);
				
				
			}
			
			//asks user if they want to quit entirely
			
			
			while(true) {
				
				int choice;
				System.out.println("[1]Do You want to quit completely\n[2]go back to main menu");
					
			  if (!input.hasNextInt()) {
				  
			        input.next(); // consume the non-int token
			        
			        System.out.println("Please enter a number (1 or 2).");
			        
			        continue; // re-prompt
			    }
			  
			  choice = input.nextInt();
				
				//if they want to quit
				if(choice == 1){
					
					//exits
					System.exit(1);
					
					//exits the loop
					gameMenu = true;
					
					
				}
				else if(choice == 2) {
					
					//continues the loop
					gameMenu = false;
					
					break;
					
				}
				
				//if the user enters invalid
				else {
					
					System.out.println("Please enter 1 or 2");
				}
			}
			
			
			
		}
		
		
	}
	
}

