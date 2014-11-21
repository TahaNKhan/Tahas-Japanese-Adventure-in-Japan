public class Radar extends PowerUp implements GridMember {
	
	
	public String toString() {
		String result;
		if (isSeen()) {
			result = "[r]";
		} else
			result = "[*]";
		return result;
	}
	

}