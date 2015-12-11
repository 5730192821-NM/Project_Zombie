package entity;

public abstract class Moving {

	public int x;
	public int y;

	public Moving(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
}
