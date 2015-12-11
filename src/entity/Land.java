package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Land extends Moving implements Renderable{
	private static int xc=0;
	private static boolean isEnd=false;

	public Land(int x, int y) {
		super(x, y);
		isEnd=false;
		// TODO Auto-generated constructor stub
	}
	
	public static void setX(int x){
		xc+=x;
	}
	
	public static boolean isEnd(){
		return isEnd;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(x+xc<=Resource.land1.getWidth()-800){
			x+=xc;
			xc=0;
		}
		if(x+xc>=Resource.land1.getWidth()-800)
			isEnd=true;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(Resource.land1.getSubimage(x, 0, 800, Resource.land1.getHeight()), null, 0, 435);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
