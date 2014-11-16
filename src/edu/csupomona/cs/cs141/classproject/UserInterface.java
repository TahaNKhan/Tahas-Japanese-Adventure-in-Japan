/**
 * 
 */
package edu.csupomona.cs.cs141.classproject;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Isa
 *
 */
public class UserInterface {

	private GridMember[][] gridCells;
	
	Scanner kb = new Scanner(System.in); //Just make a global Scanner here so it's scope spans the whole class.
	
	public int firstMenu(){
		
		int userChoice = 0;
		Taha tahaPlayer = new Taha();
		GameEngine gameEng = new GameEngine(tahaPlayer);
		
		System.out.println("Welcome to Taha's Adventure!\n");
		System.out.println("1. Start Game");
		System.out.println("2. Load Game");
		System.out.println("3. About");
		System.out.println("4. Help");
		System.out.println("5. Quit");
		
		while(userChoice < 1 || userChoice > 5){
		try{
				userChoice = kb.nextInt();
				kb.nextLine();
				
		} catch(InputMismatchException e){
			System.out.println("Bad Input, Try again");
			userChoice = kb.nextInt();
			kb.nextLine();
		}
		}
		return userChoice;
	}
	

	public void firstMenuRedirection(int userChoice) {
		// TODO Auto-generated method stub
		Switch (userChoice){
			
		case 1: theGameInterface();
			break;	
		case 2: //add call to loadGame method here
			
		case 3: //add call to aboutGame method here
			
		case 4: //add call to help method here
			
		case 5: System.exit(0);
		}
	}

	public void theGameInterface(){
		
		Taha tahaPlayer = new Taha();
		
		GameEngine gameEng = new GameEngine(tahaPlayer);
		
		gameEng.printGrid();
		String playerChoice = "Q";
		while(playerChoice != "A" || playerChoice != "W" || playerChoice != "S" || playerChoice != "D" || playerChoice != "0"){
		
			System.out.println("W. Up, D. Right, S. Down, A. Left, or 0 to quit.");
			try{
				playerChoice = kb.next();
				kb.nextLine();
				tahaPlayer.move(playerChoice.toLowerCase(), gameEng);
			} catch(InputMismatchException e){
				System.out.println("Please enter correct input.");
				kb.next();
			}
		}
	}
	

//  Isaac, this method is also in the engine, do we need it here?	
// 
//	public void printGrid(){
//		for(int i = 0; i < gridCells.length; i++){
//			for(int j = 0; j < gridCells[i].length; j++){
//				System.out.print(gridCells[i][j].toString());
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
}
