public abstract class Entity implements GridMember {

	private boolean seen;

	private int[] position = new int[2];

	@Override
	public boolean isSeen() {
		return seen;
	}

	public void setPosition(int row, int col) {
		position[0] = row;
		position[1] = col;
	}

	public int[] getPosition() {
		return position;
	}

	@Override
	public void see() {
		seen = true;
	}

	@Override
	public void resetSee() {
		seen = false;
	}

	@Override
	public boolean isSomething() {
		return true;
	}

}