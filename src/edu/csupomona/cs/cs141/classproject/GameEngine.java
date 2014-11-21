import java.util.Random;

/**
 * @author
 *
 */
public class GameEngine { //A lot of stuff has been moved to Grid

	private Taha thePlayer;
	
	private Grid grid;

	private int playerDirection; // moved from Taha class

	public GameEngine(Taha tahaPlayer) {

		thePlayer = new Taha();
		playerDirection = thePlayer.getPlayerDirection();
		grid = new Grid(thePlayer);
		
	}
	
	public boolean recieveWinFromGrid(){
		if(grid.showWin()){
			return true;
		}
		else{
			return false;
		}
	}


	public void playerTurnUsedWhileInvincible() {
		if (thePlayer.showCantDieTime() > 0) {
			thePlayer.cantDieCheck();
		}
	}
	
	public void gameOverCheck() {
		if (thePlayer.showLives() <= 0) {
			System.out.println("Game Over");
			System.exit(0);
		}
	}
	
	public void printGrid(){
		GridMember gm = null;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				gm = grid.getGridMember(i, j);
				System.out.print(gm.toString());
			}
			System.out.println();
		}
		System.out.println();
	}
	

	public void move(String direction) { 
		int[] playerPosition = grid.getPlayerPostion(); 
		int row = playerPosition[0]; 
		int col = playerPosition[1]; 
		switch (direction) {

		case "w":
			thePlayer.setPlayerDirection(1);
			grid.movePlayer(row - 1, col);
			break;
		case "d":
			thePlayer.setPlayerDirection(2);
			grid.movePlayer(row, col + 1);
			break;
		case "s":
			thePlayer.setPlayerDirection(3);
			grid.movePlayer(row + 1, col);
			break;
		case "a":
			thePlayer.setPlayerDirection(4);
			grid.movePlayer(row, col - 1);
			break;
		}
	}
	
	public void playerLook(int direction) {
		grid.look(direction);
	}

	public int getPlayerDirection() { // Moved from Taha class
		return playerDirection;
	}
	
	public void callGridSeeAround(){
		grid.playerSeeAround();
	}
	
	public void callGridSeeReset(){
		grid.resetSeeAll();
	}

	public Taha getPlayer() {
		return thePlayer;
	}
	
	public int lives() {
		return thePlayer.showLives();
	}

	public int ammo() {
		return thePlayer.showAmmo();
	}

	public int cantDie() {
		return thePlayer.showCantDieTime();
	}

	public void shootDirection(int shootChoice) {
		
		grid.shootDirection(shootChoice);
		
	}
	
	public boolean ammoCheck(){
		if(thePlayer.showAmmo() > 0){
			return true;
		}
		else{
			return false;
		}
	}

}