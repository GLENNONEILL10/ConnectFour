import java.util.*;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	
	//give user the option to choose different symbols
	public char symbolChoice() {
		
		while(true){
			
			System.out.println("Select A Symbol\n[X]\n[O]\n[R]\n[B]\n[@]\n[?]");
			String choice = input.nextLine().toUpperCase();
			
			//if user selects a symbol
			if(choice.equals("X") || choice.equals("O") || choice.equals("R") || choice.equals("B")
				|| choice.equals("@") || choice.equals("?")){
				
				//return the symbol
				return choice.charAt(0);
				
			}
			
			//or else error
			else {
				
				System.out.println("Error: Please enter X or O or R or B or @ or ?");
			}
			
		}
		
	}
	
	//gives user the option to select what game they want to play
	public int gameTypeSelect() {
			
			while(true) {
				
				System.out.println("Select:\n[1]2 player\n[2]Against Computer");
				
				
				if(input.hasNextInt()) {
					
					int option = input.nextInt();
					
					input.nextLine();
					
					//if user selects an option
					if(option == 1 || option == 2){
						
						return option;
						
					}
					
					//or else error
					else {
						
						System.out.println("Invalid Input:Please Enter 1 for 2 player OR 2 for against Computer");
					}
							
				}
				else {
					
					System.out.println("Invalid Input: Please Enter number 1 or number 2 ");
				}
				
			}
			
		}

	//give user choice if they want to go first or not
	public boolean playFirst() {
		
		while(true) {
			
			System.out.println("Do You want to play First:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			//if yes
			if(choice.equals("Y")){
				
				return true;
				
			}
			//or else no
			else if(choice.equals("N")) {
				
				return false;
			}
			
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
				
		}
	}
	
	
	//gives user option to play again
	public boolean playAgain() {
		
		while(true) {
			
			System.out.println("Do You want to play Again:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			//if yes
			if(choice.equals("Y")){
				
				return true;
				
			}
			//or else no
			else if(choice.equals("N")) {
				
				return false;
			}
			
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
			
		}
	}
	
	//give user the option to choose different ai modes
	public int AIChoice() {
		
		while(true) {
			
			System.out.println("[1]Easy Mode  [2]Medium Mode");
			
			if(input.hasNextInt()) {
				
				int choice = input.nextInt();
				
				input.nextLine();
		
				if(choice == 1 || choice == 2) {
					
					return choice;
				}
				else {
					
					System.out.println("Invalid Input:Please Enter 1 for Easy Mode OR 2 for Medium Mode");
				}
						
			}
			else {
				
				System.out.println("Invalid Input: Please Enter number 1 or number 2 ");
			}
		}
	}
		
	
}



