/**
 * 
 */
package edu.csupomona.cs.cs141.classproject;

import java.util.Random;

/**
 * @author Taha
 *
 */
public class Grid{
private GridMember[][] grid;

private Ninja[] ninjas;

private Taha thePlayer;

private GridMember player;

private int[] playerPosition = new int[2];

public Grid( Taha tahaPlayer) {

	ninjas = new Ninja[6];
	for (int x = 0; x < ninjas.length; x++) {
		ninjas[x] = new Ninja();
	}
	grid = new GridMember[9][9];
	player = tahaPlayer;
	thePlayer = tahaPlayer;
	player = thePlayer;

	for (int k = 0; k < grid.length; k++) {
		for (int l = 0; l < grid[k].length; l++) {
			grid[k][l] = new EmptyMember();
		}
	}
	spawnRooms();

	// spawns ninjas randomly, I am having trouble with having them spawn
	// everywhere, not within
	// 3 spaces of the player.
	spawnNinjas();

	//ninja = new Ninja();
	//grid[5][5] = ninja;
	//ninja.setPosition(5, 5);
	grid[8][0] = player;
	thePlayer.setPosition(8, 0);

	playerPosition[0] = 8;
	playerPosition[1] = 0;
}

public void spawnRooms() {
	Random rand = new Random();
	int randRoom = rand.nextInt(9);
	int startRow = 1;
	int startCol = 1;
	int count = 0;
	for (int y = 0; y < 3; y++) {

		Room room1 = new Room();
		grid[startRow][startCol] = room1;
		++count;
		if (randRoom == count) {
			room1.giveBriefcase();
		}

		Room room2 = new Room();
		grid[startRow][startCol + 3] = room2;
		++count;
		if (randRoom == count) {
			room2.giveBriefcase();
		}

		Room room3 = new Room();
		grid[startRow][startCol + 6] = room3;
		++count;
		if (randRoom == count) {
			room3.giveBriefcase();
		}

		startRow += 3;
	}
}
public void spawnNinjas() {
	Random rand = new Random();
	int[] ninjaCoordinates = new int[2];
	for (Ninja currentNinja : ninjas) {
		int ninRow = rand.nextInt(6) + 3;
		int ninCol = rand.nextInt(6) + 3;
		while (grid[ninRow][ninCol].isSomething()) {
			ninRow = rand.nextInt(6) + 3;
			ninCol = rand.nextInt(6) + 3;
		}
		ninjaCoordinates[0] = ninRow;
		ninjaCoordinates[1] = ninCol;

		grid[ninRow][ninCol] = currentNinja;
		currentNinja.setPosition(ninRow, ninCol);
	}

	grid[8][0] = player;
	thePlayer.setPosition(8, 0);

	playerPosition[0] = 8;
	playerPosition[1] = 0;
}

public Taha getPlayer() {
	return thePlayer;
}

public GridMember getGridMember(int row, int col) {

	GridMember gridMember = grid[row][col];
	return gridMember;

}

public int[] getPlayerPostion() {
	return playerPosition;
}




}
