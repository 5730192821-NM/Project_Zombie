package entity;

public abstract class Moving {

	protected int x;
	protected int y;

	public Moving(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update();
	
}
