package entity;

public class Living {

	public int x, y;

	public Living(int x) {
		this.x = x;
		//this.y = (-1)*(int)(Math.sqrt(Math.pow(300, 2) - Math.pow(x - 350, 2))) + 500;
		this.y = (-1)*(int)(Math.sqrt(Math.pow(320, 2) - Math.pow(x - 330, 2))) + 460;
	}
}
