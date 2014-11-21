
public class Taha extends Entity implements GridMember {

	private int lives;

	private int ammo;

	private int cantDieDuration;
	
	private int playerDirection;
	
	
	public Taha() {
		lives = 3;
		ammo = 1;
		playerDirection = 1;
	}

	public int showLives() {
		return lives;
	}

	public int showAmmo() {
		return ammo;
	}
	
	public int showCantDieTime(){
		return cantDieDuration;
	}
	
	public void cantDieCheck(){
		cantDieDuration--;
	}

	public void shoot() {
		ammo--;
	}

	public void dies() { 						
		lives--; 
	}
	
	public void addAmmo(){
		ammo++;
	}
	
	public void isWalkingOnSunshine(){
		cantDieDuration = 5;
	}


	public String toString() {
		String result = "[P]";
		return result;
	}

	
	public boolean isSeen() {
		return true;
	}
	
	public void setPlayerDirection(int direction) {
		playerDirection = direction;
	}
	
	public int getPlayerDirection() {
		return playerDirection;
	}


}