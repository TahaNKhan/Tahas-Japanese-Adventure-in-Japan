import java.util.Random;

public class Ninja extends Entity implements GridMember {

	private int direction;

	private boolean playerInSight;
	
	private boolean isShot;

	public int getDirection() {
		return direction;
	}

	public void updatePosition(int row, int col) {

	}

	// incomplete method that takes care of the "AI" of the ninja as well as
	// moving randomly
	public void setDirection(Taha player) {
		int[] playerCoordinates = player.getPosition();
		int[] ninjaCoordinates = getPosition();
		Random rand = new Random();
		if (playerCoordinates[0] == ninjaCoordinates[0]) {
			if (playerCoordinates[1] > ninjaCoordinates[1]) {
				direction = 2;
			} else {
				direction = 4;
			}
			playerInSight = true;
		} else if (playerCoordinates[1] == ninjaCoordinates[1]) {
			if (playerCoordinates[0] > ninjaCoordinates[0]) {
				direction = 1;
			} else {
				direction = 3;
			}
			playerInSight = true;
		} else {
			direction = rand.nextInt(4) + 1;
		}

	}

	public void setDirection(int row, int col) {

		int[] ninjaCoordinates = getPosition();
		Random rand = new Random();
		if (row == ninjaCoordinates[0]) {

			if (col > ninjaCoordinates[1]) {
				direction = 2;
			} else {
				direction = 4;
			}
			playerInSight = true;

		} else if (col == ninjaCoordinates[1]) {

			if (row > ninjaCoordinates[0]) {
				direction = 1;
			} else {
				direction = 3;
			}
			playerInSight = true;

		} else {
			direction = rand.nextInt(4) + 1;
			playerInSight = false;
		}

	}

	public boolean getPlayerInSight() {
		return playerInSight;
	}

	public void resetPlayerInSight() {
		playerInSight = false;
	}
	
	public void gotShot(){
		isShot = true;
	}
	
	public boolean totallyGotShot(){
		return isShot;
	}

	@Override
	public String toString() {
		String result;
		
		if(isShot){
			result = "[*]";
			return result;
		}

		else{
			result = "[N]";
			return result;
		}
	}
}