public class Invincibility extends PowerUp implements GridMember {

	
	public String toString() {
		String result;
		if (isSeen()) {
			result = "[I]";
		} else
			result = "[*]";
		return result;
	}
	
}