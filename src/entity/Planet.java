package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Planet implements Renderable {
	protected int x,y,radius;
	
	public Planet(int x,int y,int radius){
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.BLUE);
		//g.fillOval(x,y,2*radius,2*radius);
		g.drawImage(Resource.planet,null,x,y);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
