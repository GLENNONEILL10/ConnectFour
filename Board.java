import java.util.*;

public class Board {
	
	private char[][] board;
	
	//Row and Column constant values
	private final int ROW = 6;
	private final int COL = 7;
	
	
	//constructor that initialises a new board every time its called
	public Board() {
		
		board = new char[ROW][COL];
		
		for(int i = 0; i < ROW; i++) {
			
			for(int j = 0; j < COL;j++){
				
				board[i][j] = '*';
				
			}
		}
				
	}
	
	//prints the board to console
	public void printBoard() {
		
		//nested loop that prints the board correctly
		for(int i = 0;i < ROW;i++) {
			
			for(int j = 0;j < COL;j++) {
				
				System.out.print(board[i][j]);
				
				if(j < COL ){
					
					System.out.print("|");
					
				}
			}
			
			System.out.println();
			
			if(i < ROW ){
				
				System.out.println("--------------");
				
			}
		
		}
	}
	
	//method that places a move in an index that = *
	public int placeMove(int col,char symbol) {
		
		//if rows and cols are greater than 0 and less than 6 for row and 7 for col
		if(col < 0 || col >= COL){
			
			return -1;
			
		}
			
			//loops while 6-1 is greater than 0
			for(int r = ROW-1; r >=0; r--){
				
				//if board index = *
				if(board[r][col] == '*'){
					
					//replaces with symbol chosen
					board[r][col] = symbol;
					
					
					
					//move is placed 
					return r;
				}
				
		}
		//handles error if row or col is less than 0 or greater than 6 or 7 
		
		return -1;
			
	}
	
	//checks if the board is full
	public boolean isFull() {
		
		//loops through board 
		for(int i = 0;i< ROW;i++){
			
			for(int j = 0; j < COL;j++){
				
				//if board = * it means this index has not been chosen and therefore board is not full
				if(board[i][j] == '*'){
					
					return false;
					
				}

			}
			
		}
		// after loop[ping through the board and not finding * it means the board us full
		return true;
	}
	
	//checks if player has won
	public boolean checkWin(char symbol){
		
		int win = 4;
		
		//loops 6 times
		for(int i = 0;i<ROW;i++){
			
			//if row is equal to symbol on 4 times in a row
			if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol && board[i][3] == symbol
					
			|| board[i][1] == symbol && board[i][2] == symbol && board[i][3] == symbol && board[i][4] == symbol
			
			|| board[i][2] == symbol && board[i][3] == symbol && board[i][4] == symbol && board[i][5] == symbol
			
			|| board[i][3] == symbol && board[i][4] == symbol && board[i][5] == symbol && board[i][6] == symbol){
				
				//then its a win
				return true;
				
			}
			
		}
		
		//loops 7 times
		for(int j = 0; j < COL;j++){
			
			//if col equals symbol 4 times in a row
			if(board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol && board[3][j] == symbol
					
			|| board[1][j] == symbol && board[2][j] == symbol && board[3][j] == symbol && board[4][j] == symbol
			
			|| board[2][j] == symbol && board[3][j] == symbol && board[4][j] == symbol && board[5][j] == symbol){
				
				return true;
				
			}
	
		}
		
		// checking diagonals
		
		//diagonals down right
		//loops while i is less than or equal to 6 - 4
		for(int i = 0; i <= ROW - win ;i++) {
			
			//loops while j is less than or equal to 7 - 4
			for(int j = 0;j <= COL - win;j++) {
				
				int count = 0;
				
				//loops while k is less than 5
				for(int k = 0; k < win;k++){
					
					//if index [0 + 0][0 + 0] == symbol
					//if index [0 + 1][0 + 1] == symbol
					//if index [0 + 2][0 + 2] == symbol  and so on
					if(board[i+k][j+k] == symbol) {
						
						//increment the count
						count++;
						
						//if count is incremented to be equal to 4 (win)
						if(count == win){
							
							//then a win is true
							return true;
						}
						
					}
					//if symbol is not equal to index
					else {
						break;
					}
					
				}
				
				
			}
			
			
		}
		
		
		//diagonals going down left
		
		for(int i = 0; i <= ROW - win;i++){
			
			//loops while j = 4 - 1 is less than 7 -1
			for(int j = win - 1; j < COL; j++) {
				
				int count = 0;
				
				for(int k = 0; k < win;k++) {
					
					//if index [0 + 0][3 - 0] == symbol
					//if index [0 + 1][3 - 1] == symbol
					//if index [0 + 2][3 - 2] == symbol
					if(board[i+k][j-k] == symbol){
						
						count++;
						
						if(count == win){
							
							return true;
							
						}
						
					}
					else {
						
						break;
					}
						
				}
			
			}
		
		}
		
		//no win found in above conditions so return false
		return false;
	
	}
	
	//method that checks if a move can be made
	public boolean isValidMove(int col){
		
		//if row is less than 0 and greater than 6 and if col is less than 0 and greater than 7
		if( col >= 0 && col < COL){
			
			//if board index entered = * it means a move can be made
			if(board[0][col] == '*'){
				
				//move can be made
				return true;
				
			}
			
		}
		
		//if board index is not = to * then there is a player symbol in that position and move cant be made
		return false;
		
	}
	
	//method that gets the legal moves for ai class mainly
	public List<Integer> getLegalColumns() {
		
		//list that holds the moves available
		List<Integer> moveList = new ArrayList<>();
		
		//loops through the columns to find the * which mean its empty
		for(int c = 0;c<COL;c++){
			
			if(board[0][c] == '*'){
				
				//index is added to list
				moveList.add(c);
				
			}
			
		}
		//returns the list
		return moveList;
		
	}
	
	//class that undos last move for ai part
	public boolean undoMove(int col) {
		
		if(col < 0 || col >= COL) {
			
			return false;
		}
		
		//checks from top to bottom as top would be the last move
		for(int r = 0;r < ROW;r++) {
			
			//if index is not equal to *
			if(board[r][col] != '*'){
				
				//replace what ever is there with *
				board[r][col] = '*';
				
				return true;
			}
				
		}
		
		return false;
		
	}
	
	//function that prints number for each column underneath the board
	public void columnSelector() {
		
		System.out.println("0 1 2 3 4 5 6");
		
	}
	
	
}