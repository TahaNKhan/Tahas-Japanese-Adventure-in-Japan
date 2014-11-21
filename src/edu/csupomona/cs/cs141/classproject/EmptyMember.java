
public class EmptyMember implements GridMember {

	private boolean seen;


	public String toString() {
		String result;
		if (isSeen()) {
			result = "[ ]";
		} else
			result = "[*]";
		return result;
	}

	public boolean isSeen() {
		return seen;
	}


	public void resetSee() {
		seen = false;
	}


	public void see() {
		seen = true;
	}

	public boolean isSomething() {
		return false;
	}

}