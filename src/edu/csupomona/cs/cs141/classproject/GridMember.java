public interface GridMember {

	@Override
	String toString();

	boolean isSeen();

	boolean isSomething();

	void see();

	void resetSee();
}