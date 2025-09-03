import java.util.List; 
import java.util.Random;

public class EasyAI implements AI{
	
	//random number
	private Random rand = new Random();
	
	//method that gets the ai move
	public int getMove(Board board,char player,char opponent) {
		
		//puts the legal columns and puts them into a list
		List<Integer> gets = board.getLegalColumns();
		
		//if the list is empty
		if(gets.isEmpty()) {
			
			return -1;
			
		}
		
		//returns a random choice from the legal columns
		return gets.get(rand.nextInt(gets.size()));
		
			
	}

	
	

}

