
public abstract class PowerUp implements GridMember {

	private boolean seen;
	
	private boolean isUsed;

	public boolean isSeen() {
		return seen;
	}

	public void see() {
		seen = true;
	}

	public boolean isSomething() {
		return true;
	}

	public void resetSee() {
		seen = false;
	}
	
	public void powerGain(){
		isUsed = true;
	}
	
	public boolean isUsed(){
		return isUsed;
	}
	

}