package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class FireSkill implements Renderable{
	
	private static boolean isPlaying;
	private static int frameCount,count,x,y,frameWidth,frameHeight;
	
	public FireSkill(int x,int y,int direction){
		if(direction==1)
			FireSkill.x=x+300;
		else FireSkill.x=x-270;
		FireSkill.y=350;
		try {
			frameWidth = Resource.fire1.getWidth() / 4;
			frameHeight = Resource.fire1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}
	
	public static void setX(int x){
		FireSkill.x -= x; 
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public static void update(){
		if(isPlaying){
			if(count==10){
				count=0;
				frameCount++;
				if(frameCount==4)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isPlaying && (Resource.fire1 != null)) {
			g.drawImage(Resource.fire1.getSubimage(Resource.fire1.getWidth() / 4 * frameCount,0, frameWidth, frameHeight), null, x, y);
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
