package entity;

public class Moving {

	public int x, y,radius;
	public double angle;

	public Moving(double a,int r) {
		this.angle = a;
		this.radius = r;
		this.x = 350 - (int) ((Math.sin(angle) * radius));
		this.y = 500 - (int) ((Math.cos(angle) * radius));
	}
	
	public void update() {
		this.x = 350 + (int) ((Math.sin(angle) * radius));
		this.y = 500 + (int) ((Math.cos(angle) * radius));
	}
}
