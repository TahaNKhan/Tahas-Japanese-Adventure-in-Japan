public class ExtraBullet extends PowerUp implements GridMember {

	
	public String toString() {
		String result;
		if (isSeen()) {
			result = "[B]";
		} else
			result = "[*]";
		return result;
	}
	
	
}