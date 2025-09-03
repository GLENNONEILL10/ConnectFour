import java.util.List;
import java.util.Random;

public class MediumAI implements AI {
	
	//random number
	private Random rand = new Random();
	
	//method that gets the ai move
	public int getMove(Board board, char player , char opponent ) {
		
		//puts all legal columns into a list
		List<Integer> gets = board.getLegalColumns();
		
		//if list is empty
		if(gets.isEmpty()){
			
			return -1;
			
		}
		
		
		//for each column in the list continue the loop
		for(int column : gets) {
			
			//row equals the players move
			int row = board.placeMove(column, player);
			
			//if row is a valid input
			if(row != -1) {
				
				
				boolean win = board.checkWin(player);
				
				
				//undos ai pretend move resetting the board state
				board.undoMove(column);
				
				//if the move is a win
				if(win){
					
					//returns the column from the list
					return column;
					
				}
				
				
			}
			
		}
		
		
		//for each column in the list continue the loop
		for(int column : gets) {
			
			//row equals the opponents move
			int row = board.placeMove(column, opponent);
			
			if(row != -1){
				
				boolean oppWin = board.checkWin(opponent);
				
				board.undoMove(column);
				
				if(oppWin) {
					
					return column;
					
				}
				
			}
			
		}
		
		//if the list contains valid moves for column 3
		if(gets.contains(3)) {
			
			//ai places its move into the column 3 the centre
			return 3;
		}
		
		//array in order of closes to centre
		int[] nearCentreOrder = {2,4,1,5,0,6};
		
		//for each c item in the array
		for(int c : nearCentreOrder) {
			
			//if the list contains the column
			if(gets.contains(c)) {
				
				//return the column chosen
				return c;
				
			}
			
		}
		
		//return this just in case all else fails
		return gets.get(rand.nextInt(gets.size()));
		
		
	}

}

