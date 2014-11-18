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

	
	private GridMember[][] gridCells; // needs to be removed, breaks encapsulation.
	
	Scanner kb = new Scanner(System.in);
	
	Taha tahaPlayer = new Taha();
		
	GameEngine GMENG;
	
	Grid GRID;
	
	public UserInterface(){

//		gridCells = grid.printGrid();

	}
	
	public int FirstMenu(){
		int userChoice = 0;
		Taha tahaPlayer = new Taha();
		GMENG = new GameEngine();
		
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to Taha's Adventure!\n");
		System.out.println("1. Start Game");
		System.out.println("2. Load Game");
		System.out.println("3. About");
		System.out.println("4. Help");
		System.out.println("5. Quit");
		
		try{
			while(userChoice < 1 || userChoice > 5){
				userChoice = kb.nextInt();
				kb.nextLine();
				
				
				
			}
		} catch(InputMismatchException e){
			System.out.println("Bad Input, Try again");
		}
		return userChoice;
	}
	

	public void FirstMenuRedirection(int userChoice) {
		// TODO Auto-generated method stub
		switch (userChoice){
		case 1: theGameInterface();
			break;
		case 2: //call load game method here
			break;
		case 3: //call about method here
			break;
		case 4: //call help method here
			break;
		case 5: System.exit(0);
		}
	}

	public void theGameInterface(){
		
		GMENG.printGrid();
		String playerChoice = "Q";
		while(playerChoice != "A" || playerChoice != "W" || playerChoice != "S" || playerChoice != "D" || playerChoice != "0"){
		
			System.out.println("W. Up, D. Right, S. Down, A. Left, or 0 to quit.");
			try{
				playerChoice = kb.next();
				kb.nextLine();
				tahaPlayer.move(playerChoice.toLowerCase(), GRID,GMENG);
			} catch(InputMismatchException e){
				System.out.println("Please enter correct input.");
				kb.next();
			}
		}
	}
	

//  No, this method should not be here since according to Rodriguez, passing in gridCells breaks encapsulation. Somehow,
//  we have to make the method already existing in the grid class pass the entire grid(in string form) to GameEngine, and
// then from GameEngine to UserInterface. Shouldn't be too hard. But yea, this should't be here anymore. And neither should
// the GridCells field.

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
