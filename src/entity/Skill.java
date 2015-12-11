package entity;

public abstract class Skill {
	
	protected boolean isPlaying = false;
	protected int x, y;
	
	public Skill(int x, int y, int direction) {
		if (direction == 1)
			this.x = x + 300;
		else
			this.x = x - 270;
		this.y = 320;
	}

	public abstract void setX(int i);

	public abstract void update();

	public boolean isPlaying() {
		return isPlaying;
	}
	
	
}
