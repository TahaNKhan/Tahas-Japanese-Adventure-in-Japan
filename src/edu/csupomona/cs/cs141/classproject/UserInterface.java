import java.util.Scanner;
import java.util.InputMismatchException;


public class UserInterface {
	
	private Taha player = new Taha();
	
	GameEngine gameEng = new GameEngine(player);

	Scanner kb = new Scanner(System.in);
	
	
	
	public void options(){
		int shootMove = 9;
		while(shootMove != 0){
		gameEng.callGridSeeReset();
		gameEng.callGridSeeAround();
		gameEng.printGrid();
		gameEng.gameOverCheck();
		doIWinYet();
		doYouWannaSee();
		System.out.println("1 Shoot 2 Move");
		playerStatus();
		gameEng.playerTurnUsedWhileInvincible();
		shootMove = kb.nextInt();
		if (shootMove == 1){
			if(gameEng.ammoCheck()){
				playerShoot();
			}
			else{
				System.out.println("You are out of ammo.");
			}
		}
		else{
			theGameInterface();
		}
		}
	}
	
	public void theGameInterface() {
		
		String playerChoice = "Q";
		
			System.out
					.println("W. Up, D. Right, S. Down, A. Left, or 0 to quit.");
			try {
				playerChoice = kb.next();
				kb.nextLine();
					gameEng.move(playerChoice.toLowerCase());
				
			} catch (InputMismatchException e) {
				System.out.println("Please enter correct input.");
				kb.next();
			}
		}
	
	public void playerShoot(){
		System.out.println("Which direction to shoot? 1 up 2 left 3 down 4 right");
		int shootChoice = kb.nextInt();
		gameEng.shootDirection(shootChoice);
	}
	
	public void playerStatus(){
		System.out.println("Lives: " + gameEng.lives()+ " Ammo: "+ gameEng.ammo());
		System.out.println("Turns invincible: "+ gameEng.cantDie() );
	}
	
	public void doIWinYet(){
		if(gameEng.recieveWinFromGrid()){
			System.out.println("You have won the game");
			System.exit(0);
		}
	}
	
	public void doYouWannaSee(){
		int lookFurther = 0;
		System.out.println("Do you want to look further in a direction? 1 yes 2 no");
		lookFurther = kb.nextInt();
		if(lookFurther == 1){
			wantedToSee();
		}
		else{
			System.out.println("Guess you wanna be blind");
		}
	}
	
	public void wantedToSee(){
		int lookDirection = 0;
		System.out.println("Which direction do you wanna look at?");
		System.out.println("1 up, 2 right, 3 down, 4 left" );
		lookDirection = kb.nextInt();
		gameEng.playerLook(lookDirection);
	}
}
