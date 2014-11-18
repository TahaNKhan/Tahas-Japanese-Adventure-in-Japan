/**
 * 
 */
package edu.csupomona.cs.cs141.classproject;

/**
 * @author Isa
 *
 */
 
 //Ultimately, we should move the move method into the game engine
 //For now I'm adding in attributes that the player should have
 //leave your name here so I know who's doing what lol -Taha
 
public class Taha extends Entity implements GridMember{
	
	private int playerDirection;
	
	private int lives;
	
	private int ammo;
	
	public Taha(){ //Going to change this constructor where lives = 3 and ammo = 1
		playerDirection = 1;
	}
	
	public int showLives(){
		return lives;
	}
	
	public int showAmmo(){
		return ammo;
	}
	
	public void shoot(){
		ammo--;
	}
	
	public void dies(){ //when a ninja kills the player, this method should be called, then another method
		lives--;    // that places the player at spawn should be called
	}
	
	public void move(String direction, Grid grid, GameEngine GMENG){ //going to move this to the GameEngine
		int[] playerPosition = grid.getPlayerPostion();
		int row = playerPosition[0];
		int col = playerPosition[1];
		switch(direction){
		
		case "w":
			playerDirection = 1;
			GMENG.movePlayer(row-1, col);
			break;
		case "d":
			playerDirection = 2;
			GMENG.movePlayer(row, col+1);
			break;
		case "s":
			playerDirection = 3;
			GMENG.movePlayer(row+1, col);
			break;
		case "a":
			playerDirection = 4;
			GMENG.movePlayer(row, col-1);
			break;
		}
		
		
	}
	
	public int getPlayerDirection(){
		return playerDirection;
	}
	
	public String toString(){
		String result = "[P]";
		return result;
	}
	
	public boolean isSeen(){
		return true;
	}
	
	
}
