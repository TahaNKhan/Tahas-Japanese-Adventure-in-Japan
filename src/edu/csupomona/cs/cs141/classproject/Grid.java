import java.util.Random;

/**
 * 
 */

/**
 * @author Isa
 *
 */
public class Grid {
	
	private GridMember[][] grid;

	private Ninja[] ninjas = new Ninja[6];

	private Taha thePlayer;

	private GridMember player;

	private int[] playerPosition = new int[2];

	private int[] briefcase = new int[2];

	private PowerUp radar = new Radar();

	private PowerUp extraBullet = new ExtraBullet();

	private PowerUp cantDie = new Invincibility();

	private int[] radarPosition = new int[2];

	private int[] bulletPosition = new int[2];

	private int[] inviPosition = new int[2];
	
	private boolean win = false;

	public Grid(Taha tahaPlayer) {

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
		spawnRadar();
		spawnExtraBullet();
		spawnInvincibility();

		// ninja = new Ninja();
		// grid[5][5] = ninja;
		// ninja.setPosition(5, 5);
		grid[8][0] = player;
		thePlayer.setPosition(8, 0);

		playerPosition[0] = 8;
		playerPosition[1] = 0;
	}
	
	public void resetSeeAll(){
		for (int k = 0; k < grid.length; k++) {
			for (int l = 0; l < grid[k].length; l++) {
				grid[k][l].resetSee();
			}
		}
	}

	public void spawnRadar() {
		Random rand = new Random();
		int[] itemCoordinates = new int[2];
		int iRow = rand.nextInt(6) + 3;
		int iCol = rand.nextInt(6) + 3;
		while (grid[iRow][iCol].isSomething()) {
			iRow = rand.nextInt(6) + 3;
			iCol = rand.nextInt(6) + 3;
		}
		itemCoordinates[0] = iRow;
		itemCoordinates[1] = iCol;

		radarPosition[0] = iRow;
		radarPosition[1] = iCol;

		grid[iRow][iCol] = radar;
	}

	public void spawnExtraBullet() {
		Random rand = new Random();
		int[] itemCoordinates = new int[2];
		int iRow = rand.nextInt(6) + 3;
		int iCol = rand.nextInt(6) + 3;
		while (grid[iRow][iCol].isSomething()) {
			iRow = rand.nextInt(6) + 3;
			iCol = rand.nextInt(6) + 3;
		}
		itemCoordinates[0] = iRow;
		itemCoordinates[1] = iCol;

		bulletPosition[0] = iRow;
		bulletPosition[1] = iCol;

		grid[iRow][iCol] = extraBullet;
	}

	public void spawnInvincibility() {
		Random rand = new Random();
		int[] itemCoordinates = new int[2];
		int iRow = rand.nextInt(6) + 3;
		int iCol = rand.nextInt(6) + 3;
		while (grid[iRow][iCol].isSomething()) {
			iRow = rand.nextInt(6) + 3;
			iCol = rand.nextInt(6) + 3;
		}
		itemCoordinates[0] = iRow;
		itemCoordinates[1] = iCol;

		inviPosition[0] = iRow;
		inviPosition[1] = iCol;

		grid[iRow][iCol] = cantDie;
	}

	public void runAllChecks() {
		bulletGainedCheck();
		radarGainedCheck();
		inviGainedCheck();
	}

	public void bulletGainedCheck() {
		if (extraBullet.isUsed() == false) {
			bulletOverridenCheck();
		}
	}

	public void bulletOverridenCheck() {
		int row, col;

		row = bulletPosition[0];
		col = bulletPosition[1];

		if (grid[row][col] instanceof EmptyMember) {
			grid[row][col] = extraBullet;
		}
	}

	public void radarGainedCheck() {
		if (radar.isUsed() == false) {
			radarOverridenCheck();
		}
	}

	public void radarOverridenCheck() {
		int row, col;

		row = radarPosition[0];
		col = radarPosition[1];

		if (grid[row][col] instanceof EmptyMember) {
			grid[row][col] = radar;
		}
	}

	public void inviGainedCheck() {
		if (cantDie.isUsed() == false) {
			inviOverridenCheck();
		}
	}

	public void inviOverridenCheck() {
		int row, col;

		row = inviPosition[0];
		col = inviPosition[1];

		if (grid[row][col] instanceof EmptyMember) {
			grid[row][col] = cantDie;
		}
	}

	public void getPowerUp(int row, int col) {

		if (grid[row][col] instanceof Radar) {
			radarGained();
			radar.powerGain();
			grid[row][col] = new EmptyMember();
		}

		else if (grid[row][col] instanceof ExtraBullet) {
			thePlayer.addAmmo();
			extraBullet.powerGain();
			grid[row][col] = new EmptyMember();
		}

		else if (grid[row][col] instanceof Invincibility) {
			thePlayer.isWalkingOnSunshine();
			cantDie.powerGain();
			grid[row][col] = new EmptyMember();
		}

		grid[row][col] = new EmptyMember();
	}

	public void radarGained() {

		grid[briefcase[0]][briefcase[1]] = new TrueRoom();

	}


	public void playerRespawn() {
		int row = 8;
		int col = 0;
		int previousRow = playerPosition[0];
		int previousCol = playerPosition[1];
		playerPosition[0] = row;
		playerPosition[1] = col;
		grid[row][col] = player;
		thePlayer.setPosition(row, col);
		grid[previousRow][previousCol] = new EmptyMember();
		grid[8][0] = player;
		thePlayer.setPosition(8, 0);
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
				briefcase[0] = startRow;
				briefcase[1] = startCol;
			}

			Room room2 = new Room();
			grid[startRow][startCol + 3] = room2;
			++count;
			if (randRoom == count) {
				room2.giveBriefcase();
				briefcase[0] = startRow;
				briefcase[1] = startCol + 3;
			}

			Room room3 = new Room();
			grid[startRow][startCol + 6] = room3;
			++count;
			if (randRoom == count) {
				room3.giveBriefcase();
				briefcase[0] = startRow;
				briefcase[1] = startCol + 6;
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

	public void printGrid() { // this will eventually be moved to UserInterface

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j].toString());
			}
			System.out.println();
		}
		System.out.println();

	}

	public Taha getPlayer() {
		return thePlayer;
	}

	public void movePlayer(int row, int col) {
		int previousRow = playerPosition[0];
		int previousCol = playerPosition[1];
		int playerDirection = thePlayer.getPlayerDirection();

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
				Room room = (Room) grid[row][col];
				if (playerDirection == 3) {
					printGrid();
					if (room.hasBriefcase()) {
						System.out.println("has briefcase!");
						youWon();
					}
					System.out.println("You can pick up the case!");
				} else {
					printGrid();
					System.out.println("You can only enter rooms from "
							+ "the North side!");
				}
			}

			else if (grid[row][col] instanceof PowerUp) {
				getPowerUp(row, col);
				playerPosition[0] = row;
				playerPosition[1] = col;
				grid[row][col] = player;
				thePlayer.setPosition(row, col);
				grid[previousRow][previousCol] = new EmptyMember();

				moveNinjas(previousRow, previousCol);
				playerSeeAround();
				resetPlayerSeeAround();
			} else {
				playerPosition[0] = row;
				playerPosition[1] = col;
				grid[row][col] = player;
				thePlayer.setPosition(row, col);
				grid[previousRow][previousCol] = new EmptyMember();

				moveNinjas(previousRow, previousCol);
				playerSeeAround();
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
	
	public void youWon(){
		win = true;
	}
	
	public boolean showWin(){
		return win;
	}

	public void resetPlayerSeeAround() {
		int row = playerPosition[0];
		int col = playerPosition[1];
		try {
			grid[row + 1][col].resetSee();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row - 1][col].resetSee();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row][col + 1].resetSee();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row][col - 1].resetSee();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}

	public void playerSeeAround() {
		int row = playerPosition[0];
		int col = playerPosition[1];
		try {
			grid[row + 1][col].see();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row - 1][col].see();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row][col + 1].see();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
		try {
			grid[row][col - 1].see();
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}

	}
	
	public void look(int direction) {
		int playerRow = -1;
		int playerCol = -1;
		int[] position = thePlayer.getPosition();
		playerRow = position[0];
		playerCol = position[1];
		boolean ninjaAhead = false;
		try {
			switch (direction) {
			case 1:
				for (Ninja ninja : ninjas) {
					int[] ninPosition = ninja.getPosition();
					if (ninPosition[1] == playerCol) {
						if (ninPosition[0] < playerRow) {
							ninjaAhead = true;
						}
					}
				}
				grid[playerRow - 1][playerCol].see();
				grid[playerRow - 2][playerCol].see();
				break;
			case 2:
				for (Ninja ninja : ninjas) {
					int[] ninPosition = ninja.getPosition();
					if (ninPosition[0] == playerRow) {
						if (ninPosition[1] > playerCol) {
							ninjaAhead = true;
						}
					}
				}
				grid[playerRow][playerCol + 1].see();
				grid[playerRow][playerCol + 2].see();
				break;
			case 3:
				for (Ninja ninja : ninjas) {
					int[] ninPosition = ninja.getPosition();
					if (ninPosition[1] == playerCol) {
						if (ninPosition[0] > playerRow) {
							ninjaAhead = true;
						}
					}
				}
				grid[playerRow + 1][playerCol].see();
				grid[playerRow + 2][playerCol].see();
				break;
			case 4:
				for (Ninja ninja : ninjas) {
					int[] ninPosition = ninja.getPosition();
					if (ninPosition[0] == playerRow) {
						if (ninPosition[1] < playerCol) {
							ninjaAhead = true;
						}
					}
				}
				grid[playerRow][playerCol - 1].see();
				grid[playerRow][playerCol - 2].see();
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		} finally {
			if (ninjaAhead) {
				System.out.println("Ninja ahead!");
			} else {
				System.out.println("All clear.");
			}
			printGrid();
		}
	}

	public void killCheck() {

		for (Ninja currNinja : ninjas) {
			if(!currNinja.totallyGotShot()){
			int[] pp = thePlayer.getPosition();
			int[] nc = currNinja.getPosition();
			int row = pp[0];
			int col = pp[1];
			int nrow = nc[0];
			int ncol = nc[1];

			if (nrow + 1 == row && ncol == col) {
				System.out.println("You have died");
				thePlayer.dies();
				playerRespawn();

			} else if (nrow - 1 == row && ncol == col) {
				System.out.println("You have died");
				thePlayer.dies();
				playerRespawn();
			} else if (nrow == row && ncol + 1 == col) {
				System.out.println("You have died");
				thePlayer.dies();
				playerRespawn();
			} else if (nrow == row && ncol - 1 == col) {
				System.out.println("You have died");
				thePlayer.dies();
				playerRespawn();
			} else {
				// Nothing happens here.
			}
		}
			else{
				// Nothing happens here if totallyGotShot is true.
				
			}
		}
	}


	public void deathCheck() {
		if (thePlayer.showCantDieTime() <= 0) {
			killCheck();
		}
	}

	public void moveNinjas(int row, int col) {
		for (Ninja currNinja : ninjas) {
			int direction;
			boolean hasMoved = false;
			boolean tried = false;
			boolean isDead = false;
			do {
				isDead = ifNinjaPlaceIsEmptyCell(currNinja);
				
				if (isDead){
					hasMoved = true;
					break;
				}
				deathCheck();
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
							currNinja.setPosition((ninCoord[0] + 1),
									ninCoord[1]);
							hasMoved = true;
						}
						runAllChecks();
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
							currNinja.setPosition(ninCoord[0],
									(ninCoord[1] + 1));
							hasMoved = true;
						}
						runAllChecks();
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
							currNinja.setPosition((ninCoord[0] - 1),
									ninCoord[1]);
							hasMoved = true;
						}
						runAllChecks();
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
							currNinja.setPosition(ninCoord[0],
									(ninCoord[1] - 1));
							hasMoved = true;
						}
						runAllChecks();
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					hasMoved = false;
					currNinja.setPosition(ninCoord[0], ninCoord[1]);
				}
			} while (!hasMoved && !isDead);
		}
	}

	public GridMember getGridMember(int row, int col) {

		return grid[row][col];

	}

	public int[] getPlayerPostion() {
		return playerPosition;
	}

	public void shootDirection(int direction) {
		int[] playerPosition = getPlayerPostion();
		int row = playerPosition[0];
		int col = playerPosition[1];
		switch (direction) {

		case 1:
			thePlayer.setPlayerDirection(1);
			shoot(row-1, col , direction);
			break;
		case 2:
			thePlayer.setPlayerDirection(2);
			shoot(row, col-1, direction);
			break;
		case 3:
			thePlayer.setPlayerDirection(3);
			shoot(row+1, col, direction);
			break;
		case 4:
			thePlayer.setPlayerDirection(4);
			shoot(row, col+1, direction);
			break;
		}
	}

	public void shoot(int row, int col, int direction) {
		try {
			switch (direction) {
			case 1:
				while (!(grid[row][col] instanceof Room) || !(grid[row][col] instanceof Ninja)) {
					if (grid[row][col] instanceof Room) {
						System.out.println("You shot a wall of a room"); //Prints when you shoot a room
						break;                                           //only here to check if this works
					} else if (grid[row][col] instanceof Ninja) {
						for (Ninja currNinja: ninjas){
							int[] shotPosition = currNinja.getPosition();
							if (grid[row][col] == grid[shotPosition[0]][shotPosition[1]]){
								System.out.println("Totally got shot"); //Placed here to check if ninja got shot
								currNinja.gotShot();                    //will remove later
								grid[row][col] = null;
								fixTheNull(row, col);
								break;
							}
						}
					} else {
						
					}
					row--;
				}

			case 2:
				while (!(grid[row][col] instanceof Room) || !(grid[row][col] instanceof Ninja)) {
					if (grid[row][col] instanceof Room) {
						System.out.println("You shot a wall of a room"); //Prints when you shoot a room
						break;                                           //only here to check if this works
					} else if (grid[row][col] instanceof Ninja) {
						for (Ninja currNinja: ninjas){
							int[] shotPosition = currNinja.getPosition();
							if (grid[row][col] == grid[shotPosition[0]][shotPosition[1]]){
								System.out.println("Totally got shot"); //Placed here to check if ninja got shot
								currNinja.gotShot();                    //will remove later
								grid[row][col] = null;
								fixTheNull(row, col);
								break;
							}
						}
						break;
					} else {
				
					}
					col--;
				}

			case 3:
				while (!(grid[row][col] instanceof Room) || !(grid[row][col] instanceof Ninja)) {
					if (grid[row][col] instanceof Room) {
						System.out.println("You shot a wall of a room"); //Prints when you shoot a room
						break;                                           //only here to check if this works
					} else if (grid[row][col] instanceof Ninja) {
						for (Ninja currNinja: ninjas){
							int[] shotPosition = currNinja.getPosition();
							if (grid[row][col] == grid[shotPosition[0]][shotPosition[1]]){
								System.out.println("Totally got shot"); //Placed here to check if ninja got shot
								currNinja.gotShot();                    //will remove later
								grid[row][col] = null;
								fixTheNull(row, col);
								break;
							}
						}
						break;
					} else {
						
					}
					row++;
				}
			case 4:
				while (!(grid[row][col] instanceof Room) || !(grid[row][col] instanceof Ninja)) {
					if (grid[row][col] instanceof Room) {
						System.out.println("You shot a wall of a room"); //Prints when you shoot a room
						break;                                           //only here to check if this works
					} else if (grid[row][col] instanceof Ninja) {
						for (Ninja currNinja: ninjas){
							int[] shotPosition = currNinja.getPosition();
							if (grid[row][col] == grid[shotPosition[0]][shotPosition[1]]){
								System.out.println("Totally got shot"); //Placed here to check if ninja got shot
								currNinja.gotShot();                    //will remove later
								grid[row][col] = null;
								fixTheNull(row, col);
								break;
							}
						}
						break;
					} else {
						
					}
					col++;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You shot a wall"); //This is here only to check if a bullet makes it all the way through
                                                   //to the end of the world, if this prints along with a ninja getting shot
		} finally {                                //please let me know
			thePlayer.shoot();
			playerSeeAround();
			resetPlayerSeeAround();
		}
	}
	
	public boolean ifNinjaPlaceIsEmptyCell(Ninja currNinja){
		if (currNinja.totallyGotShot()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void fixTheNull(int row, int col){
		grid[row][col] = new EmptyMember();
	}

}