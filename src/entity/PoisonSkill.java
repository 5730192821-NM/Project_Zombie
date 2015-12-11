package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class PoisonSkill implements Renderable{
	
	private static boolean isPlaying;
	private static int frameCount,count,x,y,frameWidth,frameHeight,direction;
	
	public PoisonSkill(int x,int y,int direction){
		PoisonSkill.direction=direction;
		if(direction==1)
			PoisonSkill.x=x+100;
		else PoisonSkill.x=x-100;
		PoisonSkill.y=340;
		try {
			frameWidth = Resource.poison1.getWidth() / 7;
			frameHeight = Resource.poison1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}
	
	public static void setX(int x){
		PoisonSkill.x -= x; 
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public static void update(){
		if(isPlaying){
			if(count==10){
				count=0;
				frameCount++;
				if(frameCount==7)
					stop();
			}
			if(direction==1)
				x+=5;
			else
				x-=5;
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isPlaying && (Resource.poison1 != null)) {
			g.drawImage(Resource.poison1.getSubimage(Resource.poison1.getWidth() / 7 * frameCount,0, frameWidth, frameHeight), null, x, y);
		}
	}
	
	public void play(){
		isPlaying=true;
		frameCount=0;
		count=0;
	}
	
	public static void stop(){
		isPlaying=false;
		frameCount=0;
		count=0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isPlaying;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
