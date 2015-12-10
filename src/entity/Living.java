package entity;

public class Living {

	public int x, y;
	public double angle;

	public Living(double a) {
		this.angle = a;
		this.x = 350 + (int)((Math.sin(a)*300));
		this.y = 500 + (int)((Math.cos(a)*300));
	}
}
