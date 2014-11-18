/**
 * 
 */
package edu.csupomona.cs.cs141.classproject;

import java.util.Random;

/**
 * @author
 *
 */
public class GameEngine { 
	//Make sure to move most of this stuff back to the Grid class! GameEngine just needs methods
	//that deal with the User making changes from the UserInterface. The Grid can deal with spawns
	//and the movement of the ninjas.

	private GridMember[][] grid;
	
	public Grid gridm;

	private Ninja[] ninjas;

	private Taha tahaPlayer;

	private GridMember player;

	private int[] playerPosition = new int[2];
	
	private int playerDirection;
	
	public GameEngine(){
		gridm = new Grid(tahaPlayer);
	}

// Will be removed soon... (moved to grid) -Taha
//	public GameEngine(Taha tahaPlayer) {
//
//		ninjas = new Ninja[6];
//		for (int x = 0; x < ninjas.length; x++) {
//			ninjas[x] = new Ninja();
//		}
//		grid = new GridMember[9][9];
//		player = tahaPlayer;
//		thePlayer = tahaPlayer;
//		player = thePlayer;
//
//		for (int k = 0; k < grid.length; k++) {
//			for (int l = 0; l < grid[k].length; l++) {
//				grid[k][l] = new EmptyMember();
//			}
//		}
//
//		spawnRooms();
//
//		// spawns ninjas randomly, I am having trouble with having them spawn
//		// everywhere, not within
//		// 3 spaces of the player.
//		spawnNinjas();
//
//		//ninja = new Ninja();
//		//grid[5][5] = ninja;
//		//ninja.setPosition(5, 5);
//		grid[8][0] = player;
//		thePlayer.setPosition(8, 0);
//
//		playerPosition[0] = 8;
//		playerPosition[1] = 0;
//	}
//
//	public void spawnRooms() {
//		Random rand = new Random();
//		int randRoom = rand.nextInt(9);
//		int startRow = 1;
//		int startCol = 1;
//		int count = 0;
//		for (int y = 0; y < 3; y++) {
//
//			Room room1 = new Room();
//			grid[startRow][startCol] = room1;
//			++count;
//			if (randRoom == count) {
//				room1.giveBriefcase();
//			}
//
//			Room room2 = new Room();
//			grid[startRow][startCol + 3] = room2;
//			++count;
//			if (randRoom == count) {
//				room2.giveBriefcase();
//			}
//
//			Room room3 = new Room();
//			grid[startRow][startCol + 6] = room3;
//			++count;
//			if (randRoom == count) {
//				room3.giveBriefcase();
//			}
//
//			startRow += 3;
//		}
//	}
//
//	public void spawnNinjas() {
//		Random rand = new Random();
//		int[] ninjaCoordinates = new int[2];
//		for (Ninja currentNinja : ninjas) {
//			int ninRow = rand.nextInt(6) + 3;
//			int ninCol = rand.nextInt(6) + 3;
//			while (grid[ninRow][ninCol].isSomething()) {
//				ninRow = rand.nextInt(6) + 3;
//				ninCol = rand.nextInt(6) + 3;
//			}
//			ninjaCoordinates[0] = ninRow;
//			ninjaCoordinates[1] = ninCol;
//
//			grid[ninRow][ninCol] = currentNinja;
//			currentNinja.setPosition(ninRow, ninCol);
//		}
//
//		grid[8][0] = player;
//		thePlayer.setPosition(8, 0);
//
//		playerPosition[0] = 8;
//		playerPosition[1] = 0;
//	}



	public void printGrid() { // this will eventually be moved to UserInterface

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j].toString());
			}
			System.out.println();
		}
		System.out.println();

	}
// Will be removed soon... (moved to grid) -Taha
//	public Taha getPlayer() {
//		return thePlayer;
//	}

//	public void move(String direction, Grid grid){ //going to move this to the GameEngine
//	int[] playerPosition = grid.getPlayerPostion();
//	int row = playerPosition[0];
//	int col = playerPosition[1];
//	switch(direction){
//	
//	case "w":
//		playerDirection = 1;
//		movePlayer(row-1, col);
//		break;
//	case "d":
//		playerDirection = 2;
//		movePlayer(row, col+1);
//		break;
//	case "s":
//		playerDirection = 3;
//		movePlayer(row+1, col);
//		break;
//	case "a":
//		playerDirection = 4;
//		movePlayer(row, col-1);
//		break;
//	}
//	
//	
//}

	public void movePlayer(int row, int col) {
		int previousRow = playerPosition[0];
		int previousCol = playerPosition[1];
		int playerDirection = tahaPlayer.getPlayerDirection();

		// this try-catch block deals with moving the player object to a new
		// position on the Grid.
		// for now, it only deals with interaction with Room objects, but we
		// will have to write code
		// so the player object can deal with all the other types of
		// objects.(should be pretty easy,
		// using the .isSomething() method inherited from GridMember and maybe
		// writing another method
		// called interact() in the GridMember interface? We can just define it
		// differently in all the
		// classes that inherit GridMember.)
		try {
			if (grid[row][col] instanceof Room) {
				Room room = (Room)grid[row][col];
				if (playerDirection == 3) {
					printGrid();
					if (room.hasBriefcase()) {
						System.out.println("has briefcase!");
					}
					System.out.println("You can pick up the case!");
				} else {
					printGrid();
					System.out.println("You can only enter rooms from "
							+ "the North side!");
				}
			} else {
				playerPosition[0] = row;
				playerPosition[1] = col;
				grid[row][col] = player;
				tahaPlayer.setPosition(row, col);
				grid[previousRow][previousCol] = new EmptyMember();

			    moveNinjas(previousRow, previousCol);
			    playerSeeAround();
				printGrid();
				resetPlayerSeeAround();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			playerPosition[0] = previousRow;
			playerPosition[1] = previousCol;
			grid[previousRow][previousCol] = player;
			printGrid();
			System.out.println("You can't walk through walls!");
		}

	}
	
	
	public void resetPlayerSeeAround(){
		int row = playerPosition[0];
		int col = playerPosition[1];
		try{
			grid[row+1][col].resetSee();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row-1][col].resetSee();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row][col+1].resetSee();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row][col-1].resetSee();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
	}
	
	public void playerSeeAround(){
		int row = playerPosition[0];
		int col = playerPosition[1];
		try{
			grid[row+1][col].see();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row-1][col].see();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row][col+1].see();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		try{
			grid[row][col-1].see();
		} catch(ArrayIndexOutOfBoundsException e){
			//do nothing
		}
		
	}


	public void moveNinjas(int row, int col) {
		for (Ninja currNinja : ninjas) {
			int direction;
			boolean hasMoved = false;
			boolean tried = false;
			do {
				int[] ninCoord = currNinja.getPosition();
				currNinja.setDirection(row, col);
				direction = currNinja.getDirection();
				if (tried && currNinja.getPlayerInSight()) {
					Random rand = new Random();
					direction = rand.nextInt(4) + 1;
					System.out.println("Avoided room loop");
				}
				try {
					switch (direction) {

					case 1:

						if (grid[ninCoord[0] + 1][ninCoord[1]] instanceof Room) {
							hasMoved = false;
							tried = true;
							currNinja.setPosition(ninCoord[0], ninCoord[1]);

						} else {

							grid[ninCoord[0] + 1][ninCoord[1]] = currNinja;
							if (!(grid[ninCoord[0]][ninCoord[1]] instanceof PowerUp)) {
								grid[ninCoord[0]][ninCoord[1]] = new EmptyMember();
							}
							currNinja.setPosition((ninCoord[0] + 1), ninCoord[1]);
							hasMoved = true;
						}
						break;
					case 2:

						if (grid[ninCoord[0]][ninCoord[1] + 1] instanceof Room) {
							hasMoved = false;
							tried = true;
							currNinja.setPosition(ninCoord[0], ninCoord[1]);
						} else {
							grid[ninCoord[0]][ninCoord[1] + 1] = currNinja;
							if (!(grid[ninCoord[0]][ninCoord[1]] instanceof PowerUp)) {
								grid[ninCoord[0]][ninCoord[1]] = new EmptyMember();
							}
							currNinja.setPosition(ninCoord[0], (ninCoord[1] + 1));
							hasMoved = true;
						}
						break;
					case 3:

						if (grid[ninCoord[0] - 1][ninCoord[1]] instanceof Room) {
							hasMoved = false;
							tried = true;
							currNinja.setPosition(ninCoord[0], ninCoord[1]);
						} else {
							grid[ninCoord[0] - 1][ninCoord[1]] = currNinja;
							if (!(grid[ninCoord[0]][ninCoord[1]] instanceof PowerUp)) {
								grid[ninCoord[0]][ninCoord[1]] = new EmptyMember();
							}
							currNinja.setPosition((ninCoord[0] - 1), ninCoord[1]);
							hasMoved = true;
						}
						break;
					case 4:

						if (grid[ninCoord[0]][ninCoord[1] - 1] instanceof Room) {
							hasMoved = false;
							tried = true;
							currNinja.setPosition(ninCoord[0], ninCoord[1]);
						} else {
							grid[ninCoord[0]][ninCoord[1] - 1] = currNinja;
							if (!(grid[ninCoord[0]][ninCoord[1]] instanceof PowerUp)) {
								grid[ninCoord[0]][ninCoord[1]] = new EmptyMember();
							}
							currNinja.setPosition(ninCoord[0], (ninCoord[1] - 1));
							hasMoved = true;
						}
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					hasMoved = false;
					currNinja.setPosition(ninCoord[0], ninCoord[1]);
				}
			} while (!hasMoved);
		}
	}

// Will be removed soon... (moved to grid) -Taha
//	public GridMember getGridMember(int row, int col) {
//
//		GridMember gridMember = grid[row][col];
//		return gridMember;
//
//	}
//
//	public int[] getPlayerPostion() {
//		return playerPosition;
//	}

}
